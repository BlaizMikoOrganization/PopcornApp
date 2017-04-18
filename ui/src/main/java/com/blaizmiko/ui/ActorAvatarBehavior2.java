package com.blaizmiko.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.blaizmiko.ui.R;

import de.hdodenhof.circleimageview.CircleImageView;

@SuppressWarnings("unused")
public class ActorAvatarBehavior2 extends CoordinatorLayout.Behavior<CircleImageView> {

    private final static float MIN_AVATAR_PERCENTAGE_SIZE   = 0.3f;
    private final static int EXTRA_FINAL_AVATAR_PADDING     = 80;

    private final static String TAG = "behavior";
    private Context mContext;

//    private float mCustomFinalYPosition;
//    private float mCustomStartXPosition;
//    private float mCustomStartToolbarPosition;
//    private float mCustomStartHeight;
    private float mCustomFinalHeight;

    private float mAvatarMaxSize;
    private float mFinalLeftAvatarPadding;
    private float mStartPosition;
    private int mStartXPosition;
    private float mStartToolbarPosition;
    private int mStartYPosition;
    private int mFinalYPosition;
    private int mStartHeight;
    private int mFinalXPosition;
    private float mChangeBehaviorPoint;

    private int toolbarHeight;
    private boolean wasInitiated = false;
    private int startPictureYPosition;
    private int startToolbarYPosition;
    private int startPictureXPosition;
    private int finalPictureSize = 25;
    private int startPictureSize;

    public ActorAvatarBehavior2(Context context, AttributeSet attrs) {
        mContext = context;

        mCustomFinalHeight = 0;

        TypedValue tv = new TypedValue();
        if (mContext.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
        {
             toolbarHeight = TypedValue.complexToDimensionPixelSize(tv.data, mContext.getResources().getDisplayMetrics());

        }

        finalPictureSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, finalPictureSize , mContext.getResources().getDisplayMetrics());

    }


    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, CircleImageView child, View dependency) {
        return dependency instanceof Toolbar;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, CircleImageView child, View dependency) {
        System.out.println("child");
        System.out.println("Y" +child.getY() +"height" +child.getHeight() +"top" +child.getTop());
        System.out.println("dependency");
        System.out.println("Y" +dependency.getY() +"height" +dependency.getHeight() +"top" +dependency.getTop() +"measured" +dependency.getMeasuredHeight() +"bot" +dependency.getBottom());
        initStartValues(child, dependency);
        //maybeInitProperties(child, dependency);

        float toolbarPassPercentOfHeight = (float) (startToolbarYPosition - (dependency.getBottom() -toolbarHeight))/ startToolbarYPosition;
        System.out.println("toolbar pass = "+toolbarPassPercentOfHeight);
        System.out.println("new y =" +(1f - toolbarPassPercentOfHeight) * startPictureYPosition);
        child.setY( (1f - toolbarPassPercentOfHeight) * startPictureYPosition);
        System.out.println("new x =" +(1f - toolbarPassPercentOfHeight) * startPictureXPosition);
        child.setX( (1f - toolbarPassPercentOfHeight) * startPictureXPosition);


        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();

        System.out.println("startPictureSize = " +startPictureSize);
        System.out.println("new size = " +(int)((1f - toolbarPassPercentOfHeight) * startPictureSize));
        lp.width = (int)((1f - toolbarPassPercentOfHeight) * (startPictureSize - finalPictureSize) + finalPictureSize);
        lp.height = (int)((1f - toolbarPassPercentOfHeight) * (startPictureSize - finalPictureSize) + finalPictureSize);
        child.setLayoutParams(lp);



        return true;
    }

    private void maybeInitProperties(CircleImageView child, View dependency) {

        if (mStartYPosition == 0)
            mStartYPosition = 500;


        if (mFinalYPosition == 0)
            mFinalYPosition = (dependency.getHeight() /2);

        if (mStartHeight == 0)
            mStartHeight = child.getHeight();

        if (mStartXPosition == 0)
            mStartXPosition = (int) (child.getX() + (child.getWidth() / 2));

        if (mFinalXPosition == 0)
            mFinalXPosition = mContext.getResources().getDimensionPixelOffset(R.dimen.abc_action_bar_content_inset_material) + ((int) mCustomFinalHeight / 2);

        if (mStartToolbarPosition == 0)
            mStartToolbarPosition = dependency.getY();

        if (mChangeBehaviorPoint == 0) {
            mChangeBehaviorPoint = (child.getHeight() - mCustomFinalHeight) / (2f * (mStartYPosition - mFinalYPosition));
        }
    }

    private void initStartValues(CircleImageView child, View dependency) {
        if (wasInitiated) return;

        startPictureYPosition = (int)child.getY();
        startPictureXPosition = (int)child.getX();
        startPictureSize = (int) child.getWidth();

        System.out.println("startPictureYPosition " +startPictureYPosition);



        startToolbarYPosition = dependency.getHeight() - toolbarHeight;




        wasInitiated = true;
    }
}