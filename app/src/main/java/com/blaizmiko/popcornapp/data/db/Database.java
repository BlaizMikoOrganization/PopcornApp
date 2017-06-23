package com.blaizmiko.popcornapp.data.db;

import android.content.Context;
import android.util.Log;
import com.blaizmiko.popcornapp.data.db.models.movies.DetailedMovieDBModel;
import com.blaizmiko.popcornapp.data.db.models.movies.MoviesResponseDBModel;
import java.util.List;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class Database {
    private Realm realm;

    public Database(final Context context) {
        Realm.init(context);
        realm = Realm.getDefaultInstance();
    }

    public void putNowPlayingMovies(final List<DetailedMovieDBModel> nowPlayingMovies) {
        final int NOW_PLAYING_RESPONSE_ID = 0;
        putMovieResponse(nowPlayingMovies, NOW_PLAYING_RESPONSE_ID);
    }

    public void putPopularMovies(final List<DetailedMovieDBModel> popularMovies) {
        final int POPULAR_RESPONSE_ID = 1;
        putMovieResponse(popularMovies, POPULAR_RESPONSE_ID);
    }

    public void putTopMovies(final List<DetailedMovieDBModel> topMovies) {
        final int TOP_RESPONSE_ID = 2;
        putMovieResponse(topMovies, TOP_RESPONSE_ID);

    }

    public void putUpcomingMovies(final List<DetailedMovieDBModel> upcomingMovies) {
        final int UPCOMING_RESPONSE_ID = 3;
        putMovieResponse(upcomingMovies, UPCOMING_RESPONSE_ID);
    }

    private void putMovieResponse(final List<DetailedMovieDBModel> moviesList, final int id) {
        realm.executeTransactionAsync(bgRealm -> {
            bgRealm.copyToRealmOrUpdate(moviesList);
            final MoviesResponseDBModel response = bgRealm.createObject(MoviesResponseDBModel.class);
            response.getMovies().addAll(moviesList);
            response.setId(id);
            bgRealm.copyToRealmOrUpdate(moviesList);
        }, null, null);
    }

    public void getNowPlayingMovies() {
        RealmResults<MoviesResponseDBModel> results = realm.where(MoviesResponseDBModel.class)
        .findAllAsync();
        results.addChangeListener(new RealmChangeListener<RealmResults<MoviesResponseDBModel>>() {
            @Override
            public void onChange(RealmResults<MoviesResponseDBModel> moviesResponseDBModels) {
                for (MoviesResponseDBModel response : moviesResponseDBModels) {
                    Log.d("response ", ""+response.getMovies().size());
                    Log.d("pish", ""+response);
                }
            }
        });
    }

    //new
    public void putDetailedMovie(final DetailedMovieDBModel detailedMovie) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(detailedMovie);
        realm.commitTransaction();
    }
}
