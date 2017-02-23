package com.blaizmiko.ui.listeners;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class LoadMoreListener extends RecyclerView.OnScrollListener {

    private int mPastTotalItemCount;

    public interface Loader {
        void onLoadMore(RecyclerView recyclerView);
    }
    Loader mLoader;

    public LoadMoreListener(Loader loader) {
        mLoader = loader;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

        final int visibleItemCount = layoutManager.getChildCount();
        final int totalItemCount = layoutManager.getItemCount();
        final int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

        if (visibleItemCount + pastVisibleItems == totalItemCount && mPastTotalItemCount!=totalItemCount) {
            mPastTotalItemCount = totalItemCount;
            mLoader.onLoadMore(recyclerView);
        }
    }
}
