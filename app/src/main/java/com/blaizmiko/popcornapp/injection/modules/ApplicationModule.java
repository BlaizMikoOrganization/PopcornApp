package com.blaizmiko.popcornapp.injection.modules;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    @NonNull
    private final Application application;

    public ApplicationModule(@NonNull final Application application) {
        this.application = application;
    }

    @Provides
    @NonNull
    @Singleton
    public Application provideApp() {
        return application;
    }

    @Provides
    @NonNull
    @Singleton
    public Context provideApplicationContext() {
        return application.getApplicationContext();
    }
}
