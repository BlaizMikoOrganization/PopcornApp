package com.blaizmiko.popcornapp.models.network;

import android.support.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public class NetworkModule {

    private final int mTimeout;

    public NetworkModule(final int timeout) {
        this.mTimeout = timeout;
    }

    @Provides
    @NonNull
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        final OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.connectTimeout(mTimeout, TimeUnit.SECONDS);
        okHttpBuilder.writeTimeout(mTimeout, TimeUnit.SECONDS);
        okHttpBuilder.readTimeout(mTimeout * 2, TimeUnit.SECONDS);

        return okHttpBuilder.build();
    }
}
