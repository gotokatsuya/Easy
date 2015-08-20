package com.goka.easy.model.repository;

import com.goka.easy.model.CallBack;
import com.goka.easy.model.entity.Article;

import java.util.List;

import ollie.query.Select;
import rx.Observer;
import rx.Scheduler;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by katsuyagoto on 15/08/09.
 */
public class ArticleRepository {


    public static List<Article> findAll() {
        return Select.from(Article.class).fetch();
    }

    public static void asyncFindAll(final Scheduler observe, final CallBack.List<Article> callBack) {
        Select.from(Article.class)
                .observable()
                .filter(new Func1<List<Article>, Boolean>() {
                    @Override
                    public Boolean call(List<Article> articles) {
                        Timber.d("Filter.AsyncFindAll");
                        return true;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(observe)
                .subscribe(new Observer<List<Article>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(List<Article> articles) {
                        Timber.d("OnNext.AsyncFindAll");
                        callBack.call(articles);
                    }
                });
    }

}
