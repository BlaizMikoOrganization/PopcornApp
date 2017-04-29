package com.blaizmiko.popcornapp.ui.tvshows.details.cast;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.ui.all.fragments.BaseCastFragment;
import com.blaizmiko.popcornapp.ui.all.presentation.cast.CastPresenter;
import com.blaizmiko.popcornapp.ui.all.presentation.loadprogress.LoadProgressPresenter;

public class CastTvShowFragment extends BaseCastFragment{

    public static CastTvShowFragment newInstance(LoadProgressPresenter progressPresenter) {
        loadProgressPresenter = progressPresenter;
        return new CastTvShowFragment();
    }

    @InjectPresenter
    CastPresenter castTvShowPresenter;
    private static LoadProgressPresenter loadProgressPresenter;

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
        castTvShowPresenter.loadTvShowCast(id);
    }

    @Override
    public void onItemClick(View view, int position, RecyclerView.Adapter adapter) {
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