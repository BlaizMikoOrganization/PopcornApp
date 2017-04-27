package com.blaizmiko.popcornapp.ui.actors.details.movies;

import android.content.Context;
import android.os.Bundle;
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
import com.blaizmiko.popcornapp.ui.all.adapters.ActorJobAdapter;
import com.blaizmiko.popcornapp.ui.all.adapters.ActorJobCinemasAdapter;
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
    protected RecyclerView actingMoviesRecyclerView;

    @InjectPresenter
    MoviesActorPresenter moviesActorPresenter;

    ActorJobAdapter jobsAdapter;


    public static final String TITLE = "Movies";

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_actor_movies, container, false);
    }

    @Override
    protected void bindViews() {
        final Context context = getActivity().getApplicationContext();
        jobsAdapter = new ActorJobAdapter(context);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        actingMoviesRecyclerView.setLayoutManager(linearLayoutManager);
        actingMoviesRecyclerView.setAdapter(jobsAdapter);

        moviesActorPresenter.loadMoviesActor(getArguments().getInt(Constants.Extras.ID));
    }

    @Override
    public void showActorMoviesCrew(final List<ActorMovieCrewModel> crewList) {
        final List<ActorJobCinemasAdapter.CinemaItem> cinemaItems = new ArrayList<>();
        for (ActorMovieCrewModel actorMovieCrewModel: crewList) {
            cinemaItems.add(new ActorJobCinemasAdapter.CinemaItem(actorMovieCrewModel.getTitle(), actorMovieCrewModel.getPosterPath()));
        }
        jobsAdapter.add(new ActorJobAdapter.JobGroupItem(cinemaItems, crewList.get(0).getJob()));
    }

    @Override
    public void showActorMoviesCast(final List<ActorMovieCastModel> castList) {
        final List<ActorJobCinemasAdapter.CinemaItem> cinemaItems = new ArrayList<>();
        for (ActorMovieCastModel castModel: castList) {
            cinemaItems.add(new ActorJobCinemasAdapter.CinemaItem(castModel.getTitle(), castModel.getPosterPath()));
        }
        jobsAdapter.add(new ActorJobAdapter.JobGroupItem(cinemaItems, "Acting"));

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
            //case R.id.adapter_actor_root:
                //ActivityNavigator.startDetailsMovieActivity();
        }
    }
}
