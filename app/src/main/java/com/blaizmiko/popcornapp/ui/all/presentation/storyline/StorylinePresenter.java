package com.blaizmiko.popcornapp.ui.all.presentation.storyline;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.ui.all.presentation.BaseMvpPresenter;

@InjectViewState
public class StorylinePresenter extends BaseMvpPresenter<StorylineView>{
    private int STORYLINE_EXPAND_LINES = 8;
    private boolean isStoryLineTextViewOpen = true;

    public void calculateNewSize() {
        final int STORYLINE_HIDE_LINES = 3;

        if (isStoryLineTextViewOpen) {
            isStoryLineTextViewOpen = false;
            getViewState().changeStorylineSize(STORYLINE_HIDE_LINES);
            return;
        }
        isStoryLineTextViewOpen = true;
        getViewState().changeStorylineSize(STORYLINE_EXPAND_LINES);
    }

    public void setExpandedLinesNumber(int lines) {
        STORYLINE_EXPAND_LINES = lines;
    }
}
