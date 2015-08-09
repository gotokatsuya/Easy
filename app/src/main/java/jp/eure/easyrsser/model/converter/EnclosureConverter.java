package jp.eure.easyrsser.model.converter;

import android.net.Uri;

import jp.eure.easyrsser.model.entity.EnclosureModel;
import jp.eure.easyrsser.model.entity.xml.Enclosure;

/**
 * Created by katsuyagoto on 15/08/09.
 */
public class EnclosureConverter {

    public static EnclosureModel convert(Enclosure enclosure) {
        EnclosureModel enclosureModel = new EnclosureModel();
        enclosureModel.url = Uri.parse(enclosure.url);
        enclosureModel.length = enclosure.length;
        enclosureModel.type = enclosure.type;
        return enclosureModel;
    }

}
