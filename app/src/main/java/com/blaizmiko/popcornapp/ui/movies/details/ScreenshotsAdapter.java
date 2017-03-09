package com.blaizmiko.popcornapp.ui.movies.details;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.data.models.movies.Image;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScreenshotsAdapter extends RecyclerView.Adapter<ScreenshotsAdapter.ViewHolder>{

    private List<Image> mScreenshots;
    private Context mContext;

    public ScreenshotsAdapter(Context context) {
        mContext = context;
        mScreenshots = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_movie_details_screenshot_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(mContext)
                .load(Constants.TheMovieDbApi.BaseHighResImageUrl + mScreenshots.get(position).getFilePath())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.screenshotImageView);
    }

    @Override
    public int getItemCount() {
        return mScreenshots.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adapter_movie_details_screenshot_image_view)
        ImageView screenshotImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //Public methods
    public void update(List<Image> images) {
        mScreenshots.clear();
        mScreenshots.addAll(images);
        notifyDataSetChanged();
    }
}
