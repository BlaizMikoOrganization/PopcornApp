package com.blaizmiko.popcornapp.injection.modules;

import android.content.Context;
import android.support.annotation.NonNull;

import com.blaizmiko.popcornapp.data.API;
import com.blaizmiko.popcornapp.data.DataManager;
import com.blaizmiko.popcornapp.data.Database;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataManagerModule {

    @Provides
    @NonNull
    @Singleton
    public DataManager provideDataManager() {
        return new DataManager();
    }

    @Provides
    @NonNull
    @Singleton
    public Database provideDatabase(@NonNull final Context context) {
        return new Database(context);
    }

    @Provides
    @NonNull
    @Singleton
    public API provideAPI() {
        return new API();
    }
}
