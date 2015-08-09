package jp.eure.easyrsser.model.converter;

import android.net.Uri;

import jp.eure.easyrsser.model.entity.ArticleModel;
import jp.eure.easyrsser.model.entity.xml.Article;

/**
 * Created by katsuyagoto on 15/08/09.
 */
public class ArticleConverter {

    public static ArticleModel convert(Article article) {
        ArticleModel articleModel = new ArticleModel();
        articleModel.title = article.title;
        articleModel.link = Uri.parse(article.link);
        articleModel.pubDate = article.pubDate;
        articleModel.description = article.description;
        return articleModel;
    }

}
