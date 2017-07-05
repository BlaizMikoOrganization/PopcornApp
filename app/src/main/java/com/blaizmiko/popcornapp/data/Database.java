package com.blaizmiko.popcornapp.data;

import android.content.Context;
import com.blaizmiko.popcornapp.data.db.models.cast.Cast;
import com.blaizmiko.popcornapp.data.db.models.movies.DetailedMovieDBModel;
import com.blaizmiko.popcornapp.data.db.models.movies.MoviesResponseDBModel;
import java.util.List;
import io.realm.Realm;
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
                .first()
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
        final Realm realm = Realm.getDefaultInstance();
        final Observable<DetailedMovieDBModel> movie = realm.where(DetailedMovieDBModel.class)
                .equalTo(DetailedMovieDBModel.COLUMN_ID, id)
                .findAllAsync()
                .asObservable()
                .first()
                .map(detailedMovieDBModels -> detailedMovieDBModels.first());
        realm.close();
        return movie;
    }

    //------------------------------ Movie Cast ---------------------------------------------------
    //---------------------------------------------------------------------------------------------



    public void putCasts(final List<Cast> casts, final long movieId) {
        final Realm realm = Realm.getDefaultInstance();

        final DetailedMovieDBModel movie = realm.where(DetailedMovieDBModel.class)
                .equalTo(DetailedMovieDBModel.COLUMN_ID, movieId)
                .findFirst();

        realm.executeTransaction(bgRealm -> {
            movie.getCasts().addAll(casts);
            bgRealm.copyToRealmOrUpdate(movie);
        });
        realm.close();
    }

    public Observable<List<Cast>> getCasts(final long movieId) {
        return  realm.where(DetailedMovieDBModel.class)
                .equalTo(DetailedMovieDBModel.COLUMN_ID, movieId)
                .findAllAsync()
                .asObservable()
                .first()
                .map(detailedMovieDBModels -> detailedMovieDBModels.first().getCasts());
    }
}


