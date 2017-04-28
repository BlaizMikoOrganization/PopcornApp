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
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ActorJobCinemasAdapter extends BaseAdapter<ActorJobCinemasAdapter.ViewHolder> {

    private Context context;
    private final List<ActorJobCinemasAdapter.CinemaItem> items;

    public ActorJobCinemasAdapter(final Context context) {
        this.context = context;
        items = new ArrayList<>();
    }

    @Override
    public ActorJobCinemasAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_actor_cinemas_item, parent, false);
        return new ActorJobCinemasAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ActorJobCinemasAdapter.ViewHolder holder, final int position) {
        holder.cinemaNameTextView.setText(items.get(position).getTitle());
        Glide.with(context)
                .load(Constants.MovieDbApi.BASE_HIGH_RES_IMAGE_URL + items.get(position).getPosterPath())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.posterImageView);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public ActorJobCinemasAdapter.CinemaItem getItemByPosition(final int position) {
        return items.get(position);
    }


    public void update(final List<ActorJobCinemasAdapter.CinemaItem> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.image_view_adapter_actor_cinemas_item_poster)
        protected ImageView posterImageView;
        @BindView(R.id.text_view_adapter_actor_cinemas_cinema_name)
        protected TextView cinemaNameTextView;

        public ViewHolder(final View view) {
            super(view);
            ButterKnife.bind(this, view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(final View v) {
            itemClickListener.onItemClick(v, getAdapterPosition(), ActorJobCinemasAdapter.this);
        }
    }

    public static class CinemaItem {
        private int id;
        private String posterPath;
        private String title;

        public CinemaItem(final int id, final String title, final String posterPath) {
            this.posterPath = posterPath;
            this.title = title;
            this.id = id;
        }

        public String getPosterPath() {
            return posterPath;
        }

        public String getTitle() {
            return title;
        }

        public int getId() {
            return id;
        }
    }
}
