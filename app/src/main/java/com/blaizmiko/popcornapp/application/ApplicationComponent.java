package com.blaizmiko.popcornapp.application;

import com.blaizmiko.popcornapp.models.api.ApiModule;
import com.blaizmiko.popcornapp.models.network.NetworkModule;
import com.blaizmiko.popcornapp.ui.activities.home.HomeActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class, NetworkModule.class})
public interface ApplicationComponent {

    void inject(HomeActivity homeActivity);
}
