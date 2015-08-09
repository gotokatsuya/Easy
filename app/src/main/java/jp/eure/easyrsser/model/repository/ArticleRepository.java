package jp.eure.easyrsser.model.repository;

import java.util.List;

import jp.eure.easyrsser.model.entity.ArticleModel;
import ollie.query.Select;

/**
 * Created by katsuyagoto on 15/08/09.
 */
public class ArticleRepository {

    public static List<ArticleModel> findAll() {
        return Select.from(ArticleModel.class).fetch();
    }

}
