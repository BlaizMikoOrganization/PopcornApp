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
import com.blaizmiko.popcornapp.common.utils.FormatUtil;
import com.blaizmiko.popcornapp.common.utils.StringUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;

import java.sql.SQLOutput;
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
        int layoutId = R.layout.adapter_vertical_tile_item;
        if (tileType == TileType.HORIZONTAL_TILE) {
            layoutId = R.layout.adapter_horizontal_tile_item;
        }

        final View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TileAdapter.ViewHolder holder, final int position) {
        holder.titleTextView.setText(items.get(position).getTitle());
        Glide.with(context)
                .load(items.get(position).getImageUrl())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.posterImageView);

        final int zeroRating = 0;

        if (items.get(position).getRating() <= zeroRating) {
            holder.voteRatingBar.setVisibility(View.GONE);
            holder.voteTextView.setText(StringUtil.NOT_RELEASED_STRING);
            return;
        }
        holder.voteRatingBar.setVisibility(View.VISIBLE);
        holder.voteRatingBar.setRating((float) items.get(position).getRating());
        holder.voteTextView.setText(items.get(position).getRatingAsString());
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
            if (itemClickListener != null) {
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
        private final int id;
        private final String imageUrl;
        private final String title;
        private final double rating;
        private final String backdropUrl;
        private final String posterUrl;

        public Item() {
            id = 0;
            imageUrl = StringUtil.EMPTY_STRING;
            title = StringUtil.EMPTY_STRING;
            rating = 0;
            backdropUrl = StringUtil.EMPTY_STRING;
            posterUrl = StringUtil.EMPTY_STRING;
        }

        public Item(final int id, final String imageUrl, final String title, final double rating, final String backdropUrl, final String posterUrl) {
            this.id = id;
            this.imageUrl = Constants.MovieDbApi.BASE_HIGH_RES_IMAGE_URL + imageUrl;
            this.title = title;
            this.rating = rating;
            this.backdropUrl = backdropUrl;
            this.posterUrl = posterUrl;
        }

        public int getId() {
            return id;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public String getTitle() {
            return title;
        }

        public double getRating() {
            return FormatUtil.roundToOneDecimal(FormatUtil.fromTenToFivePointScale(rating));
        }

        public String getBackdropUrl() {
            return backdropUrl;
        }

        public String getPosterUrl() {
            return posterUrl;
        }

        String getRatingAsString() {
            return String.valueOf(rating);
        }
    }

    public enum TileType {
        VERTICAL_TILE, HORIZONTAL_TILE
    }
}
