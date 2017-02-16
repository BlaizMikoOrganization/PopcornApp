package com.blaizmiko.popcornapp.ui.fragments.actors;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
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
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import butterknife.BindView;

public class ActorsFragment extends BaseMvpFragment implements ActorsView {

    public static ActorsFragment newInstance() {
        return new ActorsFragment();
    }

    @InjectPresenter
    ActorsPresenter mActorsPresenter;

    private PopularActorsAdapter mPopularActorsAdapter;

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

        mPopularActorsAdapter = new PopularActorsAdapter(context);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        mActorsRecyclerView.setLayoutManager(linearLayoutManager);
        mActorsRecyclerView.setHasFixedSize(true);
        mActorsRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(context)
                .colorResId(R.color.colorDivider)
                .sizeResId(R.dimen.spacing_1)
                .marginResId(R.dimen.list_content_left_spacing, R.dimen.spacing_0)
                .build());
        mActorsRecyclerView.setAdapter(mPopularActorsAdapter);
    }

    //ActorsView
    @Override
    public void showProgress() {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgress() {
        if (mProgressBar != null) {
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
