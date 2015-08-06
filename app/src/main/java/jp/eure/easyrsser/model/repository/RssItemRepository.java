package jp.eure.easyrsser.model.repository;

import java.util.List;

import jp.eure.easyrsser.model.entity.RSSItem;
import ollie.query.Select;

/**
 * Created by katsuyagoto on 15/08/06.
 */
public class RssItemRepository {

    public static final void save(RSSItem rssItem) {
        // Should serialize `categories`(ArrayList<String>) before save.
        rssItem.setSerializedCategories();
        rssItem.save();
    }

    public static final RSSItem findByTitle(String title) {
        return Select.from(RSSItem.class).where(RSSItem.TITLE + "=?", title).fetchSingle();
    }

    public static final List<RSSItem> findAll() {
        return Select.from(RSSItem.class).fetch();
    }

}
