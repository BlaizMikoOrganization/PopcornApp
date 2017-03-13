package com.blaizmiko.popcornapp.ui.all.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.utils.AppUtil;
import com.blaizmiko.popcornapp.common.utils.StringUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TileAdapter extends BaseAdapter<TileAdapter.ViewHolder> {

    private final Context context;
    private final List<Item> items;
    private final TileType tileType;

    public TileAdapter(final Context context, final TileType tileType) {
        this.context = context;
        items = new ArrayList<>();
        this.tileType = tileType;
    }

    @Override
    public TileAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final int layoutId;

        if (tileType == TileType.HORIZONTAL_TILE) {
            layoutId = R.layout.adapter_horizontal_tile_item;
        } else {
            layoutId = R.layout.adapter_vertical_tile_item;
        }

        final View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TileAdapter.ViewHolder holder, final int position) {
        holder.titleTextView.setText(items.get(position).getTitle());
        holder.voteRatingBar.setRating((float) items.get(position).getRating());
        holder.voteTextView.setText(items.get(position).getRatingAsString());

        Glide.with(context)
                .load(items.get(position).getImageUrl())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.posterImageView);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.image_view_adapter_tile_item_poster)
        ImageView posterImageView;

        @BindView(R.id.text_view_adapter_tile_item_title)
        TextView titleTextView;

        @BindView(R.id.rating_bar_adapter_tile_item_rating)
        SimpleRatingBar voteRatingBar;

        @BindView(R.id.text_view_adapter_tile_item_rating)
        TextView voteTextView;

        ViewHolder(final View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(final View view) {
            if(itemClickListener != null) {
                itemClickListener.onItemClick(view, getAdapterPosition(), TileAdapter.this);
            }
        }
    }

    //Public methods
    public void update(final Collection<Item> list) {
        items.clear();
        items.addAll(list);
        notifyDataSetChanged();
    }

    public void add(final Collection<Item> newItems) {
        items.addAll(newItems);
        notifyDataSetChanged();
    }

    public Item getItemByPosition(final int position) {
        if (items.isEmpty()) {
            return new Item();
        }

        return items.get(position);
    }

    public static class Item {

        private final int mId;
        private final String mImageUrl;
        private final String mTitle;
        private final double mRating;

        public Item() {
            mId = 0;
            mImageUrl = StringUtil.EMPTY_STRING;
            mTitle = StringUtil.EMPTY_STRING;
            mRating = 0;
        }

        public Item(final int id, final String imageUrl, final String title, final double rating) {
            mId = id;
            mImageUrl = Constants.TheMovieDbApi.BaseHighResImageUrl + imageUrl;
            mTitle = title;
            mRating = rating;
        }

        public int getId() {
            return mId;
        }

        String getImageUrl() {
            return mImageUrl;
        }

        String getTitle() {
            return mTitle;
        }

        double getRating() {
            return AppUtil.roundToOneDecimal(mRating, AppUtil.ApiRatingToAppRating);
        }

        String getRatingAsString() {
            return String.valueOf(mRating);
        }
    }

    public enum TileType {
        VERTICAL_TILE, HORIZONTAL_TILE
    }
}
