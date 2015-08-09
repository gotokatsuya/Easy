package jp.eure.easyrsser.model.entity;

import android.net.Uri;

import ollie.Model;
import ollie.annotation.Column;
import ollie.annotation.Table;

@Table("media_thumbnail_models")
public class MediaThumbnailModel extends Model {

    public static final String RSS_ID = "rss_id";

    public static final String URL = "url";

    public static final String HEIGHT = "height";

    public static final String WIDTH = "width";

    @Column(RSS_ID)
    public Long rssID;

    @Column(URL)
    public Uri url;

    @Column(HEIGHT)
    public Integer height;

    @Column(WIDTH)
    public Integer width;
}

