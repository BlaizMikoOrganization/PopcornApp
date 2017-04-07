package com.blaizmiko.popcornapp.ui.all.presentation.similarCinemas;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.data.models.cinema.BaseCinemaModel;
import com.blaizmiko.popcornapp.ui.all.adapters.TileAdapter;

import java.util.ArrayList;
import java.util.List;

@InjectViewState
public class SimilarCinemasPresenter extends MvpPresenter<SimilarCinemasView>{

    public SimilarCinemasPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    public void parseSimilarCinemas(final List<? extends BaseCinemaModel> similarCinemas){
        final ArrayList<TileAdapter.Item> tileItems = new ArrayList<>();
        for (BaseCinemaModel similar : similarCinemas) {
            tileItems.add(new TileAdapter.Item(similar.getId(), similar.getPosterPath(), similar.getTitle(), similar.getVoteAverage(), similar.getBackdropPath(), similar.getPosterPath()));
        }
        getViewState().showSimilarCinemas(tileItems);
    }
}
