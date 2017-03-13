package com.blaizmiko.popcornapp.injection.modules;

import android.support.annotation.NonNull;

import com.blaizmiko.popcornapp.common.network.api.MovieDbApi;
import com.blaizmiko.popcornapp.common.network.api.OMDbApi;
import com.blaizmiko.popcornapp.common.network.intercepts.MovieDbInterceptor;
import com.blaizmiko.popcornapp.common.network.intercepts.OMDbInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    @NonNull
    private final String movieDbUrl, omDbUrl;

    public ApiModule(@NonNull final String movieDbUrl, @NonNull final String omDbUrl) {
        this.movieDbUrl = movieDbUrl;
        this.omDbUrl = omDbUrl;
    }

    @Provides
    @NonNull
    @Singleton
    public MovieDbApi provideMovieDbApi(@NonNull final OkHttpClient okHttpClient) {
        final OkHttpClient.Builder okHttpBuilder = okHttpClient.newBuilder();
        okHttpBuilder.addInterceptor(new MovieDbInterceptor());

        return new Retrofit.Builder()
                .baseUrl(movieDbUrl)
                .client(okHttpBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(MovieDbApi.class);
    }

    @NonNull
    @Singleton
    @Provides
    public OMDbApi provideOMDbApi(@NonNull final OkHttpClient okHttpClient) {
        final OkHttpClient.Builder okHttpBuilder = okHttpClient.newBuilder();
        okHttpBuilder.addInterceptor(new OMDbInterceptor());

        return new Retrofit.Builder()
                .baseUrl(omDbUrl)
                .client(okHttpBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(OMDbApi.class);
    }
}
