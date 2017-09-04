package com.example.cub01.samplechatapp.core;

import com.example.cub01.samplechatapp.ActionBarOwner;
import com.example.cub01.samplechatapp.Screen.GsonParceler;
import com.example.cub01.samplechatapp.model.FriendDesc;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import flow.StateParceler;

/**
 * Created by cub01 on 9/1/2017.
 */
@Module(
        includes = { ActionBarOwner.ActionBarModule.class },
        injects = MainActivity.class,
        library = true)
public class RootModule {
    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @Singleton
    StateParceler provideParcer(Gson gson) {
        return new GsonParceler(gson);
    }
}
