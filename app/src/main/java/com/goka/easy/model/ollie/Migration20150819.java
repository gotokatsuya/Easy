package com.goka.easy.model.ollie;

import com.goka.easy.model.entity.Article;

import ollie.Migration;

import static com.goka.easy.model.ollie.MigrationUtil.SqlType;

/**
 * Created by katsuyagoto on 15/08/19.
 */
public class Migration20150819 extends Migration {

    @Override
    public int getVersion() {
        return 2;
    }

    @Override
    public String[] getStatements() {
        return new String[]{
                MigrationUtil.AlterAddColumn(Article.class, Article.AUTHOR, SqlType.TEXT)
        };
    }

}
