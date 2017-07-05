package com.blaizmiko.popcornapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.ui.actors.details.DetailsActorActivity;
import com.blaizmiko.popcornapp.ui.all.activities.TrailersActivity;
import com.blaizmiko.popcornapp.ui.gallery.GalleryActivity;
import com.blaizmiko.popcornapp.ui.home.HomeActivity;
import com.blaizmiko.popcornapp.ui.movies.details.DetailsMovieActivity;
import com.blaizmiko.popcornapp.ui.movies.reviews.ReviewActivity;
import com.blaizmiko.popcornapp.ui.tvshows.details.DetailsTvShowActivity;
import com.blaizmiko.popcornapp.ui.tvshows.seasons.SeasonTvShowTvShowActivity;

@SuppressWarnings("StaticMethodOnlyUsedInOneClass")
public final class ActivityNavigator {

    public static void startHomeActivity(@NonNull final Activity activity) {
        final Intent intent = new Intent(activity, HomeActivity.class);
        activity.startActivity(intent);
    }

    public static void startDetailsMovieActivity(@NonNull final Activity activity,
                                                 final long id,
                                                 final String title,
                                                 final String backdropUrl,
                                                 final double rating) {
        final Intent intent = new Intent(activity, DetailsMovieActivity.class);
        intent.putExtra(Constants.Extras.ID, id);
        intent.putExtra(Constants.Extras.TITLE, title);
        intent.putExtra(Constants.Extras.BACKDROP_URL, backdropUrl);
        intent.putExtra(Constants.Extras.RATING, rating);
        activity.startActivity(intent);
    }

    public static void startDetailsMovieActivity(@NonNull final Activity activity,
                                                 final int id,
                                                 final String title) {
        final Intent intent = new Intent(activity, DetailsMovieActivity.class);
        intent.putExtra(Constants.Extras.ID, id);
        intent.putExtra(Constants.Extras.TITLE, title);
        activity.startActivity(intent);
    }

    public static void startDetailsTvShowActivity(@NonNull final Activity activity,
                                                  final long id,
                                                  final String title,
                                                  final String backdropUrl,
                                                  final double rating) {
        final Intent intent = new Intent(activity, DetailsTvShowActivity.class);
        intent.putExtra(Constants.Extras.ID, id);
        intent.putExtra(Constants.Extras.TITLE, title);
        intent.putExtra(Constants.Extras.BACKDROP_URL, backdropUrl);
        intent.putExtra(Constants.Extras.RATING, rating);
        activity.startActivity(intent);
    }

    public static void startDetailsTvShowActivity(@NonNull final Activity activity,
                                                  final int id,
                                                  final String title) {
        final Intent intent = new Intent(activity, DetailsTvShowActivity.class);
        intent.putExtra(Constants.Extras.ID, id);
        intent.putExtra(Constants.Extras.TITLE, title);
        activity.startActivity(intent);
    }

    public static void startGalleryActivity(@NonNull final Activity activity,
                                            final int position,
                                            final String[] images,
                                            final String releaseDate,
                                            final String filmName) {

        final Intent intent = new Intent(activity, GalleryActivity.class);
        intent.putExtra(Constants.Extras.POSITION, position);
        intent.putExtra(Constants.Extras.URLS_ARRAY, images);
        intent.putExtra(Constants.Extras.TITLE, filmName);
        intent.putExtra(Constants.Extras.RELEASE_DATE, releaseDate);
        activity.startActivity(intent);
    }

    public static void startTrailersActivity(@NonNull final Activity activity,
                                             final String videoUrl) {
        final Intent intent = new Intent(activity, TrailersActivity.class);
        intent.putExtra(Constants.Extras.VIDEO_URL, videoUrl);
        activity.startActivity(intent);
    }

    public static void startTvSeasonActivity(@NonNull final Activity activity,
                                             final int tvShowId,
                                             final String title,
                                             final int seasonNumber) {
        final Intent intent = new Intent(activity, SeasonTvShowTvShowActivity.class);
        intent.putExtra(Constants.Extras.ID, tvShowId);
        intent.putExtra(Constants.Extras.TITLE, title);
        intent.putExtra(Constants.Extras.SEASON_NUMBER, seasonNumber);
        activity.startActivity(intent);
    }

    public static void startReviewActivity(@NonNull final Activity activity,
                                           final String authorName,
                                           final String title,
                                           final String reviewText,
                                           final long movieId) {
        final Intent intent = new Intent(activity, ReviewActivity.class);
        intent.putExtra(Constants.Extras.AUTHOR, authorName);
        intent.putExtra(Constants.Extras.TITLE, title);
        intent.putExtra(Constants.Extras.REVIEW, reviewText);
        intent.putExtra(Constants.Extras.ID, movieId);
        activity.startActivity(intent);
    }

    public static void startDetailsActorActivity(@NonNull final Activity activity,
                                                 final long actorId,
                                                 final String actorName,
                                                 final String actorAvatarPath) {
        final Intent intent = new Intent(activity, DetailsActorActivity.class);
        intent.putExtra(Constants.Extras.ID, actorId);
        intent.putExtra(Constants.Extras.NAME, actorName);
        intent.putExtra(Constants.Extras.AVATAR_URL, actorAvatarPath);
        activity.startActivity(intent);
    }
}
