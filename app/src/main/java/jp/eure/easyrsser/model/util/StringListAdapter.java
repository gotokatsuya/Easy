package jp.eure.easyrsser.model.util;

import android.text.TextUtils;

import java.util.ArrayList;

/**
 * Created by katsuyagoto on 15/08/05.
 */

// Can not use List<String> as a TypeAdapter on Ollie.
public class StringListAdapter {

    public static String serialize(ArrayList<String> values) {
        if (values != null) {
            String result = null;
            for (int i = 0, n = values.size(); i < n; i++) {
                result = values.get(i) + ",";
            }
            return result;
        }
        return null;
    }

    public static ArrayList<String> deserialize(String value) {
        ArrayList<String> result = new ArrayList<>();
        if (value != null) {
            String[] values = value.split(",");
            for (int i = 0, n = values.length; i < n; i++) {
                String val = values[i];
                if (!TextUtils.isEmpty(val)) {
                    result.add(val);
                }
            }
        }
        return result;
    }

}
