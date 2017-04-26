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

public class ActorCinemasGridAdapter extends BaseAdapter<ActorCinemasGridAdapter.ViewHolder> {
    private Context context;
    private final List<Item> items;

    public ActorCinemasGridAdapter(final Context context) {
        this.context = context;
        items = new ArrayList<>();
    }

    @Override
    public ActorCinemasGridAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_actor_cinemas, parent, false);
        return new ActorCinemasGridAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ActorCinemasGridAdapter.ViewHolder holder, final int position) {
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


    public void update(final List<Item> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.image_view_adapter_actor_cinemas_poster)
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
            itemClickListener.onItemClick(v, getAdapterPosition(), ActorCinemasGridAdapter.this);
        }
    }


    public static class Item {
        private String title;
        private String posterPath;

        public Item(final String title, final String posterPath) {
            this.title = title;
            this.posterPath = posterPath;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPosterPath() {
            return posterPath;
        }

        public void setPosterPath(String posterPath) {
            this.posterPath = posterPath;
        }
    }

}
