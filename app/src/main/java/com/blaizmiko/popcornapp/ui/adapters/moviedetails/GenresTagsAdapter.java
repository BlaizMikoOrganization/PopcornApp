package com.blaizmiko.popcornapp.ui.adapters.moviedetails;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.models.movies.Genre;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GenresTagsAdapter extends RecyclerView.Adapter<GenresTagsAdapter.ViewHolder> {

    private final List<Genre> mGenres;

    public GenresTagsAdapter() {
        mGenres = new ArrayList<>();
    }


    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_movie_details_genre_tag_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.genreTagTextView.setText(mGenres.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mGenres.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.adapter_movie_details_genre_tag_text_view)
        TextView genreTagTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //Public methods
    public void update(List<Genre> genres) {
        mGenres.clear();
        mGenres.addAll(genres);
        notifyDataSetChanged();
    }
}
