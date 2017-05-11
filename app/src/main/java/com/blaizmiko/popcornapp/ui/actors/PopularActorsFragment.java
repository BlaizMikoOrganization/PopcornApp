package com.blaizmiko.popcornapp.ui.actors;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.data.models.actors.popular.PopularActorModel;
import com.blaizmiko.popcornapp.data.models.actors.popular.PopularActorsResponse;
import com.blaizmiko.popcornapp.ui.ActivityNavigator;
import com.blaizmiko.popcornapp.ui.all.fragments.BaseMvpFragment;
import com.blaizmiko.ui.listeners.RecyclerViewListeners;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import butterknife.BindView;

public class PopularActorsFragment extends BaseMvpFragment implements PopularActorsView, RecyclerViewListeners.OnItemClickListener {

    public static PopularActorsFragment newInstance() {
        return new PopularActorsFragment();
    }

    //Bind views
    @BindView(R.id.recycler_view_fragment_popular_actors)
    protected RecyclerView actorsRecyclerView;
    @BindView(R.id.progress_bar_fragment_popular_actors)
    protected ProgressBar progressBar;
    @InjectPresenter
    PopularActorsPresenter popularActorsPresenter;
    private PopularActorsAdapter popularActorsAdapter;

    //Life cycle
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_popular_actors, container, false);
    }

    //Init methods
    @Override
    protected void bindViews() {
        initAdapter();
        popularActorsPresenter.loadActorsList();
    }

    private void initAdapter() {
        final Context context = getActivity().getApplicationContext();

        popularActorsAdapter = new PopularActorsAdapter(context);
        popularActorsAdapter.setItemClickListener(this);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        actorsRecyclerView.setLayoutManager(linearLayoutManager);
        actorsRecyclerView.setHasFixedSize(true);
        actorsRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(context)
                .colorResId(R.color.colorDivider)
                .sizeResId(R.dimen.spacing_1)
                .marginResId(R.dimen.spacing_list_content_left, R.dimen.spacing_0)
                .build());
        actorsRecyclerView.setAdapter(popularActorsAdapter);
    }

    //PopularActors Presenter
    @Override
    public void showProgress() {
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgress() {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError() {
    }

    @Override
    public void showActorsList(final PopularActorsResponse popularResponse) {
        popularActorsAdapter.update(popularResponse.getPopularActors());
    }


    //Listeners
    @Override
    public void onItemClick(final View view, final int position, final RecyclerView.Adapter adapter) {
        switch (view.getId()) {
            case R.id.adapter_popular_actor_item_root_view:
                final PopularActorModel clickedActor = ((PopularActorsAdapter) adapter).getItemByPosition(position);
                ActivityNavigator.startDetailsActorActivity(getActivity(),
                        clickedActor.getId(),
                        clickedActor.getName(),
                        clickedActor.getProfileImageUrl());
        }
    }
}
