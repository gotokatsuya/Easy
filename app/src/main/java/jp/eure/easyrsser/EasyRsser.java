package jp.eure.easyrsser;

import android.app.Application;

import ollie.Ollie;

/**
 * Created by katsuyagoto on 15/08/05.
 */
public class EasyRsser extends Application {

    public static final int MAX_AVAILABLE_MEMORY = (int) (Runtime.getRuntime().maxMemory() / 1024);

    @Override
    public void onCreate() {
        super.onCreate();

        Ollie.with(this)
                .setName(getString(R.string.db_name))
                .setVersion(getResources().getInteger(R.integer.db_version))
                .setLogLevel(BuildConfig.DEBUG ? Ollie.LogLevel.FULL : Ollie.LogLevel.NONE)
                .setCacheSize(MAX_AVAILABLE_MEMORY / 8)
                .init();
    }

}
