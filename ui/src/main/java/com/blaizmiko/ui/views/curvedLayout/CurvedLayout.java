package com.blaizmiko.ui.views.curvedLayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.FrameLayout;

import com.blaizmiko.ui.R;
import com.blaizmiko.ui.utils.UiUtils;

public class CurvedLayout extends FrameLayout {

    //Variables
    private static final String TAG = CurvedLayout.class.getName();

    private static final int DEFAULT_CURVED_LAYOUT_HEIGHT = 68;
    private static final int DEFAULT_CURVED_LAYOUT_GRAVITY = CurvedLayoutHelper.Gravity.TOP;
    private static final int DEFAULT_CURVED_LAYOUT_DIRECTION = CurvedLayoutHelper.Direction.OUTWARD;

    private PorterDuffXfermode mPorterDuffXfermode;
    private Paint mPaint;
    private Path mClipPath;

    int mWidth;
    int mHeight;

    int mCurvedLayoutHeight;
    int mCurvedLayoutGravity;
    int mCurvedLayoutDirection;

    //Init view
    public CurvedLayout(final Context context) {
        super(context);
        init(context, null);
    }

    public CurvedLayout(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(final Context context, final AttributeSet attrs) {
        mPorterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.WHITE);

        mClipPath = new Path();

        final TypedArray styledAttributes = context.obtainStyledAttributes(attrs, R.styleable.CurvedLayout, 0, 0);

        mCurvedLayoutHeight = (int) styledAttributes.getDimension(R.styleable.CurvedLayout_curvature, UiUtils.dpToPx(context, DEFAULT_CURVED_LAYOUT_HEIGHT));
        mCurvedLayoutGravity = styledAttributes.getInt(R.styleable.CurvedLayout_gravity, DEFAULT_CURVED_LAYOUT_GRAVITY);
        mCurvedLayoutDirection = styledAttributes.getInt(R.styleable.CurvedLayout_direction, DEFAULT_CURVED_LAYOUT_DIRECTION);

        styledAttributes.recycle();
    }

    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        mClipPath = CurvedLayoutHelper.getClipPath(mWidth, mHeight, mCurvedLayoutHeight,
                mCurvedLayoutDirection, mCurvedLayoutGravity,
                getPaddingTop(), getPaddingBottom(), getPaddingLeft(), getPaddingRight());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ViewCompat.setElevation(this, ViewCompat.getElevation(this));
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            try {
                setOutlineProvider(getOutlineProvider());
            } catch (final Exception e) {
                Log.d(TAG, e.getMessage());
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public ViewOutlineProvider getOutlineProvider() {
        return new ViewOutlineProvider() {

            @Override
            public void getOutline(final View view, final Outline outline) {
                outline.setConvexPath(CurvedLayoutHelper.getOutlinePath(mWidth, mHeight, mCurvedLayoutHeight,
                        mCurvedLayoutDirection, mCurvedLayoutGravity,
                        getPaddingTop(), getPaddingBottom(), getPaddingLeft(), getPaddingRight()));
            }
        };
    }

    @Override
    protected void dispatchDraw(final Canvas canvas) {
        final int saveCount = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        super.dispatchDraw(canvas);
        mPaint.setXfermode(mPorterDuffXfermode);
        canvas.drawPath(mClipPath, mPaint);
        canvas.restoreToCount(saveCount);
        mPaint.setXfermode(null);
    }

    //Public methods
    public void setCurvature(final int height) {
        mCurvedLayoutHeight = UiUtils.dpToPx(getContext(), height);
    }
}
