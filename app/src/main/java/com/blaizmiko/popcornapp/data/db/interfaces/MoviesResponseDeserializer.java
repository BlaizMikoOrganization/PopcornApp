package com.blaizmiko.popcornapp.data.db.interfaces;

import android.graphics.Movie;
import android.util.Log;

import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.data.db.Database;
import com.blaizmiko.popcornapp.data.db.models.movies.DetailedMovieDBModel;
import com.blaizmiko.popcornapp.data.db.models.movies.MoviesResponseDBModel;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

/*
public class MoviesResponseDeserializer implements JsonDeserializer<MoviesResponseDBModel>{

    @Inject
    Database database;

    @Override
    public MoviesResponseDBModel deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        BaseApplication.getComponent().inject(this);

        Log.d("responseDes", "aiser");
        final String RESULTS_PROPERTY = "results";

        final JsonObject moviesResponseJson = json.getAsJsonObject();
        final Gson gson = new Gson();

        final Type moviesResponseType = new TypeToken<MoviesResponseDBModel>(){}.getType();
        final MoviesResponseDBModel moviesResponse = gson.fromJson(json, moviesResponseType);

        database.putNowPlayingMovies(moviesResponse);

        final JsonArray moviesArray = moviesResponseJson.get(RESULTS_PROPERTY).getAsJsonArray();
        final Type moviesType = new TypeToken<DetailedMovieDBModel>(){}.getType();
        final List<DetailedMovieDBModel> movies = gson.fromJson(moviesArray, moviesType);

        Log.d("tag", ""+movies.size());
        establishRelationsForMovies(movies, moviesResponse);

        return moviesResponse;
    }

    private void establishRelationsForMovies(final List<DetailedMovieDBModel> movies, final MoviesResponseDBModel parent) {
        for (DetailedMovieDBModel movie : movies) {
            Log.d("another movie", ""+movie.getTitle());
            movie.setMoviesResponseDBModel(parent);
            movie.setMoviesResponseDBModelId(parent.getId());
        }
        database.putDetailedMovies(movies);
    }
}
*/
