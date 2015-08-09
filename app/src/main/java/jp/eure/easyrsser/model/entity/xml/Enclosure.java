package jp.eure.easyrsser.model.entity.xml;

import org.simpleframework.xml.Attribute;

public class Enclosure {

    @Attribute
    public String url;

    @Attribute
    public Integer length;

    @Attribute
    public String type;

}
