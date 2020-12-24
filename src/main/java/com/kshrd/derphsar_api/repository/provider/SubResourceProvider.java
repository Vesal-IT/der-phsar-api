package com.kshrd.derphsar_api.repository.provider;

import org.apache.ibatis.jdbc.SQL;

public class SubResourceProvider {

    public String getAllShopsByUserId(){
        return new SQL(){{
            SELECT("*");
            FROM("dp_shops");
            WHERE("u_id = #{id} AND status = 'TRUE'");
        }}.toString();
    }


    public String getUserByUserId(){
        return new SQL(){{
            SELECT("*");
            FROM("dp_users");
            WHERE("user_id = #{userId}");
        }}.toString();
    }

}


