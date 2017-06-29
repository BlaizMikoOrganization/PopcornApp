package com.blaizmiko.popcornapp.ui.actors.details;

import android.content.Context;
import android.graphics.Rect;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;

import com.blaizmiko.popcornapp.common.utils.FormatUtil;

import de.hdodenhof.circleimageview.CircleImageView;

@SuppressWarnings("unused")
public class ActorAvatarBehavior extends CoordinatorLayout.Behavior<CircleImageView> {
    private Context context;
    private boolean wasInitiated = false;
    private float startToolbarYPosition;
    private float statusBarHeight;
    private float toolbarHeight;
    private float finalPictureSize = 32;
    private float margin = 16;
    private float startPictureXPosition = 16;
    private float finalPictureXPosition = 40;
    private float finalPictureYPosition;
    private float startPictureYPosition;
    private float tabsHeight = 48;
    private int startPictureSize;

    public ActorAvatarBehavior(final Context context, final AttributeSet attrs) {
        this.context = context;
        convertSizes();
    }

    @Override
    public boolean layoutDependsOn(final CoordinatorLayout parent, final CircleImageView child, final View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, CircleImageView avatar, View dependency) {
        initStartValues(avatar, dependency);

        float toolbarPassed = ((dependency.getBottom() - toolbarHeight - statusBarHeight - tabsHeight) / (startToolbarYPosition - toolbarHeight - statusBarHeight - tabsHeight));
        avatar.setY(startPictureYPosition * toolbarPassed + finalPictureYPosition * (1 - toolbarPassed));
        avatar.setX(finalPictureXPosition * (1 - toolbarPassed) + startPictureXPosition);

        final CoordinatorLayout.LayoutParams avatarLayoutParams = (CoordinatorLayout.LayoutParams) avatar.getLayoutParams();
        avatarLayoutParams.width = (int) (toolbarPassed * (startPictureSize - finalPictureSize) + finalPictureSize);
        avatarLayoutParams.height = (int) (toolbarPassed * (startPictureSize - finalPictureSize) + finalPictureSize);
        avatar.setLayoutParams(avatarLayoutParams);
        return true;
    }

    private void convertSizes() {
        final TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            toolbarHeight = TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
        }
        tabsHeight = FormatUtil.convertDPtoPX(tabsHeight, context);
        startPictureXPosition = FormatUtil.convertDPtoPX(startPictureXPosition, context);
        finalPictureXPosition = FormatUtil.convertDPtoPX(finalPictureXPosition, context);
        margin = FormatUtil.convertDPtoPX(margin, context);
        finalPictureSize = FormatUtil.convertDPtoPX(finalPictureSize, context);
    }

    private void initStartValues(CircleImageView child, View dependency) {
        if (wasInitiated) return;

        statusBarHeight = getStatusBarHeight();
        startToolbarYPosition = dependency.getBottom();
        startPictureYPosition = dependency.getBottom() - child.getWidth() - margin - tabsHeight;
        startPictureSize = child.getWidth();
        finalPictureYPosition = statusBarHeight + (toolbarHeight - finalPictureSize) / 2;
        wasInitiated = true;
    }

    private int getStatusBarHeight() {
        final Rect rectangle = new Rect();
        final Window window = ((DetailsActorActivity) context).getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rectangle);
        return rectangle.top;
    }
}