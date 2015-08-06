package jp.eure.easyrsser.rss;

import org.xml.sax.Attributes;

public final class MediaAttributes {

    private MediaAttributes() {
    }

    public static String stringValue(Attributes attributes, String name) {
        return attributes.getValue(name);
    }

    public static int intValue(Attributes attributes, String name, int defaultValue) {
        final String value = stringValue(attributes, name);
        if (value == null) {
            return defaultValue;
        }
        return Integer.parseInt(value);
    }

    public static Integer intValue(Attributes attributes, String name) {
        final String value = stringValue(attributes, name);
        if (value == null) {
            return null;
        }
        return Utils.parseInteger(value);
    }

}

