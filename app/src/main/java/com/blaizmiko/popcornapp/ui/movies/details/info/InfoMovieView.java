package com.blaizmiko.popcornapp.ui.movies.details.info;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.data.models.movies.DetailedMovieModel;
import com.blaizmiko.popcornapp.ui.all.adapters.TileAdapter;

import java.util.List;

public interface InfoMovieView extends MvpView{
    void updateMovieExtras(DetailedMovieModel movie);

    void showSimilarMovies(List<TileAdapter.Item> movie);
    void showFormattedReleaseDate(String releaseDate);
    void showFormattedRuntime(String runtime);
    void showFormattedBudget(String budget);
    void showFormattedRevenue(String revenue);

    void finishLoad();
    void showError();
    void startLoad();
}
