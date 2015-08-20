package com.goka.easy.model.entity;

import org.parceler.Parcel;

import android.net.Uri;

import ollie.Model;
import ollie.annotation.Column;
import ollie.annotation.Table;
import ollie.annotation.Unique;

import static ollie.annotation.ConflictClause.IGNORE;

@Parcel
@Table("enclosures")
public class Enclosure extends Model {

    public static final String URL = "url";

    public static final String LENGTH = "length";

    public static final String TYPE = "type";

    public Enclosure() {
        super();
    }

    @Column(URL)
    @Unique(IGNORE)
    public Uri url;

    @Column(LENGTH)
    public Integer length;

    @Column(TYPE)
    public String type;

}
