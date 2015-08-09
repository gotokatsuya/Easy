package jp.eure.easyrsser.model.net;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import jp.eure.easyrsser.model.entity.xml.RSS;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.SimpleXMLConverter;

/**
 * Created by katsuyagoto on 15/08/08.
 */
public class RssClient {

    private static RssService newRssService(String endpoint) {
        final OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(60, TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(60, TimeUnit.SECONDS);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setClient(new OkClient(okHttpClient))
                .setEndpoint(endpoint)
                .setConverter(new SimpleXMLConverter())
                .build();
        return restAdapter.create(RssService.class);
    }

    public static RSS get(String endpoint) {
        return newRssService(endpoint).get();
    }

}
