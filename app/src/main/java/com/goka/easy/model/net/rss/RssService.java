package com.goka.easy.model.net.rss;

import com.goka.easy.model.entity.xml.RssXML;

import retrofit.http.GET;
import rx.Observable;

/**
 * Created by katsuyagoto on 15/08/08.
 */
public interface RssService {

    @GET("/feed")
    Observable<RssXML> feed();
}
