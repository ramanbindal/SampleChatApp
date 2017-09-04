package com.example.cub01.samplechatapp.core;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cub01.samplechatapp.ObjectGraphService;
import com.example.cub01.samplechatapp.Screen.GsonParceler;
import com.example.cub01.samplechatapp.R;
import com.example.cub01.samplechatapp.Screen.HandlesBack;
import com.example.cub01.samplechatapp.Screen.TimelineScreen;
import com.google.gson.Gson;

import flow.Flow;
import flow.FlowDelegate;
import flow.History;
import flow.path.Path;
import flow.path.PathContainerView;
import mortar.MortarScope;
import mortar.bundler.BundleServiceRunner;

import static mortar.MortarScope.buildChild;
import static mortar.MortarScope.findChild;

public class MainActivity extends Activity implements Flow.Dispatcher {

    FlowDelegate flowDelegate;
    PathContainerView pathContainerView;
    private HandlesBack containerAsHandlesBack;


    @Override
    public Object getSystemService(String name) {
        MortarScope activityScope = findChild(getApplicationContext(), getScopeName());

        if (activityScope == null) {
            activityScope = buildChild(getApplicationContext()) //
                    .withService(BundleServiceRunner.SERVICE_NAME, new BundleServiceRunner())
//                .withService(HelloPresenter.class.getName(), new HelloPresenter())
                    .build(getScopeName());
        }

        return activityScope.hasService(name) ? activityScope.getService(name)
                : super.getSystemService(name);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BundleServiceRunner.getBundleServiceRunner(this).onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final GsonParceler parceler = new GsonParceler(new Gson());

        final FlowDelegate.NonConfigurationInstance nonConfig =
                (FlowDelegate.NonConfigurationInstance) getLastNonConfigurationInstance();

        ObjectGraphService.inject(this, this);

        pathContainerView = (PathContainerView) findViewById(R.id.container);
        containerAsHandlesBack = (HandlesBack) pathContainerView;
        flowDelegate = FlowDelegate
                .onCreate(nonConfig, getIntent(), savedInstanceState, parceler,
                        History.single(new TimelineScreen()), MainActivity.this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        flowDelegate.onResume();
    }

    @Override
    protected void onPause() {
        flowDelegate.onPause();
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        BundleServiceRunner.getBundleServiceRunner(this).onSaveInstanceState(outState);
        flowDelegate.onSaveInstanceState(outState);

    }

    @SuppressWarnings("deprecation") // https://code.google.com/p/android/issues/detail?id=151346
    @Override
    public Object onRetainNonConfigurationInstance() {
        return flowDelegate.onRetainNonConfigurationInstance();
    }

    @Override
    protected void onDestroy() {
        if (isFinishing()) {
            MortarScope activityScope = findChild(getApplicationContext(), getScopeName());
            if (activityScope != null) activityScope.destroy();
        }

        super.onDestroy();
    }


    @Override
    public void onBackPressed() {
        if (((HandlesBack) pathContainerView).onBackPressed()) return;
        if (flowDelegate.onBackPressed()) return;

        super.onBackPressed();
    }
// Flow.Dispatcher

    @Override
    public void dispatch(Flow.Traversal traversal, final Flow.TraversalCallback callback) {
        Path path = traversal.destination.top();
        setTitle(path.getClass().getSimpleName());
//        ActionBar actionBar = getActionBar();
//        boolean canGoBack = traversal.destination.size() > 1;
//        actionBar.setDisplayHomeAsUpEnabled(canGoBack);
//        actionBar.setHomeButtonEnabled(canGoBack);

        pathContainerView.dispatch(traversal, new Flow.TraversalCallback() {
            @Override
            public void onTraversalCompleted() {
                invalidateOptionsMenu();
                callback.onTraversalCompleted();
            }
        });
    }


    private String getScopeName() {
        return getClass().getName();
    }


}
