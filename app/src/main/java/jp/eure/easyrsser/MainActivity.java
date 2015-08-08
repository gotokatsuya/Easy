package jp.eure.easyrsser;

import com.jakewharton.scalpel.ScalpelFrameLayout;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.scalpel)
    ScalpelFrameLayout mScalpelFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupScalpel(BuildConfig.DEBUG);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.main_frame, RssFragment.newInstance())
                .commit();
    }

    public void setupScalpel(boolean enabled) {
        mScalpelFrameLayout.setLayerInteractionEnabled(enabled);
        mScalpelFrameLayout.setDrawViews(enabled);
        mScalpelFrameLayout.setDrawIds(enabled);
    }

}
