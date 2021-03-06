package com.blaizmiko.popcornapp.ui.actors.details.cinemas;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.data.models.actors.cinemascredits.ActorCinemaCastModel;
import com.blaizmiko.popcornapp.data.models.actors.cinemascredits.ActorCinemaCrewModel;
import com.blaizmiko.popcornapp.ui.all.adapters.ActorJobAdapter;
import com.blaizmiko.popcornapp.ui.all.adapters.ActorJobCinemasAdapter;
import com.blaizmiko.popcornapp.ui.all.fragments.BaseMvpFragment;
import com.blaizmiko.popcornapp.ui.all.presentation.loadprogress.LoadProgressPresenter;
import com.blaizmiko.ui.listeners.RecyclerViewListeners;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class CinemasActorFragment extends BaseMvpFragment implements CinemasActorView, RecyclerViewListeners.OnItemClickListener{

    @BindView(R.id.recycler_view_actor_movies_acting)
    protected RecyclerView actingMoviesRecyclerView;
    protected ProgressBar progressBar;

    @InjectPresenter
    public CinemasActorPresenter cinemasActorPresenter;

    private ActorJobAdapter jobsAdapter;
    public static String TITLE = "";

    public static LoadProgressPresenter loadProgressPresenter;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        progressBar = ButterKnife.findById(getActivity(), R.id.progress_bar);
        return inflater.inflate(R.layout.fragment_actor_movies, container, false);
    }

    @Override
    protected void bindViews() {
        final Context context = getActivity().getApplicationContext();
        jobsAdapter = new ActorJobAdapter(context);
        jobsAdapter.setItemClickListener(this);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        actingMoviesRecyclerView.setLayoutManager(linearLayoutManager);
        actingMoviesRecyclerView.setNestedScrollingEnabled(true);
        actingMoviesRecyclerView.setAdapter(jobsAdapter);
    }

    @Override
    public void showCrewCinemas(final List<ActorCinemaCrewModel> crewList) {
        final List<ActorJobCinemasAdapter.CinemaItem> cinemaItems = new ArrayList<>();
        for (ActorCinemaCrewModel actorMovieCrewModel: crewList) {
            cinemaItems.add(new ActorJobCinemasAdapter.CinemaItem(actorMovieCrewModel.getId(), actorMovieCrewModel.getTitle(), actorMovieCrewModel.getPosterPath()));
        }
        jobsAdapter.add(new ActorJobAdapter.JobGroupItem(cinemaItems, crewList.get(0).getJob()));
    }

    @Override
    public void showCastCinemas(final List<ActorCinemaCastModel> castList) {
        final List<ActorJobCinemasAdapter.CinemaItem> cinemaItems = new ArrayList<>();
        for (ActorCinemaCastModel castModel: castList) {
            cinemaItems.add(new ActorJobCinemasAdapter.CinemaItem(castModel.getId(), castModel.getTitle(), castModel.getPosterPath()));
        }
        final String castJob = "Acting";
        jobsAdapter.add(new ActorJobAdapter.JobGroupItem(cinemaItems, castJob));
    }

    @Override
    public void finishLoad() {
        loadProgressPresenter.hideProgress();
    }

    @Override
    public void showError() {
        Toast.makeText(getActivity().getApplicationContext(), "Sorry, an error occurred while establish server connection", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startLoad() {
        loadProgressPresenter.showProgress();
    }
}
