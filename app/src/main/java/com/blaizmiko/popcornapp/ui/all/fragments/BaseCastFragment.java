package com.blaizmiko.popcornapp.ui.all.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.data.models.cast.CastModel;
import com.blaizmiko.popcornapp.ui.all.adapters.BaseCastAdapter;
import com.blaizmiko.popcornapp.ui.all.interfaces.CastView;
import com.blaizmiko.popcornapp.ui.all.presentation.loadprogress.LoadProgressPresenter;
import com.blaizmiko.popcornapp.ui.all.presentation.loadprogress.LoadProgressView;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.List;

import butterknife.BindView;

public abstract class BaseCastFragment extends BaseMvpFragment implements LoadProgressView, CastView {
    @BindView(R.id.recycler_view_cast)
    protected RecyclerView castRecyclerView;

    public final static String TITLE = "Cast";
    protected BaseCastAdapter baseCastAdapter;
    protected int id;
    protected ProgressBar progressBar;

    @InjectPresenter
    LoadProgressPresenter loadProgressPresenter;


    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getArguments().getInt(Constants.Extras.ID);
    }

    public View onCreateView(final LayoutInflater inflater, final ViewGroup container) {
        return inflater.inflate(R.layout.fragment_cast, container, false);
    }

    public void initBaseAdapters() {
        Context context = getActivity().getApplicationContext();
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        baseCastAdapter = new BaseCastAdapter(context);
        castRecyclerView.setAdapter(baseCastAdapter);
        castRecyclerView.setLayoutManager(linearLayoutManager);
        castRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(context)
                .colorResId(R.color.colorDivider)
                .sizeResId(R.dimen.spacing_1)
                .marginResId(R.dimen.spacing_list_content_left, R.dimen.spacing_0)
                .build());
    }

    //Load Progress Presenter
    @Override
    public void showProgress() {
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgress() {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }

    public void finishLoad() {
        loadProgressPresenter.hideProgress();
    }

    public void startLoad() {
        loadProgressPresenter.showProgress();
    }

    //Case presenter
    @Override
    public void setCast(final List<CastModel> cast) {
        baseCastAdapter.update(cast);
    }

    public void showError() {
        Toast.makeText(getActivity().getApplicationContext(), "Sorry, an error occurred while establish server connection", Toast.LENGTH_SHORT).show();
    }
}
