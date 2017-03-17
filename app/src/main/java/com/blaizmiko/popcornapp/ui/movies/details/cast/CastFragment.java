package com.blaizmiko.popcornapp.ui.movies.details.cast;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.data.models.cast.Cast;
import com.blaizmiko.popcornapp.ui.all.fragments.BaseMvpFragment;
import com.blaizmiko.popcornapp.ui.all.presentation.cast.CastAdapter;
import java.util.List;
import butterknife.BindView;

public class CastFragment extends BaseMvpFragment implements CastView{
    private int movieId = 0;
    public static final String TITLE = "Cast";

    @InjectPresenter
    public CastPresenter castPresenter;

    @BindView(R.id.pish)
    RecyclerView actorsRecyclerView;

    //private Context context;
    private CastAdapter castAdapter;

    public static CastFragment newInstance() {
        return new CastFragment();
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        movieId = getArguments().getInt(Constants.Extras.ID);


    }


    @Override
    protected void bindViews() {
        final Context context = getActivity().getApplicationContext();
        final LinearLayoutManager actorsLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        actorsRecyclerView.setLayoutManager(actorsLayoutManager);
        castAdapter = new CastAdapter(context);
        actorsRecyclerView.setAdapter(castAdapter);

        castPresenter.loadCast(movieId);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_details_cast, container, false);
    }

    @Override
    public void showError() {

    }

    @Override
    public void finishLoad() {

    }

    @Override
    public void startLoad() {

    }
    @Override
    public void setCast(List<Cast> casts) {
        castAdapter.update(casts);
    }
}
