package com.blaizmiko.popcornapp.presentation.presenters;

import android.support.v7.widget.RecyclerView;

public class CustomOnScrollListener extends RecyclerView.OnScrollListener {

    public interface CustomScrollListener {
        void onScrolled(RecyclerView recyclerView);
    }
    CustomScrollListener listener;

    public CustomOnScrollListener(CustomScrollListener listener) {
        this.listener = listener;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        listener.onScrolled(recyclerView);
    }
}
