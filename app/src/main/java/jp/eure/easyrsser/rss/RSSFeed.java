package jp.eure.easyrsser.rss;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import jp.eure.easyrsser.model.entity.RssModel;

public class RSSFeed {

    private final List<RssModel> items;

    private Date lastBuildDate;

    private Integer ttl;

    public RSSFeed() {
        super();
        items = new LinkedList<RssModel>();
    }

    public List<RssModel> getItems() {
        return Collections.unmodifiableList(items);
    }

    void addItem(RssModel item) {
        items.add(item);
    }

    void setLastBuildDate(Date date) {
        lastBuildDate = date;
    }

    public Date getLastBuildDate() {
        return lastBuildDate;
    }

    void setTTL(Integer value) {
        ttl = value;
    }

    public Integer getTTL() {
        return ttl;
    }

}

