package jp.eure.easyrsser.model.entity;


import android.net.Uri;

import ollie.Model;
import ollie.annotation.Column;
import ollie.annotation.Table;

@Table("media_enclosure_models")
public class MediaEnclosureModel extends Model {

    public static final String RSS_ID = "rss_id";

    public static final String URL = "url";

    public static final String LENGTH = "length";

    public static final String MIME_TYPE = "mime_type";

    @Column(RSS_ID)
    public Long rssID;

    @Column(URL)
    public Uri url;

    @Column(LENGTH)
    public Integer length;

    @Column(MIME_TYPE)
    public String mimeType;

}