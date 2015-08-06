package jp.eure.easyrsser.model.repository;

import java.util.List;

import jp.eure.easyrsser.model.entity.MediaThumbnail;
import jp.eure.easyrsser.model.entity.RSSItem;
import ollie.query.Select;

/**
 * Created by katsuyagoto on 15/08/06.
 */
public class MediaThumbnailRepository {

    public static final MediaThumbnail findByURL(String url) {
        return Select.from(MediaThumbnail.class).where(MediaThumbnail.URL + "=?", url).fetchSingle();
    }

    public static final List<MediaThumbnail> getThumbnails(RSSItem rssItem) {
        return Select.from(MediaThumbnail.class).where(MediaThumbnail.RSS_ID + "=?", rssItem.id).fetch();
    }

    public static final void save(RSSItem rssItem, MediaThumbnail mediaThumbnail) {
        mediaThumbnail.rssID = rssItem.id;
        mediaThumbnail.save();
    }

}
