package com.blaizmiko.popcornapp.ui.all.presentation.genretags;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.data.db.models.genres.GenreDBModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GenresTagsAdapter extends RecyclerView.Adapter<GenresTagsAdapter.ViewHolder> {

    private final List<GenreDBModel> genres;
    public GenresTagsAdapter() {
        genres = new ArrayList<>();
    }


    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_genre_tag_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.genreTagTextView.setText(genres.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return genres.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_view_adapter_info_genre_name)
        protected TextView genreTagTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //Public methods
    public void update(List<GenreDBModel> genres) {
        this.genres.clear();
        this.genres.addAll(genres);
        notifyDataSetChanged();
    }
}
