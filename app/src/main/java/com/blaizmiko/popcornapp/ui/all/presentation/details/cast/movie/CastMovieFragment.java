package com.blaizmiko.popcornapp.ui.all.presentation.details.cast.movie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.ui.all.presentation.details.cast.base.CastView;
import com.blaizmiko.popcornapp.ui.all.presentation.details.cast.base.BaseCastFragment;

public class CastMovieFragment extends BaseCastFragment implements CastView {

    public static CastMovieFragment newInstance() {
        return new CastMovieFragment();
    }

    @InjectPresenter
    CastMoviePresenter castMoviePresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void bindViews() {
        initBaseAdapters();

        castMoviePresenter.loadCast(id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle bundle) {
        return super.onCreateView(inflater, container);
    }
}