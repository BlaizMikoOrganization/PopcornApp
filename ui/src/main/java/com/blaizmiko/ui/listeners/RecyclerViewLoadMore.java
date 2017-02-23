package com.blaizmiko.ui.listeners;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class RecyclerViewLoadMore extends RecyclerView.OnScrollListener {

    private static final int DEFAULT_ITEM_COUNT_BEFORE_LOAD_MORE = 5;

    private int mCurrentPage;
    private int mPrevTotalItemCount;

    private boolean mIsLoading = true;

    private final OnLoadMoreListener mOnLoadMoreListener;
    private final RecyclerView.LayoutManager mLayoutManager;

    public RecyclerViewLoadMore(final OnLoadMoreListener onLoadMoreListener, final RecyclerView.LayoutManager layoutManager) {
        mOnLoadMoreListener = onLoadMoreListener;
        mLayoutManager = layoutManager;
    }

    @Override
    public void onScrolled(final RecyclerView recyclerView, final int dx, final int dy) {
        super.onScrolled(recyclerView, dx, dy);

        final int totalItemCount = mLayoutManager.getItemCount();
        final int lastVisibleItemPosition = ((LinearLayoutManager) mLayoutManager).findLastVisibleItemPosition();

        if (totalItemCount < mPrevTotalItemCount) {
            mCurrentPage = 0;
            mPrevTotalItemCount = totalItemCount;

            if (totalItemCount == 0) {
                mIsLoading = true;
            }
        }

        if (mIsLoading && (totalItemCount > mPrevTotalItemCount)) {
            mIsLoading = false;
            mPrevTotalItemCount = totalItemCount;
        }

        if (!mIsLoading && (lastVisibleItemPosition + DEFAULT_ITEM_COUNT_BEFORE_LOAD_MORE) > totalItemCount) {
            mCurrentPage++;
            mOnLoadMoreListener.onLoadMore(recyclerView, mCurrentPage);
            mIsLoading = true;
        }
    }

    public interface OnLoadMoreListener {

        void onLoadMore(RecyclerView recyclerView, int nextPage);
    }
}
