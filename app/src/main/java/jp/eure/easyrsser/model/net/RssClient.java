package jp.eure.easyrsser.model.net;

import java.util.List;

import jp.eure.easyrsser.model.entity.RssModel;
import retrofit.RestAdapter;
import retrofit.converter.SimpleXMLConverter;

/**
 * Created by katsuyagoto on 15/08/08.
 */
public class RssClient {

    public static final String URL = "http://www.pairs.lv";

    private static RssService newRssService(String endpoint) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(endpoint)
                .setConverter(new SimpleXMLConverter())
                .build();
        return restAdapter.create(RssService.class);
    }

    public static List<RssModel> list(String endpoint) {
        return newRssService(endpoint).list();
    }

}
