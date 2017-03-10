package com.blaizmiko.popcornapp.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.ui.actors.details.ActorDetailsActivity;
import com.blaizmiko.popcornapp.ui.home.HomeActivity;
import com.blaizmiko.popcornapp.ui.movies.details.MovieDetailsActivity;

@SuppressWarnings("StaticMethodOnlyUsedInOneClass")
public final class ActivityNavigator {

    public static void startHomeActivity(@NonNull final Activity activity) {
        final Intent intent = new Intent(activity, HomeActivity.class);
        activity.startActivity(intent);
    }

    public static void startMovieDetailsActivity(@NonNull final Activity activity, final int id) {
        final Intent intent = new Intent(activity, MovieDetailsActivity.class);
        intent.putExtra(Constants.Bundles.ID, id);
        activity.startActivity(intent);
    }

    public static void startActorDetailsActivity(@NonNull final Activity activity) {
        final Intent intent = new Intent(activity, ActorDetailsActivity.class);
        activity.startActivity(intent);
    }
}
