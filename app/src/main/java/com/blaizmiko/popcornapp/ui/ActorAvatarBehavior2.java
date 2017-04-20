package com.blaizmiko.popcornapp.ui;

import android.content.Context;
import android.graphics.Rect;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;

import com.blaizmiko.popcornapp.ui.actors.details.DetailsActorActivity;

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
    private float backButtonWidth;
    private boolean wasInitiated = false;

    private float startToolbarYPosition;
    private float changeBehaviorPoint;

    private int finalPictureSize = 32;
    private int finalPictureXMargin = 36;
    private int finalPictureYMargin = 16;


    private float startPictureXPosition = 16;
    private float finalPictureXPosition = 40;
    private float finalPictureYPosition = 0;
    private float startPictureYPosition;
    private int startPictureSize;

    public ActorAvatarBehavior2(Context context, AttributeSet attrs) {
        mContext = context;
        mCustomFinalHeight = 0;

        TypedValue tv = new TypedValue();
        if (mContext.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
             toolbarHeight = TypedValue.complexToDimensionPixelSize(tv.data, mContext.getResources().getDisplayMetrics());
        }

        if (mContext.getTheme().resolveAttribute(android.R.attr.homeAsUpIndicator, tv, true)) {
            backButtonWidth = TypedValue.complexToDimensionPixelSize(tv.data, mContext.getResources().getDisplayMetrics());
            System.out.println("backButtonWidth " +backButtonWidth);
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

        float finalPicY = getStatusBarHeight() + (toolbarHeight - finalPictureSize)/2;
        float toolbarPassed = ((dependency.getBottom()- toolbarHeight - getStatusBarHeight())/(startToolbarYPosition - toolbarHeight - getStatusBarHeight()));
        child.setY((startPictureYPosition)*toolbarPassed + finalPicY*(1-toolbarPassed));
        child.setX(finalPictureXPosition*(1 - toolbarPassed) + startPictureXPosition);
        //child.setY((startPictureYPosition)*toolbarPassed +getStatusBarHeight() + (toolbarHeight - finalPictureSize)/2);


        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
        lp.width = (int) (toolbarPassed * (startPictureSize - finalPictureSize) + finalPictureSize);
        lp.height = (int) (toolbarPassed * (startPictureSize - finalPictureSize) + finalPictureSize);
        child.setLayoutParams(lp);


/*        System.out.println("toolbar height " +toolbarHeight);
        System.out.println("current toolbar y position = " +dependency.getBottom());
        float toolbarPassPercentOfHeight =(startToolbarYPosition - (dependency.getBottom() - toolbarHeight - getStatusBarHeight()))/ startToolbarYPosition;
        System.out.println("toolbarPass = " +toolbarPassPercentOfHeight +"   mChangeBehavior " +mChangeBehaviorPoint);
        if (1f - toolbarPassPercentOfHeight > mChangeBehaviorPoint) {
            System.out.println("final = " + finalPictureYPosition + " start = " + startPictureYPosition);
            child.setX(startPictureXPosition + (finalPictureXPosition - startPictureXPosition) * toolbarPassPercentOfHeight);
            child.setY((finalPictureYPosition) + (startPictureYPosition - finalPictureYPosition) * (1f - toolbarPassPercentOfHeight));

            System.out.println("child");
            System.out.println("Y" + child.getY() + "height" + child.getHeight() + "top" + child.getTop());
            System.out.println("dependency");
            System.out.println("Y" + dependency.getY() + "height" + dependency.getHeight() + "top" + dependency.getTop());

            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
            lp.width = (int) ((1f - toolbarPassPercentOfHeight) * (startPictureSize - finalPictureSize) + finalPictureSize);
            lp.height = (int) ((1f - toolbarPassPercentOfHeight) * (startPictureSize - finalPictureSize) + finalPictureSize);
            child.setLayoutParams(lp);
        }

            else {

            System.out.println("child");
            System.out.println("Y" + child.getY() + "height" + child.getHeight() + "top" + child.getTop());
            System.out.println("dependency");
            System.out.println("Y" + dependency.getY() + "height" + dependency.getHeight() + "top" + dependency.getTop());


            child.bringToFront();
            System.out.println("work");
                child.setY(100);
            child.setX(100);
            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
            lp.width = (finalPictureSize);
            lp.height = (finalPictureSize);
            child.setLayoutParams(lp);

            }*/




        return true;
    }



    private void initStartValues(CircleImageView child, View dependency) {


        if (wasInitiated) return;

        getStatusBarHeight();



        //NEW
        //child.setX(dependency.get);
        startToolbarYPosition = dependency.getBottom();
        startPictureYPosition = dependency.getBottom() - child.getWidth()/2;
        startPictureSize = child.getWidth();


        child.setY(startPictureYPosition);

        //

/*

        child.setY(dependency.getBottom() - child.getHeight()/2);
        child.setX(startPictureXPosition);
        //child.setY(dependency)

        System.out.println("startPictureYPosition = " +child.getY());
        System.out.println("startPictureXPosition = " +child.getX());
        System.out.println("startPictureSize = " +child.getWidth());
        System.out.println("startToolbarYPosition = " +dependency.getY());


        startPictureYPosition = dependency.getBottom() - child.getHeight()/2 - getStatusBarHeight();



        mChangeBehaviorPoint = (child.getHeight() - finalPictureSize) / (2f * ((dependency.getBottom() - child.getHeight()) - finalPictureYPosition));
        System.out.println("mChangeB = " +mChangeBehaviorPoint);

        //startPictureYPosition = (int) (dependency.getY() - child.getHeight()/2);
        //startPictureXPosition = (dependency.getHeight()/2 - child.getWidth()/2);
        startPictureSize = (int) child.getWidth();


        //changeBehaviorPoint = (child.getHeight() - 0) / (2f * (startPictureYPosition - 0));

        //startToolbarYPosition = (int)dependency.getY();

        System.out.println("startPictureYPosition = " +startPictureYPosition);
        System.out.println("startPictureXPosition = " +startPictureXPosition);
        System.out.println("startPictureSize = " +startPictureSize);


        startToolbarYPosition = dependency.getBottom();
        System.out.println("startToolbarYPosition = " +startToolbarYPosition);*/


        wasInitiated = true;
    }

    private int getStatusBarHeight() {
        Rect rectangle = new Rect();
        Window window = ((DetailsActorActivity) mContext).getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rectangle);
        int statusBarHeight = rectangle.top;
        int contentViewTop =
                window.findViewById(Window.ID_ANDROID_CONTENT).getTop();
        int titleBarHeight= contentViewTop - statusBarHeight;

        return statusBarHeight;
    }
}