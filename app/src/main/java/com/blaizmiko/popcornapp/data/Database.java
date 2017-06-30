package com.blaizmiko.popcornapp.data;

import android.content.Context;

import com.blaizmiko.popcornapp.data.db.interfaces.movies.IDetailedMovieDBConsumer;
import com.blaizmiko.popcornapp.data.db.models.cast.Cast;
import com.blaizmiko.popcornapp.data.db.models.movies.DetailedMovieDBModel;
import com.blaizmiko.popcornapp.data.db.models.movies.MoviesResponseDBModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;


public class Database {
    private Realm realm;

    private final String TAG = "Database";

    public Database(final Context context) {
        Realm.init(context);
        realm = Realm.getDefaultInstance();
    }

    //------------------------------ Movie Responses ----------------------------------------------
    //---------------------------------------------------------------------------------------------

    public void putMovieResponse(final List<DetailedMovieDBModel> nowPlayingMovies,
                                 final int currentPage,
                                 final int movieResponseId) {
        final long id = generateIdForMovieResponse(movieResponseId, currentPage);
        final Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(bgRealm -> {
            final MoviesResponseDBModel response = new MoviesResponseDBModel(id);
            response.getMovies().addAll(nowPlayingMovies);
            bgRealm.copyToRealmOrUpdate(response);
        }, null, null);
        realm.close();
    }

    public Observable<List<DetailedMovieDBModel>> getMoviesResponse(final int movieResponseId, final int page) {
        final long id = generateIdForMovieResponse(movieResponseId, page);
        final Observable<List<DetailedMovieDBModel>> response = realm.where(MoviesResponseDBModel.class)
                .equalTo(MoviesResponseDBModel.COLUMN_ID, id)
                .findAllAsync()
                .asObservable()
                .map(moviesResponseDBModels -> moviesResponseDBModels.first().getMovies());
        return response;
    }

    private long generateIdForMovieResponse(final int movieResponseId, final int page) {
        return Long.valueOf(movieResponseId + "" + page);
    }

    //------------------------------ Detailed Movies ----------------------------------------------
    //---------------------------------------------------------------------------------------------

    public void putMovie(final DetailedMovieDBModel movie) {
        final Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(bgRealm -> bgRealm.copyToRealmOrUpdate(movie), null, null);
        realm.close();
    }

    public Observable<DetailedMovieDBModel> getMovie(final long id) {
        final Observable<DetailedMovieDBModel> movie = realm.where(DetailedMovieDBModel.class)
                .equalTo(DetailedMovieDBModel.COLUMN_ID, id)
                .findFirstAsync()
                .asObservable();
        return movie;
    }


    public void putCasts(final List<Cast> casts, final long movieId) {
        getMovie(movieId)
            .map(movie -> {
                movie.setCasts(casts);
                putMovie(movie);
                return movie;
            });
    }
}


