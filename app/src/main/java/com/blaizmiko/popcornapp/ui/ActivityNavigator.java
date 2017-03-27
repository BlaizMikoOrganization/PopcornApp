package com.blaizmiko.popcornapp.ui;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.ui.all.activities.SliderGalleryActivity;
import com.blaizmiko.popcornapp.ui.movies.details.BaseDetailsMovieActivity;
import com.blaizmiko.popcornapp.ui.tvshows.details.DetailsTvShowActivity;
import com.blaizmiko.popcornapp.ui.home.HomeActivity;

@SuppressWarnings("StaticMethodOnlyUsedInOneClass")
public final class ActivityNavigator {

    public static void startHomeActivity(@NonNull final Context context) {
        final Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
    }

    public static void startDetailsMovieActivity(@NonNull final Context context,
                                            final int id,
                                            final String title,
                                            final String backdropUrl,
                                            final String posterUrl) {
        final Intent intent = new Intent(context, BaseDetailsMovieActivity.class);
        intent.putExtra(Constants.Extras.ID, id);
        intent.putExtra(Constants.Extras.TITLE, title);
        intent.putExtra(Constants.Extras.BACKDROP_URL, backdropUrl);
        intent.putExtra(Constants.Extras.POSTER_URL, posterUrl);
        context.startActivity(intent);
    }

    public static void startDetailsTvShowActivity(@NonNull final Context context,
                                                  final int id,
                                                  final String title,
                                                  final String backdropUrl,
                                                  final String posterUrl) {
        final Intent intent = new Intent(context, DetailsTvShowActivity.class);
        intent.putExtra(Constants.Extras.ID, id);
        intent.putExtra(Constants.Extras.TITLE, title);
        intent.putExtra(Constants.Extras.BACKDROP_URL, backdropUrl);
        intent.putExtra(Constants.Extras.POSTER_URL, posterUrl);
        context.startActivity(intent);
    }

    public static void startGalleryActivity(@NonNull final Context context,
                                            final int position,
                                            final String [] images,
                                            final String releaseDate,
                                            final String filmName) {

        final Intent intent = new Intent(context, SliderGalleryActivity.class);
        intent.putExtra(Constants.Extras.POSITION, position);
        intent.putExtra(Constants.Extras.URLS_ARRAY, images);
        intent.putExtra(Constants.Extras.TITLE, filmName);
        intent.putExtra(Constants.Extras.RELEASE_DATE, releaseDate);
        context.startActivity(intent);
    }
}
