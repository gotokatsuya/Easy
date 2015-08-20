package com.goka.easy.model.net.rss;

import com.goka.easy.model.entity.xml.RssXML;
import com.goka.easy.model.net.BaseClient;

import android.content.Context;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.SimpleXMLConverter;
import rx.Observable;

/**
 * Created by katsuyagoto on 15/08/08.
 */
public class RssClient extends BaseClient implements RssService {

    public RssClient(Context context, String endpoint) {
        super(context, endpoint);
    }

    private RssService newRssService() {
        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setClient(new OkClient())
                .setEndpoint(this.mEndpoint)
                .setConverter(new SimpleXMLConverter())
                .build();
        return restAdapter.create(RssService.class);
    }

    @Override
    public Observable<RssXML> feed() {
        return newRssService().feed();
    }

}
