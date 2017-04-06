package com.blaizmiko.popcornapp.ui.all.presentation.similarCinemas;

import com.arellomobile.mvp.MvpPresenter;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.data.models.cinema.Cinema;
import com.blaizmiko.popcornapp.ui.all.adapters.TileAdapter;

import java.util.ArrayList;
import java.util.List;

public class SimilarCinemasPresenter extends MvpPresenter<SimilarCinemasView>{

    public SimilarCinemasPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    public void parseSimilarCinemas(final List<Cinema> similarCinemas){
        final ArrayList<TileAdapter.Item> tileItems = new ArrayList<>();
        for (Cinema similarMovie : similarCinemas) {
            tileItems.add(new TileAdapter.Item(similarMovie.getId(), similarMovie.getPosterPath(), similarMovie.getTitle(), similarMovie.getVoteAverage(), similarMovie.getBackdropPath(), similarMovie.getPosterPath()));
        }
        getViewState().showSimilarCinemas(tileItems);
    }
}
