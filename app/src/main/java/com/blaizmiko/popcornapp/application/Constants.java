package com.blaizmiko.popcornapp.application;

import android.provider.ContactsContract;

import com.blaizmiko.popcornapp.common.utils.SymbolUtils;

public class Constants {

    public interface TheMovieDbApi {
        String BasePealUrl = "https://api.themoviedb.org/3/";
        String BaseProfileImageUrl = "https://image.tmdb.org/t/p/w185";
        String BaseHighResImageUrl = "https://image.tmdb.org/t/p/w780";
        String BaseLowResImageUrl = "https://image.tmdb.org/t/p/w185";
        String BaseTrailerUrl = "https://www.youtube.com/watch?v=";
        String BaseTrailerPreviewImageUrl = "https://img.youtube.com/vi/";
        String BaseTrailerPreviewImageHighRes = "/2.jpg";
        String ApiKey = "92e0a05bf5b6e05f60a73954b743558f";
        String Language = "en_US";
        String CreditsAppendToResponse = "credits";
        String ImagesAppendToResponse = "images";
        String VideoAppendToResponse = "videos";
        String IncludeImageLanguage = "en,null";

        String MovieDetailsAppendToResponse = ImagesAppendToResponse + SymbolUtils.COMMA + VideoAppendToResponse + SymbolUtils.COMMA + CreditsAppendToResponse;

        //TestValues
        String NowMovieDefaultRegion = "US";
        int FirstPage = 1;
    }

    public interface OMDbApi {
        String BaseOMDbUrl = "http://www.omdbapi.com/";
        String BaseIncludeTomatoesRating = "true";

    }

    public interface NetworkingConfig {
        int TIMEOUT = 20;
    }

    public interface Bundles {
        String ID = "ID";
    }

}
