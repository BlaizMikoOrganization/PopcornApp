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

    private final Context mContext;
    private final List<Item> mItems;
    private final TileType mTileType;

    public TileAdapter(final Context context, final TileType tileType) {
        mContext = context;
        mItems = new ArrayList<>();
        mTileType = tileType;
    }

    @Override
    public TileAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final int layoutId;

        if (mTileType == TileType.HORIZONTAL_TILE) {
            layoutId = R.layout.adapter_horizontal_tile_item;
        } else {
            layoutId = R.layout.adapter_vertical_tile_item;
        }

        final View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TileAdapter.ViewHolder holder, final int position) {
        holder.titleTextView.setText(mItems.get(position).getTitle());
        holder.voteRatingBar.setRating((float) mItems.get(position).getRating());
        holder.voteTextView.setText(mItems.get(position).getRatingAsString());

        Glide.with(mContext)
                .load(mItems.get(position).getImageUrl())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.posterImageView);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.adapter_tile_poster_image_view)
        ImageView posterImageView;

        @BindView(R.id.adapter_tile_title_text_view)
        TextView titleTextView;

        @BindView(R.id.adapter_tile_vote_rating_bar)
        SimpleRatingBar voteRatingBar;

        @BindView(R.id.adapter_tile_vote_text_view)
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
        mItems.clear();
        mItems.addAll(list);
        notifyDataSetChanged();
    }

    public void add(final Collection<Item> newItems) {
        mItems.addAll(newItems);
        notifyDataSetChanged();
    }

    public Item getItemByPosition(final int position) {
        if (mItems.isEmpty()) {
            return new Item();
        }

        return mItems.get(position);
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
