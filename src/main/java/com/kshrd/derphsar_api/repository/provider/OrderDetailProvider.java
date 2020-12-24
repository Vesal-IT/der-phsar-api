package com.kshrd.derphsar_api.repository.provider;

import com.kshrd.derphsar_api.page.Pagination;
import com.kshrd.derphsar_api.repository.dto.OrderDetailDto;
import com.kshrd.derphsar_api.repository.dto.OrderDto;
import com.kshrd.derphsar_api.repository.filter.OrderDetailFilter;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class OrderDetailProvider {

//    public String findAllWithFilter(@Param("filter")OrderDetailFilter orderDetailFilter,@Param("pagination") Pagination pagination){
//        return new SQL(){{
//            SELECT("od.* , o.order_id AS orderId, pro.id, pro.pro_id AS proId, pro.name AS proName, pro.price, pro.is_sold, u.user_id AS userId, u.name AS userName, u.phone, sh.shop_id AS shopId, sh.name AS shopName, promo.promo_id AS promoId, promo.title, promo.start_rank, promo.end_rank");
//            FROM("dp_order_detail as od");
//            INNER_JOIN("dp_order as o ON o.id = od.order_id ");
//            INNER_JOIN("dp_products as pro ON od.pro_id = pro.id");
//            INNER_JOIN("dp_users as u ON u.id = o.user_id");
//            INNER_JOIN("dp_shops as sh ON sh.id = o.shop_id");
//            INNER_JOIN("dp_promotion as promo ON promo.shop_id = sh.id");
//            if (orderDetailFilter.getOrderId() != null && orderDetailFilter.getUserId() != null)
//                WHERE("o.user_id =  #{filter.userId} AND  o.id = #{filter.orderId}");
//             LIMIT(pagination.getLimit());
//             OFFSET(pagination.getOffset());
//        }}.toString();
//    }



    public String findAllWithFilter(@Param("userId") int userId, @Param("pagination") Pagination pagination){
        return new SQL(){{
            SELECT("od.*, od.status , o.order_id AS orderId, pro.id, pro.pro_id AS proId, pro.name AS proName,,pro.discount, pro.price, pro.is_sold, u.user_id AS userId, u.name AS userName, u.phone, sh.shop_id AS shopId, sh.name AS shopName , promo.promo_id AS promoId, promo.title, promo.start_rank, promo.end_rank");
            FROM("dp_order_detail AS od");
            INNER_JOIN("dp_order AS o ON o.id = od.order_id");
            INNER_JOIN("dp_products AS pro ON od.pro_id = pro.id");
            INNER_JOIN("dp_users AS u ON u.id = o.user_id");
            INNER_JOIN("dp_shops AS sh ON sh.id = o.shop_id");
            INNER_JOIN("dp_promotion AS promo ON promo.id = pro.promo_id");
            WHERE("o.user_id = #{userId} od.status = 'true' AND od.is_checkout = 'false'");
            LIMIT(pagination.getLimit());
            OFFSET(pagination.getOffset());
        }}.toString();
    }


    public String findOrderDetailByOrderId(@Param("orderId") int orderId, @Param("pagination") Pagination pagination){
        return new SQL(){{
            SELECT("od.*, od.status , o.order_id AS orderId, pro.id, pro.pro_id AS proId,pro.discount, pro.name AS proName, pro.price, pro.is_sold, u.user_id AS userId, u.name AS userName, u.phone, sh.shop_id AS shopId, sh.name AS shopName , promo.promo_id AS promoId, promo.title, promo.start_rank, promo.end_rank");
            FROM("dp_order_detail AS od");
            INNER_JOIN("dp_order AS o ON o.id = od.order_id");
            INNER_JOIN("dp_products AS pro ON od.pro_id = pro.id");
            INNER_JOIN("dp_users AS u ON u.id = o.user_id");
            INNER_JOIN("dp_shops AS sh ON sh.id = o.shop_id");
            INNER_JOIN("dp_promotion AS promo ON promo.id = pro.promo_id");
            WHERE("o.id = #{orderId} AND od.status = 'true' AND od.is_checkout = 'false'");
            LIMIT(pagination.getLimit());
            OFFSET(pagination.getOffset());
        }}.toString();
    }







//    public String findOrderDetailByOrderId(int orderId, @Param("pagination") Pagination pagination){
//        return new SQL(){{
//            SELECT("od.* , o.order_id AS orderId, pro.id, pro.pro_id AS proId, pro.name AS proName, pro.price, pro.is_sold, u.user_id AS userId, u.name AS userName, u.phone, sh.shop_id AS shopId, sh.name AS shopName, promo.promo_id AS promoId, promo.title, promo.start_rank, promo.end_rank");
//            FROM("dp_order_detail as od");
//            INNER_JOIN("dp_order as o ON o.id = od.order_id ");
//            INNER_JOIN("dp_products as pro ON od.pro_id = pro.id");
//            INNER_JOIN("dp_users as u ON u.id = o.user_id");
//            INNER_JOIN("dp_shops as sh ON sh.id = o.shop_id");
//            INNER_JOIN("dp_promotion as promo ON promo.shop_id = sh.id");
//            WHERE("o.order_id =  #{orderId}");
//            LIMIT(pagination.getLimit());
//            OFFSET(pagination.getOffset());
//        }}.toString();
//    }


    public String getOrderDetailByItemId(String itemId){
        return new SQL(){{
            SELECT("*");
            FROM("dp_order_detail");
            WHERE("item_id = #{itemId}");
        }}.toString();
    }
}
