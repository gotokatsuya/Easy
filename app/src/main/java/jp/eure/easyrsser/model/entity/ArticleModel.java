package jp.eure.easyrsser.model.entity;

import android.net.Uri;

import ollie.Model;
import ollie.annotation.Column;
import ollie.annotation.NotNull;
import ollie.annotation.Table;
import ollie.annotation.Unique;

import static ollie.annotation.ConflictClause.REPLACE;

@Table("articles")
public class ArticleModel extends Model {

    public static final String TITLE = "title";

    public static final String LINK = "link";

    public static final String PUB_DATE = "pubDate";

    public static final String DESCRIPTION = "description";

    @Column(TITLE)
    @NotNull
    @Unique(REPLACE)
    public String title;

    @Column(LINK)
    public Uri link;

    @Column(PUB_DATE)
    public String pubDate;

    @Column(DESCRIPTION)
    public String description;

}
