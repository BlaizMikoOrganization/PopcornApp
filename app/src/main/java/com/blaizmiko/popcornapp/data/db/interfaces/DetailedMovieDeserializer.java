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
import java.util.List;

import javax.inject.Inject;
public class DetailedMovieDeserializer implements JsonDeserializer<DetailedMovieDBModel> {
    @Inject
    public Database database;
    @Override
    public DetailedMovieDBModel deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context){
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
        //final JsonArray videosArray = movieJsonObject.get(videosProperty).getAsJsonObject().get(videoResultsProperty).getAsJsonArray();
        //final JsonArray genresArray = movieJsonObject.get(genresProperty).getAsJsonArray();

        final Type imageListType = new TypeToken<List<ImageDBModel>>(){}.getType();
        final List<ImageDBModel> posterList = gson.fromJson(postersArray, imageListType);
        final List<ImageDBModel> backdropList = gson.fromJson(backdropsArray, imageListType);


        //final Type videoListType = new TypeToken<List<VideoDBModel>>(){}.getType();
        //final List<VideoDBModel> videoList = gson.fromJson(videosArray, videoListType);

        //final Type genresListType = new TypeToken<List<GenreDBModel>>(){}.getType();
        //final List<GenreDBModel> genreList = gson.fromJson(genresArray, genresListType);

        detailedMovieDBModel.addPosters(posterList);
        detailedMovieDBModel.addBackdrops(backdropList);
        //detailedMovieDBModel.addVideos(videoList);
        //detailedMovieDBModel.addGenres(genreList);
        return detailedMovieDBModel;
    }


}
