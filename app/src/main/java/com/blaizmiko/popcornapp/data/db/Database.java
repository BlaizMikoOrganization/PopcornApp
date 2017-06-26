package com.blaizmiko.popcornapp.data.db;

import android.content.Context;
import android.util.Log;

import com.blaizmiko.popcornapp.data.db.interfaces.movies.IDetailedMovieDBConsumer;
import com.blaizmiko.popcornapp.data.db.interfaces.movies.IMovieResponseDBConsumer;
import com.blaizmiko.popcornapp.data.db.models.movies.DetailedMovieDBModel;
import com.blaizmiko.popcornapp.data.db.models.movies.MoviesResponseDBModel;
import java.util.List;
import io.realm.Realm;
import io.realm.RealmResults;

public class Database {
    private Realm realm;

    private final int NOW_PLAYING_RESPONSE_ID = 1;
    private final int POPULAR_RESPONSE_ID = 2;
    private final int TOP_RESPONSE_ID = 3;
    private final int UPCOMING_RESPONSE_ID = 4;

    private final String TAG = "Database";

    public Database(final Context context) {
        Realm.init(context);
        realm = Realm.getDefaultInstance();
    }

    //------------------------------ Movie Responses ----------------------------------------------
    //---------------------------------------------------------------------------------------------
    public void putNowPlayingMovies(final List<DetailedMovieDBModel> nowPlayingMovies, final int currentPage) {
        final long id = generateIdForMovieResponse(NOW_PLAYING_RESPONSE_ID, currentPage);
        putMovieResponse(nowPlayingMovies, id);
    }

    public void putPopularMovies(final List<DetailedMovieDBModel> popularMovies, final int currentPage) {
        final long id = generateIdForMovieResponse(POPULAR_RESPONSE_ID, currentPage);
        putMovieResponse(popularMovies, id);
    }

    public void putTopMovies(final List<DetailedMovieDBModel> topMovies, final int currentPage) {
        final long id = generateIdForMovieResponse(TOP_RESPONSE_ID, currentPage);
        putMovieResponse(topMovies, id);
    }

    public void putUpcomingMovies(final List<DetailedMovieDBModel> upcomingMovies, final int currentPage) {
        final long id = generateIdForMovieResponse(UPCOMING_RESPONSE_ID, currentPage);
        putMovieResponse(upcomingMovies, id);
    }

    private void putMovieResponse(final List<DetailedMovieDBModel> moviesList, final long id) {
        realm.executeTransactionAsync(bgRealm -> {
            final MoviesResponseDBModel response = new MoviesResponseDBModel(id);
            response.getMovies().addAll(moviesList);
            bgRealm.copyToRealmOrUpdate(response);
        }, null, null);
    }

    public void getNowPlayingMovies(final IMovieResponseDBConsumer dbConsumer, final int currentPage) {
        final long id = generateIdForMovieResponse(NOW_PLAYING_RESPONSE_ID, currentPage);
        getMovieResponse(id, dbConsumer);
    }

    public void getPopularMovies(final IMovieResponseDBConsumer dbConsumer, final int currentPage) {
        final long id = generateIdForMovieResponse(POPULAR_RESPONSE_ID, currentPage);
        getMovieResponse(id, dbConsumer);
    }

    public void getTopMovies(final IMovieResponseDBConsumer dbConsumer, final int currentPage) {
        final long id = generateIdForMovieResponse(TOP_RESPONSE_ID, currentPage);
        getMovieResponse(id, dbConsumer);
    }

    public void getUpcomingMovies(final IMovieResponseDBConsumer dbConsumer, final int currentPage) {
        final long id = generateIdForMovieResponse(UPCOMING_RESPONSE_ID, currentPage);
        getMovieResponse(id, dbConsumer);
    }

    public void getMovieResponse(final long id, final IMovieResponseDBConsumer dbConsumer) {
        final RealmResults<MoviesResponseDBModel> results = realm.where(MoviesResponseDBModel.class)
                .equalTo(MoviesResponseDBModel.COLUMN_ID, id)
                .findAllAsync();

        results.addChangeListener(moviesResponseDBModels -> {
            MoviesResponseDBModel movieResponse = new MoviesResponseDBModel();
            if (!moviesResponseDBModels.isEmpty()) {
                movieResponse = moviesResponseDBModels.first();
            }
            dbConsumer.transferData(movieResponse);
        });
    }

    private long generateIdForMovieResponse(final int movieResponseId, final int page) {
        return Long.valueOf(movieResponseId+""+page);
    }

    //------------------------------ Detailed Movies ----------------------------------------------
    //---------------------------------------------------------------------------------------------

    public void putDetailedMovie(final DetailedMovieDBModel detailedMovie) {
        Log.d(TAG, "putting");
        Log.d(TAG, ""+detailedMovie);
        realm.executeTransactionAsync(bgRealm ->
            bgRealm.copyToRealmOrUpdate(detailedMovie), null, null);
    }

    public void getDetailedMovie(final long id, final IDetailedMovieDBConsumer dbConsumer) {
        RealmResults<DetailedMovieDBModel> results = realm.where(DetailedMovieDBModel.class)
            .equalTo(DetailedMovieDBModel.COLUMN_ID, id)
            .findAllAsync();

        results.addChangeListener(detailedMovies -> {
            for (DetailedMovieDBModel detailedMovieDBModel : detailedMovies) {
                Log.d(TAG, ""+detailedMovieDBModel);
            }
            dbConsumer.transferData(detailedMovies.first());});
    }
}
