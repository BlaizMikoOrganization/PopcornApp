package com.blaizmiko.popcornapp.ui.tvshows.details.info;

import android.content.Context;
import android.icu.text.IDNA;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.data.models.tvshows.detailed.SeasonTvShowModel;
import com.blaizmiko.popcornapp.ui.all.adapters.BaseAdapter;
import com.blaizmiko.ui.listeners.RecyclerViewListeners;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoSeasonsAdapter extends BaseAdapter <InfoSeasonsAdapter.ViewHolder> {

    private List<SeasonTvShowModel> seasons;
    private Context context;
    public InfoSeasonsAdapter(Context context) {
        seasons = new ArrayList<>();
        this.context = context;
    }


    @Override
    public InfoSeasonsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_seasons_tv_shows_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(InfoSeasonsAdapter.ViewHolder holder, int position) {
        Glide.with(context)
                .load(Constants.MovieDbApi.BASE_HIGH_RES_IMAGE_URL + seasons.get(position).getPosterPath())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.posterImageView);

        holder.seasonTextView.setText(Integer.toString(seasons.get(position).getSeasonNumber()));
        holder.airDateTextView.setText(seasons.get(position).getReleaseDate());
    }

    @Override
    public int getItemCount() {
        return seasons.size();
    }

    public SeasonTvShowModel getItemByPosition(int position) {
        return seasons.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.image_view_seasons_tv_shows_poster)
        ImageView posterImageView;
        @BindView(R.id.text_view_seasons_tv_shows_season)
        TextView seasonTextView;
        @BindView(R.id.text_view_seasons_tv_shows_air_date)
        TextView airDateTextView;


        public ViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v, getAdapterPosition(), InfoSeasonsAdapter.this);
        }
    }

    public void update(List<SeasonTvShowModel> seasons) {
        this.seasons = seasons;
    }
}
