package com.goka.easy.model.repository;

import com.goka.easy.model.entity.Article;
import com.goka.easy.model.entity.Enclosure;
import com.goka.easy.model.entity.xml.ArticleXML;
import com.goka.easy.model.entity.xml.ChannelXML;
import com.goka.easy.model.entity.xml.RssXML;
import com.goka.easy.model.entity.xml.converter.ArticleConverter;
import com.goka.easy.model.entity.xml.converter.EnclosureConverter;

import java.util.List;

/**
 * Created by katsuyagoto on 15/08/09.
 */
public class RssRepository {

    public static boolean save(RssXML rssXML) {
        ChannelXML channelXML = rssXML.channel;
        if (channelXML == null) {
            return false;
        }

        List<ArticleXML> articleXMLs = channelXML.articles;
        for (ArticleXML articleXML : articleXMLs) {
            Enclosure enclosure = EnclosureConverter.convert(articleXML.enclosure);
            if (enclosure != null) {
                enclosure.save();
            }

            Article article = ArticleConverter.convert(articleXML);
            if (article != null) {
                article.author = null;
                if (enclosure != null) {
                    article.enclosure = enclosure;
                }
                article.save();
            }
        }

        return true;
    }
}
