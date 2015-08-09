package jp.eure.easyrsser.view.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import jp.eure.easyrsser.DaggerService;
import jp.eure.easyrsser.presenter.MainPresenter;
import jp.eure.easyrsser.R;
import jp.eure.easyrsser.view.fragment.RssFragment;
import mortar.MortarScope;
import mortar.bundler.BundleServiceRunner;
import static mortar.MortarScope.buildChild;
import static mortar.MortarScope.findChild;
import static jp.eure.easyrsser.DaggerService.createComponent;

public class MainActivity extends AppCompatActivity {

    @Override
    public Object getSystemService(String name) {
        MortarScope activityScope = findChild(getApplicationContext(), getScopeName());
        if (activityScope == null) {
            activityScope = buildChild(getApplicationContext())
                    .withService(BundleServiceRunner.SERVICE_NAME, new BundleServiceRunner())
                    .withService(DaggerService.SERVICE_NAME, createComponent(MainPresenter.Component.class))
                    .build(getScopeName());
        }
        return activityScope.hasService(name) ? activityScope.getService(name) : super.getSystemService(name);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BundleServiceRunner.getBundleServiceRunner(this).onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.main_frame, RssFragment.newInstance())
                .commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        BundleServiceRunner.getBundleServiceRunner(this).onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        if (isFinishing()) {
            MortarScope activityScope = findChild(getApplicationContext(), getScopeName());
            if (activityScope != null) {
                activityScope.destroy();
            }
        }
        super.onDestroy();
    }

    private String getScopeName() {
        return getClass().getName();
    }
}
