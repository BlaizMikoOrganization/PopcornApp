package com.blaizmiko.popcornapp.di;

import com.blaizmiko.popcornapp.di.modules.ApplicationModule;
import com.blaizmiko.popcornapp.di.modules.ApiModule;
import com.blaizmiko.popcornapp.di.modules.NetworkModule;
import com.blaizmiko.popcornapp.ui.activities.home.HomeActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class, NetworkModule.class})
public interface ApplicationComponent {

    void inject(HomeActivity homeActivity);
}
