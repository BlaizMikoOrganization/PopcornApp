package com.blaizmiko.popcornapp.ui;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.ui.home.HomeActivity;
import com.blaizmiko.popcornapp.ui.all.presentation.details.DetailsActivity;

@SuppressWarnings("StaticMethodOnlyUsedInOneClass")
public final class ActivityNavigator {

    public static void startHomeActivity(@NonNull final Context context) {
        final Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
    }

    public static void startDetailsActivity(@NonNull final Context context,
                                            final int id,
                                            final String title,
                                            final String backdropUrl,
                                            final String posterUrl) {
        final Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(Constants.Extras.ID, id);
        intent.putExtra(Constants.Extras.TITLE, title);
        intent.putExtra(Constants.Extras.BACKDROP_URL, backdropUrl);
        intent.putExtra(Constants.Extras.POSTER_URL, posterUrl);
        context.startActivity(intent);
    }
}
