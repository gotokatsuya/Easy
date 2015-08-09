package jp.eure.easyrsser.model.net;

import jp.eure.easyrsser.model.entity.xml.RSS;
import retrofit.http.GET;

/**
 * Created by katsuyagoto on 15/08/08.
 */
public interface RssService {

    @GET("/feed")
    RSS get();
}
