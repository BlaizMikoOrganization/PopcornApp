package com.blaizmiko.popcornapp.ui.all.presentation.details.info.movie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.data.models.moviesNew.BaseMovieModel;
import com.blaizmiko.popcornapp.data.models.moviesNew.DetailedMovieModel;
import com.blaizmiko.popcornapp.ui.all.adapters.TileAdapter;
import com.blaizmiko.popcornapp.ui.all.presentation.details.info.base.BaseInfoFragment;

import java.util.ArrayList;

import butterknife.BindView;

public class InfoMovieFragment extends BaseInfoFragment implements InfoMovieView{

    public static final String TITLE = "Info";

    @BindView(R.id.text_view_info_release_date)
    TextView releaseDateTextView;
    @BindView(R.id.text_view_info_budget)
    TextView budgetTextView;
    @BindView(R.id.text_view_info_revenue)
    TextView revenueTextView;
    @BindView(R.id.text_view_info_original_name)
    TextView originalNameTextView;
    @BindView(R.id.text_view_info_runtime)
    TextView runtimeTextView;


    public static InfoMovieFragment newInstance() {
        return new InfoMovieFragment();
    }

    private int movieId;
    @InjectPresenter
    InfoMoviePresenter infoMoviePresenter;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        movieId = getArguments().getInt(Constants.Extras.ID);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState){
        super.onCreateView(inflater, container, R.layout.fragment_info);
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    @Override
    public void bindViews() {
        initAdapters();
        infoMoviePresenter.loadMovieInfo(movieId);
    }

    public void setMovieInfo(DetailedMovieModel movie) {
        setInfo(movie);

        infoMoviePresenter.getFormattedBudget(Integer.toString(movie.getBudget()));
        infoMoviePresenter.getFormattedRevenue(Integer.toString(movie.getRevenue()));
        infoMoviePresenter.getFormattedReleaseDate(movie.getReleaseDate());
        infoMoviePresenter.getFormattedRuntime(movie.getRuntime());
        originalNameTextView.setText(movie.getTitle());

        ArrayList<TileAdapter.Item> tileItems = new ArrayList<>();
        for (BaseMovieModel similarMovie : movie.getSimilarMovies().getList()) {
            tileItems.add(new TileAdapter.Item(similarMovie.getId(), similarMovie.getPosterPath(), similarMovie.getTitle(), similarMovie.getVoteAverage(), similarMovie.getBackdropPath(), similarMovie.getPosterPath()));
        }
        similarAdapter.update(tileItems);
    }


    public void setFormattedReleaseDate(String releaseDate) {
        releaseDateTextView.setText(releaseDate);
    }

    public void setFormattedRuntime(String runtime) {
        runtimeTextView.setText(runtime);
    }

    public void setFormattedBudget(String money) {
        budgetTextView.setText(money);
    }

    public void setFormattedRevenue(String money) {
        revenueTextView.setText(money);
    }
}
