package com.blaizmiko.popcornapp.ui.actors.details.cinemas.movies;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.ui.ActivityNavigator;
import com.blaizmiko.popcornapp.ui.actors.details.cinemas.CinemasActorFragment;
import com.blaizmiko.popcornapp.ui.all.adapters.ActorJobCinemasAdapter;
import com.blaizmiko.popcornapp.ui.all.presentation.loadprogress.LoadProgressPresenter;

public class MoviesActorFragment extends CinemasActorFragment {
    public static MoviesActorFragment newInstance(final LoadProgressPresenter progressPresenter) {
        loadProgressPresenter = progressPresenter;
        return new MoviesActorFragment();
    }

    public static final String TITLE = "Movies";

    @Override
    protected void bindViews() {
        super.bindViews();
        cinemasActorPresenter.loadActorMovies(getArguments().getInt(Constants.Extras.ID));
    }

    @Override
    public void onItemClick(final View view, final int position, final RecyclerView.Adapter adapter) {
        final ActorJobCinemasAdapter.CinemaItem item = ((ActorJobCinemasAdapter) adapter).getItemByPosition(position);
        ActivityNavigator.startDetailsMovieActivity(getActivity().getApplicationContext(),
                item.getId(),
                item.getTitle());
    }
}
