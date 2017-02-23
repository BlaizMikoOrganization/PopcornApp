package com.blaizmiko.popcornapp.ui.fragments.movies;

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
import com.blaizmiko.popcornapp.presentation.presenters.movies.LoadProgressPresenter;
import com.blaizmiko.popcornapp.presentation.views.movies.LoadProgressView;
import com.blaizmiko.ui.listeners.LoadMoreListener;
import com.blaizmiko.popcornapp.presentation.presenters.movies.NowMoviesPresenter;
import com.blaizmiko.popcornapp.presentation.presenters.movies.PopularMoviesPresenter;
import com.blaizmiko.popcornapp.presentation.presenters.movies.TopMoviesPresenter;
import com.blaizmiko.popcornapp.presentation.presenters.movies.UpcomingMoviesPresenter;
import com.blaizmiko.popcornapp.presentation.views.movies.NowMoviesView;
import com.blaizmiko.popcornapp.presentation.views.movies.PopularMoviesView;
import com.blaizmiko.popcornapp.presentation.views.movies.TopMoviesView;
import com.blaizmiko.popcornapp.presentation.views.movies.UpcomingMoviesView;
import com.blaizmiko.popcornapp.ui.adapters.TileAdapter;
import com.blaizmiko.popcornapp.ui.fragments.base.BaseMvpFragment;

import java.util.List;

import butterknife.BindView;

public class MoviesFragment extends BaseMvpFragment implements LoadMoreListener.Loader, LoadProgressView, NowMoviesView, PopularMoviesView, TopMoviesView, UpcomingMoviesView {

    public static MoviesFragment newInstance() {
        return new MoviesFragment();
    }

    @InjectPresenter
    NowMoviesPresenter mNowMoviesPresenter;
    @InjectPresenter
    PopularMoviesPresenter mPopularMoviesPresenter;
    @InjectPresenter
    TopMoviesPresenter mTopRatedMoviesPresenter;
    @InjectPresenter
    UpcomingMoviesPresenter mUpcomingMoviesPresenter;
    @InjectPresenter
    LoadProgressPresenter mLoadProgressPresenter;

    private TileAdapter mNowPlayingMoviesAdapter, mPopularMoviesAdapter, mTopMoviesAdapter, mUpcomingMoviesAdapter;

    //Bind views
    @BindView(R.id.fragment_movies_now_playing_recycler_view)
    protected RecyclerView mNowMoviesRecyclerView;
    @BindView(R.id.fragment_movies_top_movies_recycler_view)
    protected RecyclerView mTopRatedMoviesRecyclerView;
    @BindView(R.id.fragment_movies_popular_movies_recycler_view)
    protected RecyclerView mPopularMoviesRecyclerView;
    @BindView(R.id.fragment_movies_progress_bar)
    protected ProgressBar mProgressBar;
    @BindView(R.id.fragment_movies_upcoming_movies_recycler_view)
    protected RecyclerView mUpcomingMoviesRecyclerView;

    //Life cycle
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    //Init methods
    @Override
    protected void bindViews() {
        initAdapters();

        mNowMoviesPresenter.loadNowMoviesList();
        mPopularMoviesPresenter.loadPopularMoviesList();
        mTopRatedMoviesPresenter.loadTopRatedMoviesList();
        mUpcomingMoviesPresenter.loadUpcomingMoviesList();
    }

    private void initAdapters(){
        final Context context = getActivity().getApplicationContext();

        mNowPlayingMoviesAdapter = initAdapter(context, mNowMoviesRecyclerView, LinearLayoutManager.HORIZONTAL, TileAdapter.TileType.HORIZONTAL_TILE);
        mPopularMoviesAdapter = initAdapter(context, mPopularMoviesRecyclerView, LinearLayoutManager.HORIZONTAL, TileAdapter.TileType.VERTICAL_TILE);
        mTopMoviesAdapter = initAdapter(context, mTopRatedMoviesRecyclerView, LinearLayoutManager.HORIZONTAL, TileAdapter.TileType.VERTICAL_TILE);
        mUpcomingMoviesAdapter = initAdapter(context, mUpcomingMoviesRecyclerView, LinearLayoutManager.HORIZONTAL, TileAdapter.TileType.VERTICAL_TILE);
    }

    private TileAdapter initAdapter(final Context context, final RecyclerView recyclerView, final int layoutManagerType, final TileAdapter.TileType tileType) {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, layoutManagerType, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        final TileAdapter adapter = new TileAdapter(context, tileType);
        recyclerView.setAdapter(adapter);
        LoadMoreListener loadMoreListener = new LoadMoreListener(this);
        recyclerView.addOnScrollListener(loadMoreListener);
        return adapter;
    }

    //Movies View
    public void startLoad() {
        mLoadProgressPresenter.loadStarted();
        mProgressBar.setVisibility(ProgressBar.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(ProgressBar.GONE);
    }

    @Override
    public void setNowMoviesList(final List<TileAdapter.Item> nowMoviesCells) {
        mNowPlayingMoviesAdapter.add(nowMoviesCells);
        mLoadProgressPresenter.loadFinished();
    }

    @Override
    public void setPopularMoviesList(final List<TileAdapter.Item> popularMoviesCells) {
        mPopularMoviesAdapter.add(popularMoviesCells);
        mLoadProgressPresenter.loadFinished();
    }

    @Override
    public void setTopMoviesList(final List<TileAdapter.Item> topMovies) {
        mTopMoviesAdapter.add(topMovies);
        mLoadProgressPresenter.loadFinished();
    }

    @Override
    public void setUpcomingMoviesList(final List<TileAdapter.Item> upcomingMoviesCells) {
        mUpcomingMoviesAdapter.add(upcomingMoviesCells);
        mLoadProgressPresenter.loadFinished();
    }

    public void onLoadMore (RecyclerView recyclerView) {
        switch (recyclerView.getId()) {
            case R.id.fragment_movies_now_playing_recycler_view:
                mNowMoviesPresenter.loadNowMoviesList();
                break;
            case R.id.fragment_movies_popular_movies_recycler_view:
                mPopularMoviesPresenter.loadPopularMoviesList();
                break;
            case R.id.fragment_movies_top_movies_recycler_view:
                mTopRatedMoviesPresenter.loadTopRatedMoviesList();
                break;
            case R.id.fragment_movies_upcoming_movies_recycler_view:
                mUpcomingMoviesPresenter.loadUpcomingMoviesList();
                break;
        }
    }
}
