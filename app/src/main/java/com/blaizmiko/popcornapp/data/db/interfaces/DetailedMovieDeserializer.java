package com.blaizmiko.popcornapp.data.db.interfaces;

import android.util.Log;

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

public class DetailedMovieDeserializer implements JsonDeserializer<DetailedMovieDBModel> {

    @Override
    public DetailedMovieDBModel deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context){
        Log.d("her", "pishher");
        final String imagesProperty = "images";
        final String videosProperty = "videos";
        final String genresProperty = "genres";
        final String backdropsProperty = "backdrops";
        final String postersProperty = "posters";
        final String videoResultsProperty = "results";


        final Gson gson = new Gson();
        final JsonObject movieJsonObject = json.getAsJsonObject();
        final DetailedMovieDBModel detailedMovieDBModel = gson.fromJson(movieJsonObject, DetailedMovieDBModel.class);

        final JsonObject imagesJsonObject = movieJsonObject.get(imagesProperty).getAsJsonObject();

        final JsonArray backdropsArray = imagesJsonObject.get(backdropsProperty).getAsJsonArray();
        final JsonArray postersArray = imagesJsonObject.get(postersProperty).getAsJsonArray();
        //final JsonArray videosArray = movieJsonObject.get(videosProperty).getAsJsonObject().get(videoResultsProperty).getAsJsonArray();
        final JsonArray genresArray = movieJsonObject.get(genresProperty).getAsJsonArray();

        final Type imageListType = new TypeToken<List<ImageDBModel>>(){}.getType();
        final List<ImageDBModel> posterList = gson.fromJson(postersArray, imageListType);
        final List<ImageDBModel> backdropList = gson.fromJson(backdropsArray, imageListType);

        final Type videoListType = new TypeToken<List<VideoDBModel>>(){}.getType();
        //final List<VideoDBModel> videoDBModels = gson.fromJson(videosArray, videoListType);

        final Type genresListType = new TypeToken<List<GenreDBModel>>(){}.getType();
        final List<GenreDBModel> genreList = gson.fromJson(genresArray, genresListType);

        //detailedMovieDBModel.setBackdrops(backdropList);
        //detailedMovieDBModel.setPosters(posterList);
        //detailedMovieDBModel.setVideos(videoList);
        //detailedMovieDBModel.setGenres(genreList);
        return null;
    }
}
