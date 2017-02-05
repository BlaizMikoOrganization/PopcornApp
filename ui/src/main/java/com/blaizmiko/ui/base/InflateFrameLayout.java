package com.blaizmiko.ui.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

public abstract class InflateFrameLayout extends FrameLayout {

    public InflateFrameLayout(final Context context) {
        super(context);
    }

    public InflateFrameLayout(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public InflateFrameLayout(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public InflateFrameLayout(final Context context, final AttributeSet attrs, final int defStyleAttr, final int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(final Context pContext, final AttributeSet pAttrs) {
        View.inflate(pContext, getViewLayout(), this);
        onCreateView(pContext, pAttrs);
    }

    protected abstract void onCreateView(final Context pContext, final AttributeSet pAttrs);

    @LayoutRes
    protected abstract int getViewLayout();
}
