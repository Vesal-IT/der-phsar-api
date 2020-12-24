package com.kshrd.derphsar_api.repository.provider;

import org.apache.ibatis.jdbc.SQL;

public class CategoryProvider {

    //select all categories
    public String select(){
        return new SQL(){{
            SELECT("*");
            FROM("dp_category");
            LIMIT("#{pagination.limit}  OFFSET #{pagination.offset}");
        }}.toString();
    }


    public String countId(){
        return new SQL(){{
            SELECT("COUNT(id)");
            FROM("dp_category");
        }}.toString();
    }
}
