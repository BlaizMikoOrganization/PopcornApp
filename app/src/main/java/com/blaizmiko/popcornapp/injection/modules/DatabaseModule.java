package com.blaizmiko.popcornapp.injection.modules;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.blaizmiko.popcornapp.data.db.Database;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {
    public DatabaseModule(){}

    @Provides
    @NonNull
    @Singleton
    public Database provideDatabase(@NonNull final Context context) {
        return new Database(context);
    }
}
