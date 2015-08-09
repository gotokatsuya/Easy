package jp.eure.easyrsser.model.entity;

import android.net.Uri;

import ollie.Model;
import ollie.annotation.Column;
import ollie.annotation.NotNull;
import ollie.annotation.Table;
import ollie.annotation.Unique;

import static ollie.annotation.ConflictClause.REPLACE;

@Table("enclosures")
public class EnclosureModel extends Model {

    public static final String ARTICLE_ID = "article_id";

    public static final String URL = "url";

    public static final String LENGTH = "length";

    public static final String TYPE = "type";

    @Column(ARTICLE_ID)
    @NotNull
    @Unique(REPLACE)
    public Long articleID;

    @Column(URL)
    public Uri url;

    @Column(LENGTH)
    public Integer length;

    @Column(TYPE)
    public String type;

}
