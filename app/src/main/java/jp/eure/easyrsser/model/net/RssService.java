package jp.eure.easyrsser.model.net;

import java.util.List;

import jp.eure.easyrsser.model.entity.RssModel;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by katsuyagoto on 15/08/08.
 */
public interface RssService {
    @GET("/feed")
    List<RssModel> list();
}
