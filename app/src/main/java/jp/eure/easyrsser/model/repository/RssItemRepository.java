package jp.eure.easyrsser.model.repository;

import java.util.List;

import jp.eure.easyrsser.model.entity.RssModel;
import ollie.query.Select;

/**
 * Created by katsuyagoto on 15/08/06.
 */
public class RssItemRepository {

    public static final void save(RssModel rssModel) {
        // Should serialize `categories`(ArrayList<String>) before save.
        rssModel.setSerializedCategories();
        rssModel.save();
    }

    public static final RssModel findByTitle(String title) {
        return Select.from(RssModel.class).where(RssModel.TITLE + "=?", title).fetchSingle();
    }

    public static final List<RssModel> findAll() {
        return Select.from(RssModel.class).fetch();
    }

}
