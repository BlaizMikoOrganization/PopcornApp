package com.blaizmiko.popcornapp.ui.movies.details.cast;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.ui.all.fragments.BaseCastFragment;
import com.blaizmiko.popcornapp.ui.all.presentation.cast.CastPresenter;
import com.blaizmiko.popcornapp.ui.all.presentation.cast.CastView;
import com.blaizmiko.popcornapp.ui.all.presentation.loadprogress.LoadProgressPresenter;
import com.blaizmiko.ui.listeners.RecyclerViewListeners;

public class CastMovieFragment extends BaseCastFragment implements CastView, RecyclerViewListeners.OnItemClickListener {

    public static CastMovieFragment newInstance(final LoadProgressPresenter progressPresenter) {
        loadProgressPresenter = progressPresenter;
        return new CastMovieFragment();
    }
    private static LoadProgressPresenter loadProgressPresenter;
    @InjectPresenter
    CastPresenter castMoviePresenter;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void bindViews() {
        initBaseAdapters();
        castMoviePresenter.loadMovieCast(id);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle bundle) {
        return super.onCreateView(inflater, container);
    }

    @Override
    public void onItemClick(final View view, final int position, final RecyclerView.Adapter adapter) {
        super.onItemClick(view, position, adapter);
    }

    @Override
    public void finishLoad() {
        loadProgressPresenter.hideProgress();
    }

    @Override
    public void startLoad() {
        loadProgressPresenter.showProgress();
    }
}