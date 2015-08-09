package jp.eure.easyrsser.model.repository;

import java.util.List;

import jp.eure.easyrsser.model.entity.MediaThumbnailModel;
import jp.eure.easyrsser.model.entity.RssModel;
import ollie.query.Select;

/**
 * Created by katsuyagoto on 15/08/06.
 */
public class MediaThumbnailRepository {

    public static final MediaThumbnailModel findByURL(String url) {
        return Select.from(MediaThumbnailModel.class).where(MediaThumbnailModel.URL + "=?", url).fetchSingle();
    }

    public static final List<MediaThumbnailModel> getThumbnails(RssModel rssModel) {
        return Select.from(MediaThumbnailModel.class).where(MediaThumbnailModel.RSS_ID + "=?", rssModel.id).fetch();
    }

    public static final void save(RssModel rssModel, MediaThumbnailModel mediaThumbnailModel) {
        mediaThumbnailModel.rssID = rssModel.id;
        mediaThumbnailModel.save();
    }

}
