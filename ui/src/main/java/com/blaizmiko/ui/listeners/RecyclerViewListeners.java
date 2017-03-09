package com.blaizmiko.ui.listeners;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public class RecyclerViewListeners {

    public interface OnLoadMoreListener {
        void onLoadMore(RecyclerView recyclerView, int nextPage);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, RecyclerView.Adapter adapter);
    }
}
