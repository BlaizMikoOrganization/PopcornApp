package com.blaizmiko.popcornapp.ui.tvshows.episodes;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.data.models.seasons.EpisodeModel;
import com.blaizmiko.popcornapp.ui.all.activities.BaseMvpActivity;
import com.blaizmiko.popcornapp.ui.all.presentation.loadprogress.LoadProgressPresenter;
import com.blaizmiko.popcornapp.ui.all.presentation.loadprogress.LoadProgressView;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.List;

import butterknife.BindView;

public class SeasonTvShowTvShowActivity extends BaseMvpActivity implements LoadProgressView, SeasonTvShowView {

    @InjectPresenter
    SeasonTvShowPresenter seasonTvShowPresenter;
    @InjectPresenter
    LoadProgressPresenter loadProgressPresenter;

    @BindView(R.id.recycler_view_episodes)
    protected RecyclerView episodesRecyclerView;
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;
    @BindView(R.id.progress_bar)
    protected ProgressBar progressBar;

    private SeasonTvShowAdapter seasonTvShowAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_season_tv_show);
    }
    @Override
    protected void bindViews() {
        setToolbar(toolbar);
        setToolbarTitle(getIntent().getStringExtra(Constants.Extras.TITLE));
        setToolbarDisplayHomeButtonEnabled(true);
        initAdapter();

        seasonTvShowPresenter.loadEpisodes(
                getIntent().getIntExtra(Constants.Extras.ID, Constants.MovieDbApi.DEFAULT_CINEMA_ID),
                getIntent().getIntExtra(Constants.Extras.SEASON_NUMBER, Constants.MovieDbApi.DEFAULT_SEASON_NUMBER));
    }

    private void initAdapter() {
        Context context = getApplicationContext();
        LinearLayoutManager episodesLayoutManager = new LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL, false);
        seasonTvShowAdapter = new SeasonTvShowAdapter(context,
                getIntent().getIntExtra(Constants.Extras.SEASON_NUMBER, Constants.MovieDbApi.DEFAULT_SEASON_NUMBER));
        episodesRecyclerView.setLayoutManager(episodesLayoutManager);
        episodesRecyclerView.setAdapter(seasonTvShowAdapter);
        episodesRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(context)
                .colorResId(R.color.colorDivider)
                .sizeResId(R.dimen.spacing_1)
                .build());
    }

    @Override
    public void startLoad() {
        loadProgressPresenter.showProgress();
    }

    @Override
    public void finishLoad() {
        loadProgressPresenter.hideProgress();
    }

    public void setEpisodeInfo(List<EpisodeModel> episodes) {
        seasonTvShowAdapter.update(episodes);
    }

    //Load Progress Presenter
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

    //presenter
    public void showError() {
        Toast.makeText(getApplicationContext(), "Sorry, an error occurred while establish server connection", Toast.LENGTH_SHORT).show();
    }
}
