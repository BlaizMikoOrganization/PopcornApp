package com.blaizmiko.popcornapp.ui.all.presentation.details.seasons;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.ui.all.fragments.BaseMvpFragment;

import butterknife.BindView;

public class SeasonsTvShowFragment extends BaseMvpFragment {

    public static SeasonsTvShowFragment newInstance() {
        return new SeasonsTvShowFragment();
    }



    @BindView(R.id.recycler_view_seasons_tv_shows_seasons)
    RecyclerView seasonsRecyclerView;

    SeasonsTvShowAdapter seasonsAdapter;


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_seasons_tv_shows, container, false);
    }

    @Override
    protected void bindViews() {
        initAdapters();
    }

    private void initAdapters() {
        Context context = getActivity().getApplicationContext();
        LinearLayoutManager seasonsLayoutManger = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        seasonsAdapter = new SeasonsTvShowAdapter(context);
        seasonsRecyclerView.setLayoutManager(seasonsLayoutManger);
        seasonsRecyclerView.setLayoutManager(seasonsLayoutManger);
    }
}
