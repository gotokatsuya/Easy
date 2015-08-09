package jp.eure.easyrsser.model.entity.xml;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "item", strict = false)
public class Article {

    @Element
    public String title;

    @Element
    public String link;

    @Element(required = false)
    public String pubDate;

    @Element(data = true)
    public String description;

    @Element(required = false)
    public Enclosure enclosure;

}
