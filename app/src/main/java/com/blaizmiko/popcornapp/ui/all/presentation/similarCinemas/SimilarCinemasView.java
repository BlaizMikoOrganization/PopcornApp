package com.blaizmiko.popcornapp.ui.all.presentation.similarCinemas;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.data.models.cinema.Cinema;
import com.blaizmiko.popcornapp.data.models.tvshows.BaseTvShowModel;
import com.blaizmiko.popcornapp.ui.all.adapters.TileAdapter;

import java.util.List;

public interface SimilarCinemasView extends MvpView{
    void showSimilarCinemas(final List<TileAdapter.Item> movie);
}
