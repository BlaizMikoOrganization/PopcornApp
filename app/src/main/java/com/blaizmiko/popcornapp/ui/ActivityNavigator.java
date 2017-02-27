package com.blaizmiko.popcornapp.ui;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.blaizmiko.popcornapp.ui.activities.home.HomeActivity;
import com.blaizmiko.popcornapp.ui.activities.movie.MovieDetailsActivity;

@SuppressWarnings("StaticMethodOnlyUsedInOneClass")
public final class ActivityNavigator {

    public static void startHomeActivity(@NonNull final Context context) {
        final Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
    }

    public static void startMovieDetailsActivity(@NonNull final Context context, final int elementId) {
        final Intent intent = new Intent(context, MovieDetailsActivity.class);
        intent.putExtra("ID", elementId);
        context.startActivity(intent);
    }
}
