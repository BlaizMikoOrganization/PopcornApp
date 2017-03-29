package com.blaizmiko.popcornapp.ui.tvshows.episodes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.data.models.seasons.EpisodeModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SeasonTvShowAdapter extends RecyclerView.Adapter<SeasonTvShowAdapter.ViewHolder>{
    private Context context;
    private int seasonNumber;
    private List<EpisodeModel> episodes;

    public SeasonTvShowAdapter(final Context context, int seasonNumber) {
        this.context = context;
        this.seasonNumber = seasonNumber;
        episodes = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SeasonTvShowAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_episode_item, parent, false));
    }

    @Override
    public void onBindViewHolder(SeasonTvShowAdapter.ViewHolder holder, int position) {
        holder.nameTextView.setText(episodes.get(position).getName());
        holder.overviewTextView.setText(episodes.get(position).getOverview());
        holder.releaseDateTextView.setText(episodes.get(position).getAirDate());
        holder.numberTextView.setText(Integer.toString(episodes.get(position).getEpisodeNumber()));
        holder.seasonNumber.setText(Integer.toString(seasonNumber));
        Glide.with(context)
                .load(Constants.MovieDbApi.BASE_HIGH_RES_IMAGE_URL + episodes.get(position).getBackdrop())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.pictureImageView);
    }

    @Override
    public int getItemCount() {
        return episodes.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.image_view_episode_picture)
        ImageView pictureImageView;
        @BindView(R.id.text_view_episode_overview)
        TextView overviewTextView;
        @BindView(R.id.text_view_episode_name)
        TextView nameTextView;
        @BindView(R.id.text_view_episode_release_date)
        TextView releaseDateTextView;
        @BindView(R.id.text_view_episode_number)
        TextView numberTextView;
        @BindView(R.id.text_view_episode_season_number)
        TextView seasonNumber;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void update(List<EpisodeModel> episodes){
        this.episodes.clear();
        this.episodes.addAll(episodes);
        notifyDataSetChanged();
    }
}
