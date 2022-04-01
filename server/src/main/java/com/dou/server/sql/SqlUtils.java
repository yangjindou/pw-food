package com.dou.server.sql;

/**
 * @author yangjindou
 * @date 2022-04-01
 */
public class SqlUtils {

    public static String concatLike(Object str, SqlLike type) {
        switch(type) {
            case LEFT:
                return "%" + str;
            case RIGHT:
                return str + "%";
            default:
                return "%" + str + "%";
        }
    }
}
