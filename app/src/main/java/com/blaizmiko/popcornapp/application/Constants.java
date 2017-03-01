package com.blaizmiko.popcornapp.application;

import android.provider.ContactsContract;

public class Constants {

    public interface Api {
        String BasePealUrl = "https://api.themoviedb.org/3/";
        String BaseProfileImageUrl = "https://image.tmdb.org/t/p/w185";
        String BaseHighResImageUrl = "https://image.tmdb.org/t/p/w780";
        String BaseLowResImageUrl = "https://image.tmdb.org/t/p/w185";
        String BaseTrailerUrl = "https://www.youtube.com/watch?v=";
        String BaseTrailerPreviewImageUrl = "https://img.youtube.com/vi/";
        String BaseTrailerPreviewImageHighRes = "/0.jpg";
        String ApiKey = "92e0a05bf5b6e05f60a73954b743558f";
        String Language = "en_US";
        String CreditsAppendToResponse = "credits";
        String ImagesAppendToResponse = "images";
        String VideoAppendToResponse = "videos";

        String MovieDetailsAppendToResponse = CreditsAppendToResponse + "," +VideoAppendToResponse;
        //String MovieDetailsAppendToResponse = CreditsAppendToResponse + "," + ImagesAppendToResponse +"," +VideoAppendToResponse;

        //TestValues
        String NowMovieDefaultRegion = "US";
        int FirstPage = 1;
    }

    public interface NetworkingConfig {
        int TIMEOUT = 20;
    }

    public interface Bundles {
        String ID = "ID";
    }

}
