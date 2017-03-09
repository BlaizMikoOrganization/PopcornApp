package com.blaizmiko.popcornapp.ui.movies;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.ui.all.presenters.BaseMvpPresenter;
import com.blaizmiko.popcornapp.ui.movies.StorylineView;

@InjectViewState
public class StorylinePresenter extends BaseMvpPresenter<StorylineView>{

    private boolean mIsStoryLineTextViewOpen = false;

    public void calculateNewSize() {
        final int mStorylineHide = 3;
        final int mStorylineExpand = 8;

        if (mIsStoryLineTextViewOpen) {
            mIsStoryLineTextViewOpen = false;
            getViewState().changeStorylineSize(mStorylineHide);
            return;
        }
        mIsStoryLineTextViewOpen = true;
        getViewState().changeStorylineSize(mStorylineExpand);
    }
}
