package com.blaizmiko.popcornapp.ui.all.presentation.details.seasons;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.data.models.tvshows.detailed.Season;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SeasonsTvShowAdapter extends RecyclerView.Adapter <SeasonsTvShowAdapter.ViewHolder> {

    private List<Season> seasons;
    private Context context;
    public SeasonsTvShowAdapter(Context context) {
        seasons = new ArrayList<>();
        this.context = context;
    }


    @Override
    public SeasonsTvShowAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_seasons_tv_shows_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SeasonsTvShowAdapter.ViewHolder holder, int position) {

        Glide.with(context)
                .load(seasons.get(position).getPosterPath())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.posterImageView);

        holder.seasonTextView.setText(seasons.get(position).getSeasonNumber());
        holder.airDateTextView.setText(seasons.get(position).getReleaseDate());
        holder.episodeCountTextView.setText(seasons.get(position).getEpisodeCount());
    }

    @Override
    public int getItemCount() {
        return seasons.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.image_view_seasons_tv_shows_poster)
        ImageView posterImageView;
        @BindView(R.id.text_view_seasons_tv_shows_season)
        TextView seasonTextView;
        @BindView(R.id.text_view_seasons_tv_shows_air_date)
        TextView airDateTextView;
        @BindView(R.id.text_view_seasons_tv_shows_episode_count)
        TextView episodeCountTextView;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }
}
