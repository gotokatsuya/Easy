package com.goka.easy.model.entity.xml;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "channel", strict = false)
public class ChannelXML {

    @Element
    public String title;

    @Element(required = false)
    public String link;

    @Element(required = false)
    public String description;

    @ElementList(name = "item", inline = true)
    public List<ArticleXML> articles;

}
