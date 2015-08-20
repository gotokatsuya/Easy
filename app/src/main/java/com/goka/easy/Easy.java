package com.goka.easy;

import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;

import android.app.Application;

import mortar.MortarScope;
import ollie.Ollie;
import timber.log.Timber;

/**
 * Created by katsuyagoto on 15/08/05.
 */
public class Easy extends Application {

    public static final int MAX_AVAILABLE_MEMORY = (int) (Runtime.getRuntime().maxMemory() / 1024);

    private static final String ROOT_NAME = "Root";

    private MortarScope mRootMortarScope;

    public static final String END_POINT = "http://www.pairs.lv";

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);

        initTree();
        initOllie();
        initStetho();
    }

    private void initTree() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new ERDebugTree());
        } else {
            // Should prepare a tree for release.
        }
    }

    private void initOllie() {
        Ollie.with(this)
                .setName(getString(R.string.db_name))
                .setVersion(getResources().getInteger(R.integer.db_version))
                .setLogLevel(BuildConfig.DEBUG ? Ollie.LogLevel.FULL : Ollie.LogLevel.NONE)
                .setCacheSize(MAX_AVAILABLE_MEMORY / 8)
                .init();
    }

    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(
                                Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(
                                Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }

    @Override
    public Object getSystemService(String name) {
        if (mRootMortarScope == null) {
            mRootMortarScope = MortarScope.buildRootScope().build(ROOT_NAME);
        }
        return mRootMortarScope.hasService(name) ? mRootMortarScope.getService(name) : super.getSystemService(name);
    }

}
