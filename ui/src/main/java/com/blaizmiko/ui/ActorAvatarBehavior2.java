package com.blaizmiko.ui;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import org.androidannotations.annotations.App;

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



    private float toolbarHeight;
    private boolean wasInitiated = false;

    private float startToolbarYPosition;
    private float changeBehaviorPoint;

    private int finalPictureSize = 25;
    private int finalPictureXMargin = 36;
    private int finalPictureYMargin = 16;


    private float startPictureXPosition = 16;
    private float finalPictureXPosition = 36;
    private float finalPictureYPosition = 16;
    private float startPictureYPosition;
    private int startPictureSize;

    public ActorAvatarBehavior2(Context context, AttributeSet attrs) {
        mContext = context;
        mCustomFinalHeight = 0;

        TypedValue tv = new TypedValue();
        if (mContext.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
             toolbarHeight = TypedValue.complexToDimensionPixelSize(tv.data, mContext.getResources().getDisplayMetrics());

        }

        startPictureXPosition = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, startPictureXPosition , mContext.getResources().getDisplayMetrics());
        finalPictureXPosition = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, finalPictureXPosition , mContext.getResources().getDisplayMetrics());
        finalPictureYPosition = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, finalPictureYPosition , mContext.getResources().getDisplayMetrics());


        finalPictureSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, finalPictureSize , mContext.getResources().getDisplayMetrics());
        finalPictureXMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, finalPictureXMargin, mContext.getResources().getDisplayMetrics());
        finalPictureYMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, finalPictureYMargin, mContext.getResources().getDisplayMetrics());
    }


    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, CircleImageView child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, CircleImageView child, View dependency) {
        initStartValues(child, dependency);


        float toolbarPassPercentOfHeight =(startToolbarYPosition - dependency.getBottom())/ startToolbarYPosition;

        child.setX(startPictureXPosition + (finalPictureXPosition - startPictureXPosition) * toolbarPassPercentOfHeight);
        child.setY(finalPictureYPosition + (finalPictureYPosition - startPictureYPosition) * toolbarPassPercentOfHeight);

        System.out.println("child");
        System.out.println("Y" + child.getY() + "height" + child.getHeight() + "top" + child.getTop());
        System.out.println("dependency");
        System.out.println("Y" + dependency.getY() + "height" + dependency.getHeight() + "top" + dependency.getTop());

        return true;
    }
        /*



        child.setX(16);
        child.setY(16);
        System.out.println(startToolbarYPosition - dependency.getY());
        System.out.println();
        initStartValues(child, dependency);
        System.out.println("");


        float toolbarPassPercentOfHeight =(startToolbarYPosition - dependency.getY())/ startToolbarYPosition;

        child.setY();
        System.out.println("start toolbar y = " +startToolbarYPosition);
        System.out.println("toolbar.getY = " +dependency.getY());
        System.out.println("expecting = " +(startToolbarYPosition - (dependency.getBottom() -toolbarHeight)));

        //if (1 - toolbarPassPercentOfHeight > changeBehaviorPoint) {
            System.out.println("child");
            System.out.println("Y" + child.getY() + "height" + child.getHeight() + "top" + child.getTop());
            System.out.println("dependency");
            System.out.println("Y" + dependency.getY() + "height" + dependency.getHeight() + "top" + dependency.getTop() + "measured" + dependency.getMeasuredHeight() + "bot" + dependency.getBottom());

            //maybeInitProperties(child, dependency);


            System.out.println("toolbar pass = " + toolbarPassPercentOfHeight);
            System.out.println("new y =" + (1f - toolbarPassPercentOfHeight) * startPictureYPosition);
            //child.setY(finalPictureYMargin + (1f - toolbarPassPercentOfHeight) * startPictureYPosition);

        System.out.println("start ");
            System.out.println("new x =" + toolbarPassPercentOfHeight * startPictureXPosition);
            //child.setX((1f - toolbarPassPercentOfHeight) * startPictureXPosition);


            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();

            System.out.println("startPictureSize = " + startPictureSize);
            System.out.println("new size = " + (int) ((1f - toolbarPassPercentOfHeight) * startPictureSize));
            lp.width = (int) ((1f - toolbarPassPercentOfHeight) * (startPictureSize - finalPictureSize) + finalPictureSize);
            lp.height = (int) ((1f - toolbarPassPercentOfHeight) * (startPictureSize - finalPictureSize) + finalPictureSize);
            child.setLayoutParams(lp);
        //} else {
//            float distanceYToSubtract = ((startPictureYPosition - 0)
//                    * (1f - toolbarPassPercentOfHeight)) + (startPictureYPosition/2);
//            child.setX(50);
//            child.setY(50);
        //}


        return true;
    }*/

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

        //child.setX(startPictureXPosition);


        startToolbarYPosition = dependency.getBottom();

        child.setY(dependency.getBottom() - child.getHeight()/2);
        child.setX(startPictureXPosition);

        //child.setY(dependency)

        System.out.println("startPictureYPosition = " +child.getY());
        System.out.println("startPictureXPosition = " +child.getX());
        System.out.println("startPictureSize = " +child.getWidth());
        System.out.println("startToolbarYPosition = " +dependency.getY());


        startPictureYPosition = dependency.getBottom() - child.getHeight()/2;



        //startPictureYPosition = (int) (dependency.getY() - child.getHeight()/2);
        //startPictureXPosition = (dependency.getHeight()/2 - child.getWidth()/2);
        //startPictureSize = (int) child.getWidth();


        //changeBehaviorPoint = (child.getHeight() - 0) / (2f * (startPictureYPosition - 0));

        //startToolbarYPosition = (int)dependency.getY();

        System.out.println("startPictureYPosition = " +startPictureYPosition);
        System.out.println("startPictureXPosition = " +startPictureXPosition);
        System.out.println("startPictureSize = " +startPictureSize);
        System.out.println("startToolbarYPosition = " +dependency.getY());




        wasInitiated = true;
    }
}