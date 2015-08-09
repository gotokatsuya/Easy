package jp.eure.easyrsser.model.repository;

import java.util.List;

import jp.eure.easyrsser.model.converter.ArticleConverter;
import jp.eure.easyrsser.model.converter.EnclosureConverter;
import jp.eure.easyrsser.model.entity.ArticleModel;
import jp.eure.easyrsser.model.entity.EnclosureModel;
import jp.eure.easyrsser.model.entity.xml.Article;
import jp.eure.easyrsser.model.entity.xml.Channel;
import jp.eure.easyrsser.model.entity.xml.RSS;

/**
 * Created by katsuyagoto on 15/08/09.
 */
public class RSSRepository {

    public static boolean save(RSS rss) {
        Channel channel = rss.channel;
        if (channel == null) {
            return false;
        }

        List<Article> articles = channel.articles;
        for (Article article : articles) {
            ArticleModel articleModel = ArticleConverter.convert(article);
            articleModel.save();

            EnclosureModel enclosureModel = EnclosureConverter.convert(article.enclosure);
            enclosureModel.articleID = articleModel.id;
            enclosureModel.save();
        }

        return true;
    }
}
