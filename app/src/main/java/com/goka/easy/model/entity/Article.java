package com.goka.easy.model.entity;

import org.parceler.Parcel;

import android.net.Uri;

import ollie.Model;
import ollie.annotation.Column;
import ollie.annotation.NotNull;
import ollie.annotation.Table;
import ollie.annotation.Unique;

import static ollie.annotation.ConflictClause.IGNORE;

@Parcel
@Table("articles")
public class Article extends Model {

    public static final String TITLE = "title";

    public static final String LINK = "link";

    public static final String PUB_DATE = "pubDate";

    public static final String DESCRIPTION = "description";

    public static final String ENCLOSURE = "enclosure";

    public static final String AUTHOR = "author";

    public Article() {
        super();
    }

    @Column(TITLE)
    public String title;

    @Column(LINK)
    @NotNull
    @Unique(IGNORE)
    public Uri link;

    @Column(PUB_DATE)
    public String pubDate;

    @Column(DESCRIPTION)
    public String description;

    @Column(ENCLOSURE)
    public Enclosure enclosure;

    @Column(AUTHOR)
    public String author;

}
