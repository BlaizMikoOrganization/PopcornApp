package com.blaizmiko.popcornapp.ui.all.presentation.details.info.movie;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.data.models.moviesNew.BaseMovieModel;
import com.blaizmiko.popcornapp.data.models.moviesNew.DetailedMovieModel;
import com.blaizmiko.popcornapp.ui.all.adapters.TileAdapter;

import java.util.List;

public interface InfoMovieView extends MvpView{
    void setMovieInfo(DetailedMovieModel movie);

    void setSimilarMoviesAdapter(List<TileAdapter.Item> movie);
    void setFormattedReleaseDate(String releaseDate);
    void setFormattedRuntime(String runtime);
    void setFormattedBudget(String budget);
    void setFormattedRevenue(String revenue);

    void finishLoad();
    void showError();
    void startLoad();
}
