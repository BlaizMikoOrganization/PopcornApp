package com.blaizmiko.popcornapp.ui.actors.details.movies;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.data.models.actors.moviecredits.ActorMovieCastModel;
import com.blaizmiko.popcornapp.data.models.actors.moviecredits.ActorMovieCrewModel;
import com.blaizmiko.popcornapp.ui.ActivityNavigator;
import com.blaizmiko.popcornapp.ui.all.adapters.ActorCinemasGridAdapter;
import com.blaizmiko.popcornapp.ui.all.fragments.BaseMvpFragment;
import com.blaizmiko.ui.listeners.RecyclerViewListeners;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MoviesActorFragment extends BaseMvpFragment implements MoviesActorView, RecyclerViewListeners.OnItemClickListener{
    public static MoviesActorFragment newInstance() {
        return new MoviesActorFragment();
    }

    @BindView(R.id.recycler_view_actor_movies_acting)
    protected RecyclerView moviesRecyclerView;

    @InjectPresenter
    MoviesActorPresenter moviesActorPresenter;

    private ActorCinemasGridAdapter actorCinemasGridAdapter;

    public static final String TITLE = "Movies";

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_actor_movies, container, false);
    }

    @Override
    protected void bindViews() {
        final Context context = getActivity().getApplicationContext();
        final GridLayoutManager actorMoviesGridLayoutManager = new GridLayoutManager(context, 3);
        actorCinemasGridAdapter = new ActorCinemasGridAdapter(getActivity().getApplicationContext());
        actorCinemasGridAdapter.setItemClickListener(this);
        moviesRecyclerView.setLayoutManager(actorMoviesGridLayoutManager);
        moviesRecyclerView.setAdapter(actorCinemasGridAdapter);

        moviesActorPresenter.loadMoviesActor(getArguments().getInt(Constants.Extras.ID));
    }

    @Override
    public void showActorMoviesCrew(final List<ActorMovieCrewModel> crewList) {

    }

    @Override
    public void showActorMoviesCast(final List<ActorMovieCastModel> castList) {
        final List<ActorCinemasGridAdapter.Item> items = new ArrayList<>();
        for (ActorMovieCastModel castModel : castList) {
            items.add(new ActorCinemasGridAdapter.Item(castModel.getTitle(), castModel.getPosterPath()));
        }
        actorCinemasGridAdapter.update(items);
    }

    @Override
    public void finishLoad() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void startLoad() {

    }

    @Override
    public void onItemClick(View view, int position, RecyclerView.Adapter adapter) {
        switch(view.getId()) {
            case R.id.adapter_actor_root:
                //ActivityNavigator.startDetailsMovieActivity();
        }
    }
}
