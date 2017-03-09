package com.blaizmiko.popcornapp.ui.all.adapters;

import android.support.v7.widget.RecyclerView;

import com.blaizmiko.ui.listeners.RecyclerViewListeners;

public abstract class BaseAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected RecyclerViewListeners.OnItemClickListener itemClickListener;

    public void setItemClickListener(final RecyclerViewListeners.OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
