package com.blaizmiko.popcornapp.ui.movies.details.cast;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.ui.all.interfaces.CastView;
import com.blaizmiko.popcornapp.ui.all.fragments.BaseCastFragment;

public class CastMovieFragment extends BaseCastFragment implements CastView {

    public static CastMovieFragment newInstance() {
        return new CastMovieFragment();
    }

    @InjectPresenter
    CastMoviePresenter castMoviePresenter;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void bindViews() {
        initBaseAdapters();
        castMoviePresenter.loadCast(id);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle bundle) {
        return super.onCreateView(inflater, container);
    }
}