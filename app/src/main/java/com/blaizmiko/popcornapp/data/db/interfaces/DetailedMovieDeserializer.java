package com.blaizmiko.popcornapp.data.db.interfaces;

import android.util.Log;

import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.data.db.Database;
import com.blaizmiko.popcornapp.data.db.models.movies.DetailedMovieDBModel;
import com.blaizmiko.popcornapp.data.db.models.movies.GenreDBModel;
import com.blaizmiko.popcornapp.data.db.models.movies.ImageDBModel;
import com.blaizmiko.popcornapp.data.db.models.movies.VideoDBModel;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.objectbox.relation.ToMany;

public class DetailedMovieDeserializer implements JsonDeserializer<DetailedMovieDBModel> {
    @Inject
    public Database database;
    @Override
    public DetailedMovieDBModel deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context){
        BaseApplication.getComponent().inject(this);

        final String imagesProperty = "images";
        final String videosProperty = "videos";
        final String genresProperty = "genres";
        final String backdropsProperty = "backdrops";
        final String postersProperty = "posters";
        final String videoResultsProperty = "results";

        final Gson gson = new Gson();
        final JsonObject movieJsonObject = json.getAsJsonObject();
        final Type detailedMovieType = new TypeToken<DetailedMovieDBModel>(){}.getType();
        final DetailedMovieDBModel detailedMovieDBModel = gson.fromJson(json, detailedMovieType);
        final JsonObject imagesJsonObject = movieJsonObject.get(imagesProperty).getAsJsonObject();

        final JsonArray backdropsArray = imagesJsonObject.get(backdropsProperty).getAsJsonArray();
        final JsonArray postersArray = imagesJsonObject.get(postersProperty).getAsJsonArray();
        final JsonArray videosArray = movieJsonObject.get(videosProperty).getAsJsonObject().get(videoResultsProperty).getAsJsonArray();
        final JsonArray genresArray = movieJsonObject.get(genresProperty).getAsJsonArray();

        database.putDetailedMovie(detailedMovieDBModel);

        final Type imageListType = new TypeToken<List<ImageDBModel>>(){}.getType();
        final List<ImageDBModel> posterList = gson.fromJson(postersArray, imageListType);
        final List<ImageDBModel> backdropList = gson.fromJson(backdropsArray, imageListType);
        establishRelationsForImages(posterList, detailedMovieDBModel);
        establishRelationsForImages(backdropList, detailedMovieDBModel);

        final Type videoListType = new TypeToken<List<VideoDBModel>>(){}.getType();
        final List<VideoDBModel> videoList = gson.fromJson(videosArray, videoListType);
        establishRelationsForVideos(videoList, detailedMovieDBModel);

        final Type genresListType = new TypeToken<List<GenreDBModel>>(){}.getType();
        final List<GenreDBModel> genreList = gson.fromJson(genresArray, genresListType);
        establishRelationsForGenres(genreList, detailedMovieDBModel);
        return detailedMovieDBModel;
    }

    private void establishRelationsForImages(final List<ImageDBModel> images, final DetailedMovieDBModel parent) {
        for (final ImageDBModel image : images) {
            image.setDetailedMovieDBModelId(parent.getId());
            image.setDetailedMovieDBModel(parent);
        }
        database.putImageDBModels(images);
    }

    private void establishRelationsForVideos(final List<VideoDBModel> videos, final DetailedMovieDBModel parent) {
        for (final VideoDBModel video: videos) {
            video.setDetailedMovieDBModelId(parent.getId());
            video.setDetailedMovieDBModel(parent);
        }
        database.putVideoDBModels(videos);
    }

    private void establishRelationsForGenres(final List<GenreDBModel> genres, final DetailedMovieDBModel parent) {
        for (final GenreDBModel genre: genres) {
            genre.setDetailedMovieDBModelId(parent.getId());
            genre.setDetailedMovieDBModel(parent);
        }
        database.putGenreDBModels(genres);
    }
}
