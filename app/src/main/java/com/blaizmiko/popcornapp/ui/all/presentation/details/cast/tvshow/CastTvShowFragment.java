package com.blaizmiko.popcornapp.ui.all.presentation.details.cast.tvshow;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.ui.all.presentation.details.cast.base.BaseCastFragment;

public class CastTvShowFragment extends BaseCastFragment{

    public static CastTvShowFragment newInstance() {
        return new CastTvShowFragment();
    }

    @InjectPresenter
    CastTvShowPresenter castTvShowPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle bundle) {
        return super.onCreateView(inflater, container);
    }

    @Override
    protected void bindViews() {
        initBaseAdapters();

        castTvShowPresenter.loadCast(id);
    }
}
