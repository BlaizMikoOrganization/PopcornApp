package com.blaizmiko.popcornapp.ui.tvshows.details.cast;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.ui.ActivityNavigator;
import com.blaizmiko.popcornapp.ui.all.fragments.BaseCastFragment;
import com.blaizmiko.popcornapp.ui.all.presentation.cast.CastPresenter;

public class CastTvShowFragment extends BaseCastFragment{

    public static CastTvShowFragment newInstance() {
        return new CastTvShowFragment();
    }

    @InjectPresenter
    CastPresenter castTvShowPresenter;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle bundle) {
        return super.onCreateView(inflater, container);
    }

    @Override
    protected void bindViews() {
        initBaseAdapters();
        castTvShowPresenter.loadTvShowCast(id);
    }

    @Override
    public void onItemClick(final View view, final int position, final RecyclerView.Adapter adapter) {
        super.onItemClick(view, position, adapter);
    }
}
