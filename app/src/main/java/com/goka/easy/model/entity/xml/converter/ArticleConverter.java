package com.goka.easy.model.entity.xml.converter;

import com.goka.easy.model.entity.Article;
import com.goka.easy.model.entity.xml.ArticleXML;

import android.net.Uri;

/**
 * Created by katsuyagoto on 15/08/09.
 */
public class ArticleConverter {

    public static Article convert(ArticleXML articleXML) {
        if (articleXML == null) {
            return null;
        }
        Article article = new Article();
        article.title = articleXML.title;
        article.link = Uri.parse(articleXML.link);
        article.pubDate = articleXML.pubDate;
        article.description = articleXML.description;
        return article;
    }

}
