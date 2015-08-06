package jp.eure.easyrsser.model.repository;

import jp.eure.easyrsser.model.entity.MediaEnclosure;
import ollie.query.Select;

/**
 * Created by katsuyagoto on 15/08/06.
 */
public class MediaEnclosureRepository {

    public static final MediaEnclosure findByURL(String url) {
        return Select.from(MediaEnclosure.class).where(MediaEnclosure.URL + "=?", url).fetchSingle();
    }

}
