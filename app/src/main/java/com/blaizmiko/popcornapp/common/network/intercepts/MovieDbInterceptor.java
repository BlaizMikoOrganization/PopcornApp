package com.blaizmiko.popcornapp.common.network.intercepts;

import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.network.api.MovieDbApi;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class MovieDbInterceptor implements Interceptor {

    @Override
    public Response intercept(final Chain chain) throws IOException {
        final Request originalRequest = chain.request();
        final HttpUrl originalUrl = originalRequest.url();

        final HttpUrl url = originalUrl.newBuilder()
                .addQueryParameter(MovieDbApi.QUERY_API_KEY, Constants.MovieDbApi.API_KEY)
                .addQueryParameter(MovieDbApi.QUERY_LANGUAGE, Constants.MovieDbApi.DEFAULT_LANGUAGE)
                .build();

        final Request.Builder requestBuilder = originalRequest.newBuilder().url(url);
        final Request newRequest = requestBuilder.build();
        return chain.proceed(newRequest);
    }
}
