package jp.eure.easyrsser.model.entity;

import android.net.Uri;

import java.util.ArrayList;
import java.util.Date;

import jp.eure.easyrsser.model.util.StringListAdapter;
import ollie.Model;
import ollie.annotation.Column;
import ollie.annotation.ForeignKey;
import ollie.annotation.NotNull;
import ollie.annotation.Table;
import ollie.annotation.Unique;

import static ollie.annotation.ConflictClause.REPLACE;
import static ollie.annotation.ForeignKey.ReferentialAction.CASCADE;

@Table("rss_models")
public class RssModel extends Model {

    public static final String TITLE = "name";

    public static final String LINK = "user";

    public static final String DESCRIPTION = "description";

    public static final String PUB_DATE = "pub_date";

    public static final String CONTENT = "content";

    public static final String MEDIA_ENCLOSURE = "media_enclosure";

    public static final String CATEGORIES = "categories";

    @Column(TITLE)
    @NotNull
    @Unique(REPLACE)
    public String title;

    @Column(LINK)
    public Uri link;

    @Column(DESCRIPTION)
    public String description;

    @Column(PUB_DATE)
    public Date pubDate;

    @Column(CONTENT)
    public String content;

    @Column(MEDIA_ENCLOSURE)
    @ForeignKey(onDelete = CASCADE)
    public MediaEnclosureModel enclosure;

    @Column(CATEGORIES)
    public String serializedCategories;

    private ArrayList<String> categories;

    public void setSerializedCategories() {
        if (categories == null) {
            categories = new ArrayList<>();
        }
        this.serializedCategories = StringListAdapter.serialize(categories);
    }

    public ArrayList<String> getDeserializedCategories() {
        return StringListAdapter.deserialize(serializedCategories);
    }

    public void addCategory(String category) {
        if (categories == null) {
            categories = new ArrayList<>();
        }
        categories.add(category);
    }

}
