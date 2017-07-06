package com.blaizmiko.popcornapp.data;

import android.content.Context;
import android.util.Log;

import com.blaizmiko.popcornapp.data.db.models.cast.Cast;
import com.blaizmiko.popcornapp.data.db.models.movies.DetailedMovieDBModel;
import com.blaizmiko.popcornapp.data.db.models.movies.MoviesResponseDBModel;
import com.blaizmiko.popcornapp.data.db.models.movies.ReviewDBModel;
import com.google.gson.Gson;

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
        Log.d("starting", "here");
        realm.executeTransactionAsync(bgRealm -> {
            //Log.d("here", "here1");
            //bgRealm.copyToRealmOrUpdate(movie);
            bgRealm.createOrUpdateObjectFromJson(DetailedMovieDBModel.class, DetailedMovieDBModel.getInfoJsonString(movie));
        });
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

        realm.executeTransactionAsync(bgRealm -> {
            movie.getCasts().addAll(casts);
            bgRealm.createOrUpdateObjectFromJson(DetailedMovieDBModel.class, DetailedMovieDBModel.getReviewsJsonString(movie));
        });
        realm.close();
    }

    public Observable<List<Cast>> getCasts(final long movieId) {
        return  realm.where(DetailedMovieDBModel.class)
                .equalTo(DetailedMovieDBModel.COLUMN_ID, movieId)
                .findAllAsync()
                .asObservable()
                .filter(movie -> movie.isLoaded())
                .first()
                .map(detailedMovieDBModels -> detailedMovieDBModels.first().getCasts());
    }

    //------------------------------ Movie Reviews ------------------------------------------------
    //---------------------------------------------------------------------------------------------

    public void putReviews(final List<ReviewDBModel> reviews, final long movieId) {
        final Realm realm = Realm.getDefaultInstance();
        final DetailedMovieDBModel movie = realm.where(DetailedMovieDBModel.class)
                .equalTo(DetailedMovieDBModel.COLUMN_ID, movieId)
                .findFirst();

        Log.d("starting", "here3");

        realm.executeTransactionAsync(bgRealm -> {
            movie.getReviews().addAll(reviews);

            String pish = DetailedMovieDBModel.getReviewsJsonString(movie);
            Log.d("look_at_this", ""+pish);
            bgRealm.createOrUpdateObjectFromJson(DetailedMovieDBModel.class, pish);
        });

        RealmResults<DetailedMovieDBModel> results = realm.where(DetailedMovieDBModel.class)
                .findAll();
        for (DetailedMovieDBModel movie2 : results) {
            Log.d("movie", ""+movie2);
        }
        realm.close();
    }

    public Observable<List<ReviewDBModel>> getReviews(final long movieId) {
        return realm.where(DetailedMovieDBModel.class)
                .equalTo(DetailedMovieDBModel.COLUMN_ID, movieId)
                .findAllAsync()
                .asObservable()
                .map(movie -> {
                    Log.d("detailedMovieGG", ""+movie);
                    return movie;
                })
                .filter(detailedMovieDBModels -> detailedMovieDBModels.isLoaded() && !detailedMovieDBModels.isEmpty())
                .first()
                .map(movie -> movie.first().getReviews());
    }

}


