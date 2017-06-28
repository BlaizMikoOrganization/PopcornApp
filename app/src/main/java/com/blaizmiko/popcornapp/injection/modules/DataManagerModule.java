package com.blaizmiko.popcornapp.injection.modules;

import android.support.annotation.NonNull;

import com.blaizmiko.popcornapp.data.DataManager;

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
}
