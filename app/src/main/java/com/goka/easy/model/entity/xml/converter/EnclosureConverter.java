package com.goka.easy.model.entity.xml.converter;

import com.goka.easy.model.entity.Enclosure;
import com.goka.easy.model.entity.xml.EnclosureXML;

import android.net.Uri;

/**
 * Created by katsuyagoto on 15/08/09.
 */
public class EnclosureConverter {

    public static Enclosure convert(EnclosureXML enclosureXML) {
        if (enclosureXML == null) {
            return null;
        }
        Enclosure enclosure = new Enclosure();
        enclosure.url = Uri.parse(enclosureXML.url);
        enclosure.length = enclosureXML.length;
        enclosure.type = enclosureXML.type;
        return enclosure;
    }

}
