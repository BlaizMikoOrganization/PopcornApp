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

import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.data.db.models.cast.Cast;
import com.blaizmiko.popcornapp.data.models.cast.CastModel;
import com.blaizmiko.popcornapp.ui.ActivityNavigator;
import com.blaizmiko.popcornapp.ui.all.adapters.BaseCastAdapter;
import com.blaizmiko.popcornapp.ui.all.presentation.cast.CastView;
import com.blaizmiko.ui.listeners.RecyclerViewListeners;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.List;

import butterknife.BindView;

public abstract class BaseCastFragment extends BaseMvpFragment implements RecyclerViewListeners.OnItemClickListener, CastView {
    @BindView(R.id.recycler_view_cast)
    protected RecyclerView castRecyclerView;

    public final static String TITLE = "Cast";
    protected BaseCastAdapter baseCastAdapter;
    protected long id;
    protected ProgressBar progressBar;



    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getArguments().getLong(Constants.Extras.ID);
    }

    public View onCreateView(final LayoutInflater inflater, final ViewGroup container) {
        //progressBar = ButterKnife.findById(getActivity(), R.id.progress_bar);
        return inflater.inflate(R.layout.fragment_cast, container, false);
    }

    public void initBaseAdapters() {
        Context context = getActivity().getApplicationContext();
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        baseCastAdapter = new BaseCastAdapter(context);
        baseCastAdapter.setItemClickListener(this);
        castRecyclerView.setAdapter(baseCastAdapter);
        castRecyclerView.setLayoutManager(linearLayoutManager);
        castRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(context)
                .colorResId(R.color.colorDivider)
                .sizeResId(R.dimen.spacing_1)
                .marginResId(R.dimen.spacing_list_content_left, R.dimen.spacing_0)
                .build());
    }
    //Case presenter
    @Override
    public void showCast(final List<Cast> cast) {
        baseCastAdapter.update(cast);
    }

    public void showError() {
        Toast.makeText(getActivity().getApplicationContext(), "Sorry, an error occurred while establish server connection", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(final View view, final int position, final RecyclerView.Adapter adapter) {
        final Cast clickedActor = ((BaseCastAdapter) adapter).getItemByPosition(position);
        ActivityNavigator.startDetailsActorActivity(getActivity(),
            clickedActor.getActor().getId(),
            clickedActor.getActor().getName(),
            clickedActor.getActor().getProfileImageUrl());
    }
}
