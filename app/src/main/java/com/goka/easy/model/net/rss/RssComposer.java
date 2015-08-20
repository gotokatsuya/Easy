package com.goka.easy.model.net.rss;

import com.goka.easy.model.CallBack;
import com.goka.easy.model.entity.Article;
import com.goka.easy.model.entity.xml.RssXML;
import com.goka.easy.model.repository.ArticleRepository;
import com.goka.easy.model.repository.RssRepository;

import android.content.Context;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by katsuyagoto on 15/08/19.
 */
public class RssComposer {

    public static final RssComposer sInstance = new RssComposer();

    public static RssComposer getInstance() {
        return sInstance;
    }

    public void feed(Context context, String endpoint, final CallBack.List<Article> callBack) {
        RssClient client = new RssClient(context, endpoint);
        client.feed()
                .filter(new Func1<RssXML, Boolean>() {
                    @Override
                    public Boolean call(RssXML rssXML) {
                        Timber.d("Filter.RssClient.Feed.RssRepository.Save");
                        return RssRepository.save(rssXML);
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<RssXML>() {
                    @Override
                    public void onCompleted() {
                        Timber.d("OnCompleted.RssClient.Feed");
                        ArticleRepository.asyncFindAll(AndroidSchedulers.mainThread(), callBack);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(RssXML rssXML) {
                    }
                });
    }

}
