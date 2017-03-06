package com.blaizmiko.popcornapp.di.modules;

import android.support.annotation.NonNull;

import com.blaizmiko.popcornapp.common.api.OMDbApi;
import com.blaizmiko.popcornapp.common.api.PealApi;

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
        return new Retrofit.Builder()
                .baseUrl(mRatingUrl)
                .client(okHttpBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(OMDbApi.class);
    }
}
