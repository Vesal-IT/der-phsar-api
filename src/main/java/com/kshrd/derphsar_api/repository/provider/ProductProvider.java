package com.kshrd.derphsar_api.repository.provider;

import com.kshrd.derphsar_api.page.Pagination;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class ProductProvider{

    //get all products
//    public String getProducts() {
//        return new SQL(){{
//            SELECT("id, pro_id, pro_name, pro_price, pro_description, pro_status, pro_is_sold, pro_view_count,\n" +
//                    "pro_img, pro_details ->> 'color' AS color, pro_details ->> 'size' AS size, shop_id");
//            FROM("dp_products");
//        }}.toString();
//    }


    //get all products
    public String getProducts(@Param("pagination") Pagination pagination) {
        return new SQL(){{
            SELECT("*");
            FROM("dp_products");
            WHERE("status = TRUE");
            LIMIT("#{pagination.limit}  OFFSET #{pagination.offset}");
        }}.toString();
    }


    public String countId(){
        return new SQL(){{
            SELECT("COUNT(id)");
            FROM("dp_products");
            WHERE("status = 'TRUE'");
        }}.toString();
    }


    public String getAllProductsByUserId(int uId, @Param("pagination") Pagination pagination){
        return new SQL(){{
            SELECT("pro.pro_id , pro.name AS proName, sh.name AS shopName, sh.status , pro.price, pro.view_count, pro.images AS images");
            FROM ("dp_products AS pro");
            INNER_JOIN ("dp_shops AS sh ON pro.shop_id = sh.id");
            INNER_JOIN ("dp_users AS u ON u.id = sh.u_id");
            WHERE ("u.id =  #{uId}");
            LIMIT(pagination.getLimit());
            OFFSET(pagination.getOffset());
        }}.toString();
    }


    public String getViewCount(String proId){
        return new SQL(){{
            SELECT("view_count");
            FROM("dp_products");
            WHERE("pro_id = #{proId}");
        }}.toString();
    }

    public String updateViewCount(){
        return new SQL(){{
            UPDATE("dp_products");
            SET("view_count = #{viewCount}");
            WHERE("pro_id = #{proId}");
        }}.toString();
    }

}
