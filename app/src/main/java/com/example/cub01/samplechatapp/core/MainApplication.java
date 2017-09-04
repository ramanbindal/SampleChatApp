package com.example.cub01.samplechatapp.core;

import android.app.Application;
import android.util.Log;

import com.example.cub01.samplechatapp.ObjectGraphService;

import dagger.ObjectGraph;
import mortar.MortarScope;

/**
 * Created by cub01 on 9/1/2017.
 */

public class MainApplication extends Application {
    private MortarScope rootScope;

    @Override
    public Object getSystemService(String name) {
        if (rootScope == null) {
            Log.e("raman", "rootscope null");
            rootScope = MortarScope.buildRootScope()
                    .withService(ObjectGraphService.SERVICE_NAME, ObjectGraph.create(new RootModule()))
                    .build("Root");
            Log.e("raman", "" + rootScope);
        } else {
            Log.e("raman", "rootscope not null");
        }

        return rootScope.hasService(name) ? rootScope.getService(name) : super.getSystemService(name);
    }
}
