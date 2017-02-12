package com.blaizmiko.popcornapp.ui.fragments.actors;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.models.actors.PopularActors;
import com.blaizmiko.popcornapp.presentation.presenters.actors.ActorsPresenter;
import com.blaizmiko.popcornapp.presentation.views.actors.ActorsView;
import com.blaizmiko.popcornapp.ui.adapters.actors.PopularActorsAdapter;
import com.blaizmiko.popcornapp.ui.fragments.base.BaseMvpFragment;

import butterknife.BindView;

public class ActorsFragment extends BaseMvpFragment implements ActorsView {

    public static ActorsFragment newInstance() {
        return new ActorsFragment();
    }

    @InjectPresenter
    ActorsPresenter mActorsPresenter;

    private PopularActorsAdapter mPopularActorsAdapter;

    private final int GRID_SPAN_COUNT = 2;

    //Bind views
    @BindView(R.id.fragment_actors_recycler_view)
    protected RecyclerView mActorsRecyclerView;

    @BindView(R.id.fragment_actors_progress_bar)
    protected ProgressBar mProgressBar;

    //Life cycle
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_actors, container, false);
    }

    //Init methods
    @Override
    protected void bindViews() {
        initAdapter();

        mActorsPresenter.loadActorsList();
    }

    private void initAdapter() {
        final Context context = getActivity().getApplicationContext();

        final GridLayoutManager gridLayoutManager = new GridLayoutManager(context, GRID_SPAN_COUNT);
        mActorsRecyclerView.setLayoutManager(gridLayoutManager);
        mActorsRecyclerView.setHasFixedSize(true);

        mPopularActorsAdapter = new PopularActorsAdapter(context);
        mActorsRecyclerView.setAdapter(mPopularActorsAdapter);
    }

    //ActorsView
    @Override
    public void showProgress() {
        if(mProgressBar != null) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgress() {
        if(mProgressBar != null) {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError() {

    }

    @Override
    public void setActorsList(final PopularActors popularActors) {
        mPopularActorsAdapter.update(popularActors.getPopularActors());
    }

}
