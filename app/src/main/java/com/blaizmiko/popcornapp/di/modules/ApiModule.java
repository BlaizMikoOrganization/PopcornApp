package com.blaizmiko.popcornapp.di.modules;

import android.support.annotation.NonNull;

import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.api.OMDbApi;
import com.blaizmiko.popcornapp.common.api.PealApi;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    @NonNull
    private final String mPealUrl, mRatingUrl;

    public ApiModule(@NonNull final String pealUrl, @NonNull final String ratingUrl) {
        this.mPealUrl = pealUrl;
        this.mRatingUrl = ratingUrl;
    }

    @Provides
    @NonNull
    @Singleton
    public PealApi provideStubApi(@NonNull final OkHttpClient okHttpClient) {
        final OkHttpClient.Builder okHttpBuilder = okHttpClient.newBuilder();
        okHttpBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                final Request originalRequest = chain.request();
                final HttpUrl originalUrl = originalRequest.url();
                final HttpUrl newUrl = originalUrl.newBuilder()
                        .addQueryParameter("api_key", Constants.TheMovieDbApi.ApiKey)
                        .addQueryParameter("language", Constants.TheMovieDbApi.Language)
                        .build();

                final Request.Builder requestBuilder = originalRequest.newBuilder()
                        .url(newUrl);

                final Request newRequest = requestBuilder.build();
                return chain.proceed(newRequest);
            }
        });

        return new Retrofit.Builder()
                .baseUrl(mPealUrl)
                .client(okHttpBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(PealApi.class);
    }

    @NonNull
    @Singleton
    @Provides
    public OMDbApi provideRatingApi(@NonNull final OkHttpClient okHttpClient) {
        final OkHttpClient.Builder okHttpBuilder = okHttpClient.newBuilder();
        okHttpBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                final Request originalRequest = chain.request();
                final HttpUrl originalUrl = originalRequest.url();
                final HttpUrl newUrl = originalUrl.newBuilder()
                        .addQueryParameter("tomatoes", Constants.OMDbApi.BaseIncludeTomatoesRating)
                        .build();

                final Request.Builder requestBuilder = originalRequest.newBuilder()
                        .url(newUrl);

                final Request newRequest = requestBuilder.build();
                return chain.proceed(newRequest);
            }
        });

        return new Retrofit.Builder()
                .baseUrl(mRatingUrl)
                .client(okHttpBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(OMDbApi.class);
    }
}
