package com.kshrd.derphsar_api.repository.provider;

import org.apache.ibatis.jdbc.SQL;

public class PromotionProvider {

    //get all promotions
    public String getPromotions(){
        return new SQL(){{
            SELECT("*");
            FROM("dp_promotion");
        }}.toString();
    }
}
