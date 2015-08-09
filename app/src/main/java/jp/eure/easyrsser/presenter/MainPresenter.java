package jp.eure.easyrsser.presenter;

import android.os.Bundle;

import javax.inject.Inject;
import javax.inject.Singleton;

import jp.eure.easyrsser.view.MainView;
import mortar.ViewPresenter;

/**
 * Created by katsuyagoto on 15/08/08.
 */
public class MainPresenter {

    @dagger.Component
    @Singleton
    public interface Component {

        void inject(MainView t);
    }

    @Singleton
    public static class Presenter extends ViewPresenter<MainView> {

        private int serial = -1;

        @Inject
        Presenter() {
        }

        @Override
        protected void onLoad(Bundle savedInstanceState) {
            if (savedInstanceState != null && serial == -1) {
                serial = savedInstanceState.getInt("serial");
            }
            getView().show(serial);
        }

        @Override
        protected void onSave(Bundle outState) {
            outState.putInt("serial", serial);
        }
    }
}
