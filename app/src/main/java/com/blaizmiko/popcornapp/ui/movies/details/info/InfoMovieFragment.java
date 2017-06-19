package com.blaizmiko.popcornapp.ui.movies.details.info;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.data.db.Database;
import com.blaizmiko.popcornapp.data.db.models.movies.DetailedMovieDBModel;
import com.blaizmiko.popcornapp.data.db.models.movies.GenreDBModel;
import com.blaizmiko.popcornapp.data.db.models.movies.ImageDBModel;
import com.blaizmiko.popcornapp.data.db.models.movies.VideoDBModel;
import com.blaizmiko.popcornapp.data.models.rating.RatingModel;
import com.blaizmiko.popcornapp.ui.ActivityNavigator;
import com.blaizmiko.popcornapp.ui.all.adapters.TileAdapter;
import com.blaizmiko.popcornapp.ui.all.fragments.BaseInfoFragment;
import com.blaizmiko.popcornapp.ui.all.presentation.loadprogress.LoadProgressPresenter;
import com.blaizmiko.popcornapp.ui.all.presentation.rating.RatingAdapter;
import com.blaizmiko.ui.listeners.RecyclerViewListeners;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.objectbox.Box;
import io.objectbox.android.AndroidScheduler;
import io.objectbox.query.Query;

public class InfoMovieFragment extends BaseInfoFragment implements InfoMovieView, RecyclerViewListeners.OnItemClickListener {

    public static InfoMovieFragment newInstance(final LoadProgressPresenter progressPresenter) {
        loadProgressPresenter = progressPresenter;
        return new InfoMovieFragment();
    }

    private static LoadProgressPresenter loadProgressPresenter;
    @BindView(R.id.text_view_info_movie_release_date)
    protected TextView releaseDateTextView;
    @BindView(R.id.text_view_info_movie_budget)
    protected TextView budgetTextView;
    @BindView(R.id.text_view_info_movie_revenue)
    protected TextView revenueTextView;
    @BindView(R.id.text_view_info_movie_runtime)
    protected TextView runtimeTextView;
    @BindView(R.id.recycler_view_base_info_ratings)
    protected RecyclerView ratingRecyclerView;

    private long movieId;
    RatingAdapter ratingAdapter;
    @Inject
    Database database;
    @InjectPresenter
    InfoMoviePresenter infoMoviePresenter;


    //Life Cycle Methods
    @Override
    public void onCreate(final Bundle saveInstanceState) {
        BaseApplication.getComponent().inject(this);
        super.onCreate(saveInstanceState);
    }

    //BindViews
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState){
        super.onCreateView(inflater, container, R.layout.fragment_info_movies);
        return inflater.inflate(R.layout.fragment_info_movies, container, false);
    }

    @Override
    public void bindViews() {
        initBaseAdapters();

        movieId = getArguments().getLong(Constants.Extras.ID);
        Log.d("movieIn2", ""+movieId);
        ratingAdapter = new RatingAdapter();
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        ratingRecyclerView.setLayoutManager(layoutManager);
        ratingRecyclerView.setAdapter(ratingAdapter);
        infoMoviePresenter.loadMovieInfo(movieId);
    }

    public void updateMovieExtras(final DetailedMovieDBModel movie) {
        setStoryLineView(movie.getOverview());

        cinemaName = movie.getTitle();
        cinemaReleaseDate = movie.getReleaseDate();

/*        trailersAdapter.update(movie.getVideos());
        photosAdapter.update(movie.getBackdrops());
        genresTagsAdapter.update(movie.getGenres());*/
        //similarCinemasPresenter.parseSimilarCinemas(movie.getSimilarMovies().getMovies());
        ratingPresenter.loadMovieRating(movie.getImdbId());

        final Box detailedMovieDBModelBox = database.getBoxForDetailedMovies();
        final Query<DetailedMovieDBModel> query = detailedMovieDBModelBox.query().build();
        query.subscribe().on(AndroidScheduler.mainThread()).observer(data ->
            pish(data));

        final Box videoBox = database.getBoxForVideos();
        List<VideoDBModel> list = (List<VideoDBModel>)videoBox.getAll();
        for (VideoDBModel vd : list) {
            Log.d("tag", ""+vd.getName());
        }

        final Box genreBox = database.getBoxForGenres();
        List<GenreDBModel> list2 = (List<GenreDBModel>) genreBox.getAll();

        final Box imageBox = database.getBoxForImages();
        List<ImageDBModel> list3 = (List<ImageDBModel>) imageBox.getAll();

        Log.d("Images", ""+list3.size());


        final Query<VideoDBModel> query2 = detailedMovieDBModelBox.query().build();
        query.subscribe().on(AndroidScheduler.mainThread()).observer(data ->
                pish(data));
    }


    private void pish(final List<DetailedMovieDBModel> data) {
        for (DetailedMovieDBModel movie : data) {
            Log.d("Movie", "Title "+movie.getTitle());
            Log.d("Videos", "Size " +movie.getVideos().size());
            Log.d("Posters", "Size " +movie.getPosters().size());
            Log.d("Backdrops", "Size " +movie.getBackdrops().size());
            Log.d("Genres", "Size " +movie.getGenres().size());
        }
    }

    //Info Movie Presenter
    public void showSimilarMovies(List<TileAdapter.Item> items) {
        similarAdapter.update(items);
    }

    public void showFormattedReleaseDate(String releaseDate) {
        releaseDateTextView.setText(releaseDate);
    }

    public void showFormattedRuntime(String runtime) {
        runtimeTextView.setText(runtime);
    }

    public void showFormattedBudget(String money) {
        budgetTextView.setText(money);
    }

    public void showFormattedRevenue(String money) {
        revenueTextView.setText(money);
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

    //RatingMovieResponse presenter
    @Override
    public void showFullRating(List<RatingModel> ratings) {
        ratingPresenter.addMovieDbRatingToRatingsList(ratings, getArguments().getDouble(Constants.Extras.RATING));
        ratingAdapter.update(ratings);
    }

    //Listeners
    @Override
    public void onItemClick(View view, int position, RecyclerView.Adapter adapter) {
        super.onItemClick(view, position, adapter);
        switch (view.getId()) {
            case R.id.vertical_tile_item:
                final TileAdapter.Item item = ((TileAdapter) adapter).getItemByPosition(position);
                ActivityNavigator.startDetailsMovieActivity(getActivity(),
                        item.getId(),
                        item.getTitle(),
                        item.getBackdropUrl(),
                        item.getRating());
                break;
        }
    }
}
