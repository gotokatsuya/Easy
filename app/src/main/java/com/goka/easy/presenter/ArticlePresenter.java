package com.goka.easy.presenter;

import com.goka.easy.Constant;
import com.goka.easy.R;
import com.goka.easy.model.entity.Article;
import com.goka.easy.view.ArticleView;
import com.goka.easy.view.popup.ArticlePopup;

import org.parceler.Parcels;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import javax.inject.Inject;
import javax.inject.Singleton;

import de.greenrobot.event.EventBus;
import mortar.ViewPresenter;
import timber.log.Timber;

/**
 * Created by katsuyagoto on 15/08/14.
 */
public class ArticlePresenter {

    @Singleton
    @dagger.Component
    public interface Component {

        void inject(ArticleView v);
    }

    @Singleton
    public static class Presenter extends ViewPresenter<ArticleView> {

        Article mArticle;

        @Inject
        Presenter() {
        }

        @Override
        public void onLoad(Bundle savedInstanceState) {
            Timber.d("OnLoad");
            EventBus.getDefault().register(this);

            super.onLoad(savedInstanceState);
            if (!hasView()) {
                return;
            }

            Intent intent = getView().getActivity().getIntent();
            mArticle = Parcels.unwrap(intent.getParcelableExtra(Constant.Extra.ARTICLE));

            getView().loadData(mArticle.description, "text/html; charset=UTF-8", "UTF-8");

            new ArticlePopup(getView().getContext(), mArticle)
                    .show();
        }

        public void onEvent(View popupViewEvent) {
            if (!hasView()) {
                return;
            }
            switch (popupViewEvent.getId()) {
                case R.id.popup_back_btn:
                    getView().getActivity().finish();
                    break;
            }
        }

        @Override
        public void dropView(ArticleView view) {
            Timber.d("DropView");
            EventBus.getDefault().unregister(this);
            super.dropView(view);
        }
    }
}
