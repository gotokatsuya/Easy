package com.goka.easy.model.ollie;

import ollie.Model;
import ollie.Ollie;

/**
 * Created by katsuyagoto on 15/08/19.
 */
public class MigrationUtil {

    public enum SqlType {
        BLOB, REAL, INTEGER, TEXT;
    }

    public static <T extends Model> String AlterAddColumn(Class<T> cls, String column, SqlType sqlType) {
        StringBuilder sql = new StringBuilder();
        sql.append("ALTER TABLE ");
        sql.append(Ollie.getTableName(cls));
        sql.append(" ADD COLUMN ");
        sql.append(column);
        sql.append(" ");
        sql.append(sqlType.name());
        return sql.toString();
    }

}
