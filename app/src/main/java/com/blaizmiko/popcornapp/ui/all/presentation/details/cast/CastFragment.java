package com.blaizmiko.popcornapp.ui.all.presentation.details.cast;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.data.models.cast.Cast;
import com.blaizmiko.popcornapp.ui.all.fragments.BaseMvpFragment;
import com.blaizmiko.popcornapp.ui.all.presentation.loadprogress.LoadProgressPresenter;
import com.blaizmiko.popcornapp.ui.all.presentation.loadprogress.LoadProgressView;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CastFragment extends BaseMvpFragment implements LoadProgressView, CastView{

    public static CastFragment newInstance() {
        return new CastFragment();
    }

    @InjectPresenter
    CastPresenter castPresenter;
    @InjectPresenter
    LoadProgressPresenter loadProgressPresenter;

    private int movieId = 0;
    public static final String TITLE = "Cast";

    //Bind Views
    @BindView(R.id.recycler_view_cast)
    protected RecyclerView castRecyclerView;
    protected ProgressBar progressBar;
    private CastAdapter castAdapter;

    //Life cycle
    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        movieId = getArguments().getInt(Constants.Extras.ID);
    }

    @Override
    protected void bindViews() {
        progressBar = ButterKnife.findById(getActivity(), R.id.progress_bar_details_load);

        final Context context = getActivity().getApplicationContext();
        final LinearLayoutManager actorsLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        castRecyclerView.setLayoutManager(actorsLayoutManager);
        castAdapter = new CastAdapter(context);
        castRecyclerView.setAdapter(castAdapter);

        castPresenter.loadCast(movieId);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cast, container, false);
    }

    @Override
    public void showError() {

    }

    @Override
    public void finishLoad() {
        loadProgressPresenter.hideProgress();
    }

    @Override
    public void startLoad() {
        loadProgressPresenter.showProgress();
    }
    @Override
    public void setCast(List<Cast> casts) {
        castAdapter.update(casts);
    }

    @Override
    public void hideProgress() {
        if (progressBar.getVisibility() != View.GONE) {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showProgress() {
        if (progressBar.getVisibility() != View.VISIBLE) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }
}
