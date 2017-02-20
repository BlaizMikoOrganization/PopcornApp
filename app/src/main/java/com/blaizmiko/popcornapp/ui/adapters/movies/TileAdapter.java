package com.blaizmiko.popcornapp.ui.adapters.movies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blaizmiko.popcornapp.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TileAdapter extends RecyclerView.Adapter<TileAdapter.ViewHolder> {

    private final Context mContext;
    private final List<Item> mList;
    private final int mLayoutId;

    public TileAdapter(Context context, int layoutId) {
        mContext = context;
        mLayoutId = layoutId;
        mList = new ArrayList<>();
    }

    @Override
    public TileAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mLayoutId, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TileAdapter.ViewHolder holder, final int position) {
        holder.titleTextView.setText(mList.get(position).mTitle);
        holder.avrVoteRatingBar.setRating(mList.get(position).mAvrVote);
        holder.avrVoteTextView.setText(Float.toString(mList.get(position).mAvrVote));

        Glide.with(mContext)
                .load(mList.get(position).mImagePath)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.posterImageView);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.adapter_tile_poster_image_view)
        ImageView posterImageView;

        @BindView(R.id.adapter_tile_title_text_view)
        TextView titleTextView;

        @BindView(R.id.adapter_tile_rating_rating_bar)
        SimpleRatingBar avrVoteRatingBar;

        @BindView(R.id.adapter_tile_rating_text_view)
        TextView avrVoteTextView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }

    public static class Item {
        private final String mImagePath;
        private final String mTitle;
        private final float mAvrVote;

        public Item(String imagePath, String title, float avrVote) {
            mImagePath = imagePath;
            mTitle = title;
            mAvrVote = avrVote;
        }
    }

    //Public methods
    public void update(final List<Item> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }
}
