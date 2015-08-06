package jp.eure.easyrsser.model.util;

import android.net.Uri;

import ollie.TypeAdapter;

/**
 * Created by katsuyagoto on 15/08/05.
 */
public class UriAdapter extends TypeAdapter<Uri, String> {

    @Override
    public String serialize(Uri value) {
        if (value != null) {
            return value.toString();
        }
        return null;
    }

    @Override
    public Uri deserialize(String value) {
        if (value != null) {
            return Uri.parse(value);
        }
        return null;
    }

}
