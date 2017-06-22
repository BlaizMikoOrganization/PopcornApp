package com.blaizmiko.popcornapp.data.db.interfaces;

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
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class DetailedMovieDeserializer implements JsonDeserializer<DetailedMovieDBModel> {
    @Inject
    public Database database;

    @Override
    public DetailedMovieDBModel deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) {
        final Gson gson = new Gson();
        final JsonObject movieJsonObject = json.getAsJsonObject();
        final Type detailedMovieType = new TypeToken<DetailedMovieDBModel>() {}.getType();
        final DetailedMovieDBModel movie = gson.fromJson(json, detailedMovieType);

        final List<ImageDBModel> posters = parsePosters(gson, movieJsonObject);
        final List<ImageDBModel> backdrops = parseBackdrops(gson, movieJsonObject);
        final List<GenreDBModel> genres = parseGenres(gson, movieJsonObject);
        final List<VideoDBModel> videos = parseVideos(gson, movieJsonObject);
        final List<DetailedMovieDBModel> similars = parseSimilars(gson, movieJsonObject);

        movie.addPosters(posters);
        movie.addBackdrops(backdrops);
        movie.addGenres(genres);
        movie.addVideos(videos);
        movie.getSimilars().addAll(similars);
        return movie;
    }

    private List<DetailedMovieDBModel> parseSimilars(final Gson gson, final JsonObject parent) {
        final String SIMILAR_PROPERTY = "similar";
        final String RESULTS_PROPERTY = "results";
        final Type movieType = new TypeToken<List<DetailedMovieDBModel>>(){}.getType();
        final JsonObject similarParent = parent.getAsJsonObject(SIMILAR_PROPERTY);
        return (List<DetailedMovieDBModel>) parseList(gson, similarParent, movieType, RESULTS_PROPERTY);
    }

    private List<?> parseList(final Gson gson, final JsonObject parent, final Type listType, final String property) {
        List<?> resultList;
        try {
            final JsonArray resultJsonArray = parent.get(property).getAsJsonArray();
            resultList = gson.fromJson(resultJsonArray, listType);
        } catch (final NullPointerException ex) {
            resultList = new ArrayList<>();
        }
        return resultList;
    }

    private List<VideoDBModel> parseVideos(final Gson gson, final JsonObject parent) {
        final String RESULTS_PROPERTY = "results";
        final String VIDEOS_PROPERTY = "videos";
        final Type videoListType = new TypeToken<List<VideoDBModel>>() {}.getType();
        final JsonObject videoParent = parent.getAsJsonObject(VIDEOS_PROPERTY);
        return (List<VideoDBModel>) parseList(gson, videoParent, videoListType, RESULTS_PROPERTY);
    }

    private List<GenreDBModel> parseGenres(final Gson gson, final JsonObject parent) {
        final String GENRES_PROPERTY = "genres";
        final Type genresListType = new TypeToken<List<GenreDBModel>>() {}.getType();
        return (List<GenreDBModel>) parseList(gson, parent, genresListType, GENRES_PROPERTY);
    }

    private List<ImageDBModel> parseImages(final Gson gson, final JsonObject parent, final String property) {
        final String IMAGES_PROPERTY = "images";
        List<ImageDBModel> images;
        try {
            final JsonObject imagesParent = parent.get(IMAGES_PROPERTY).getAsJsonObject();
            final Type imageListType = new TypeToken<List<ImageDBModel>>() {}.getType();
            images = (List<ImageDBModel>) parseList(gson, imagesParent, imageListType, property);
            return images;
        } catch (final NullPointerException ex) {
            images = new ArrayList<>();
        }
        return images;
    }

    private List<ImageDBModel> parseBackdrops(final Gson gson, final JsonObject parent) {
        final String BACKDROPS_PROPERTY = "backdrops";
        return parseImages(gson, parent, BACKDROPS_PROPERTY);
    }

    private List<ImageDBModel> parsePosters(final Gson gson, final JsonObject parent) {
        final String POSTERS_PROPERTY = "posters";
        return parseImages(gson, parent, POSTERS_PROPERTY);
    }
}
