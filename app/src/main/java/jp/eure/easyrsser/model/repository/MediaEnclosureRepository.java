package jp.eure.easyrsser.model.repository;

import jp.eure.easyrsser.model.entity.MediaEnclosureModel;
import ollie.query.Select;

/**
 * Created by katsuyagoto on 15/08/06.
 */
public class MediaEnclosureRepository {

    public static final MediaEnclosureModel findByURL(String url) {
        return Select.from(MediaEnclosureModel.class).where(MediaEnclosureModel.URL + "=?", url).fetchSingle();
    }

}
