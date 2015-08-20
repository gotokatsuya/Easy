package com.goka.easy.presenter;

import com.goka.easy.Easy;
import com.goka.easy.model.CallBack;
import com.goka.easy.model.entity.Article;
import com.goka.easy.model.net.rss.RssComposer;
import com.goka.easy.model.repository.ArticleRepository;
import com.goka.easy.view.MainView;
import com.goka.easy.view.activity.ArticleActivity;

import org.parceler.Parcels;

import android.os.Bundle;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import mortar.ViewPresenter;
import timber.log.Timber;

/**
 * Created by katsuyagoto on 15/08/08.
 */
public class MainPresenter {

    @dagger.Component
    @Singleton
    public interface Component {

        void inject(MainView v);
    }

    @Singleton
    public static class Presenter extends ViewPresenter<MainView> {

        @Inject
        Presenter() {
        }

        @Override
        public void onLoad(Bundle savedInstanceState) {
            Timber.d("OnLoad");
            super.onLoad(savedInstanceState);
            if (!hasView()) {
                return;
            }

            getView().setArticles(ArticleRepository.findAll());
            asyncUpdateArticles();
        }

        private void asyncUpdateArticles() {
            RssComposer.getInstance().feed(getView().getContext(), Easy.END_POINT, new CallBack.List<Article>() {
                @Override
                public void call(List<Article> list) {
                    if (!hasView() || list.isEmpty()) {
                        return;
                    }
                    getView().clearArticles();
                    getView().setArticles(list);
                }
            });
        }

        public void onClickArticle(Article article) {
            if (!hasView()) {
                return;
            }
            ArticleActivity.launch(getView().getActivity(), Parcels.wrap(article));
        }

        @Override
        public void dropView(MainView view) {
            Timber.d("DropView");
            super.dropView(view);
        }

    }
}
