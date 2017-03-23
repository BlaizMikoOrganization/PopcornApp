package com.blaizmiko.popcornapp.ui.all.presentation.expandingtext;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.ui.all.presentation.BaseMvpPresenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@InjectViewState
public class ExpandingTextPresenter extends BaseMvpPresenter<ExpandingTextView> {
    private int STORYLINE_EXPAND_LINES = 8;
    private List<Boolean> isExpand;
    private int [] expandedLinesSize;

    public void calculateNewSize(int position, int maxLines) {
        STORYLINE_EXPAND_LINES = maxLines;
        final int STORYLINE_HIDE_LINES = 3;

        if (isExpand.get(position)) {
            isExpand.set(position, false);
            getViewState().changeTextViewSize(STORYLINE_HIDE_LINES);
            return;
        }
        isExpand.set(position, true);
        getViewState().changeTextViewSize(expandedLinesSize[position]);
    }

    public void setExpandedLinesNumber(int lines) {
        STORYLINE_EXPAND_LINES = lines;
    }

    public void setExpandedLinesSize(int [] expandedLines) {
        expandedLinesSize = expandedLines;
    }

    public void setViewsAmount(int amount) {
        isExpand = new ArrayList<>(amount);
        for (int i = 0; i < amount; i++) {
            isExpand.add(i,true);
        }
    }

}

