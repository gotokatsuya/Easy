package com.goka.easy.view.activity;

import com.goka.easy.DaggerService;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import mortar.MortarScope;
import mortar.bundler.BundleServiceRunner;

import static com.goka.easy.DaggerService.createComponent;
import static mortar.MortarScope.buildChild;
import static mortar.MortarScope.findChild;

/**
 * Created by katsuyagoto on 15/08/10.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected abstract Class componentClass();

    protected abstract int layoutResID();

    protected String scopeName() {
        return getClass().getName();
    }

    @Override
    public Object getSystemService(String name) {
        MortarScope activityScope = findChild(getApplicationContext(), scopeName());
        if (activityScope == null) {
            activityScope = buildChild(getApplicationContext())
                    .withService(BundleServiceRunner.SERVICE_NAME, new BundleServiceRunner())
                    .withService(DaggerService.SERVICE_NAME, createComponent(componentClass()))
                    .build(scopeName());
        }
        return activityScope.hasService(name) ? activityScope.getService(name) : super.getSystemService(name);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BundleServiceRunner.getBundleServiceRunner(this).onCreate(savedInstanceState);
        setContentView(layoutResID());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        BundleServiceRunner.getBundleServiceRunner(this).onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        if (isFinishing()) {
            MortarScope activityScope = findChild(getApplicationContext(), scopeName());
            if (activityScope != null) {
                activityScope.destroy();
            }
        }
        super.onDestroy();
    }

}

