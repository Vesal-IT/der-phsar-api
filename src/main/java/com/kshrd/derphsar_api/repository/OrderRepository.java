package com.kshrd.derphsar_api.repository;

import com.kshrd.derphsar_api.page.Pagination;
import com.kshrd.derphsar_api.repository.dto.OrderDetailDto;
import com.kshrd.derphsar_api.repository.dto.OrderDto;
import com.kshrd.derphsar_api.repository.provider.OrderDetailProvider;
import com.kshrd.derphsar_api.repository.provider.OrderProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository {

    @Select("SELECT DISTINCT(o.order_id) , o.id AS oId,u.user_id, u.name AS userName, u.phone, sh.shop_id, sh.name AS shopName,ord.status,ord.is_checkout, ord.order_date,sum(quatity) qty\n" +
            "            FROM dp_order AS o\n" +
            "INNER JOIN dp_users AS u ON u.id = o.user_id\n" +
            "INNER JOIN dp_shops AS sh ON sh.id = o.shop_id\n" +
            "INNER JOIN dp_order_detail AS ord ON ord.order_id = o.id\n" +
            "WHERE o.shop_id = #{shopId} AND ord.is_checkout = 'TRUE' AND ord.status = 'TRUE'\n" +
            "GROUP BY o.order_id,o.id,u.user_id, u.name, u.phone, sh.shop_id, sh.name,ord.status,ord.is_checkout, ord.order_date\n" +
            "ORDER BY u.name DESC")
        @Results( id ="OrderMap", value = {
                @Result(property = "id", column = "oId"),
                @Result(property = "orderId", column = "order_id"),

                @Result(property = "user.name", column = "userName"),
                @Result(property = "user.userId", column = "user_id"),
                @Result(property = "user.phone", column = "phone"),

                @Result(property = "shop.shopId", column = "shop_id"),
                @Result(property = "shop.name", column = "shopName"),

                @Result(property = "orderDetail.checkoutStatus", column = "is_checkout"),
                @Result(property = "orderDetail.status", column = "status"),
                @Result(property = "orderDetail.orderDate", column = "order_date"),
                @Result(property = "orderDetail.quatity", column = "qty"),

        })
    List<OrderDto> getAllOrderByShopId(int shopId);


    @SelectProvider(type = OrderProvider.class, method = "orderDetailByOrderId")
    @Results({
            @Result(property = "itemId", column = "item_id"),
            @Result(property = "quatity", column = "quatity"),
            @Result(property = "orderDate", column = "order_date"),
    })
    List<OrderDetailDto> getAllOrderDetailByOrderId(int orderId);


    @Select("SELECT sum(quatity) AS QTY FROM dp_order_detail WHERE order_id = #{orderId}")
    int getSumQty(int orderId);



//    @Select("\tSELECT DISTINCT( o.order_id), u.name AS userName, u.phone, ord.order_date\n" +
//            "            FROM dp_order AS o\n" +
//            "            INNER JOIN dp_users AS u ON u.id = o.user_id\n" +
//            "            INNER JOIN dp_shops AS sh ON sh.id = o.shop_id\n" +
//            "            INNER JOIN dp_order_detail AS ord ON ord.order_id = o.id\n" +
//            "\t\t\t\t\t\tWHERE ord.status = 'TRUE' AND ord.is_checkout = 'TRUE'\n" +
//            "\t\t\t\t\t\tORDER BY ord.order_date DESC\n" +
//            "\t\t\t\t\t\tLIMIT 5")

    @Select("SELECT DISTINCT(o.order_id),o.id AS oId, u.user_id, u.name AS userName, u.phone, sh.shop_id, sh.name AS shopName,ord.status,ord.is_checkout, ord.order_date,sum(quatity) qty\n" +
            "            FROM dp_order AS o\n" +
            "INNER JOIN dp_users AS u ON u.id = o.user_id\n" +
            "INNER JOIN dp_shops AS sh ON sh.id = o.shop_id\n" +
            "INNER JOIN dp_order_detail AS ord ON ord.order_id = o.id\n" +
            "WHERE ord.is_checkout = 'TRUE' AND ord.status = 'TRUE'\n" +
            "GROUP BY o.order_id,o.id, u.user_id, u.name, u.phone, sh.shop_id, sh.name,ord.status,ord.is_checkout, ord.order_date\n" +
            "ORDER BY ord.order_date DESC\n" +
            "LIMIT 5")
            @ResultMap("OrderMap")
    List<OrderDto> getOrdersLatestFiveRecords();



    @SelectProvider(type = OrderProvider.class, method = "getAllOrdersHistoryByUserId")
    @Results({
            @Result(property = "orderDetail.product.name", column = "proName"),
            @Result(property = "orderDetail.product.price", column = "proPrice"),

            @Result(property = "orderDetail.product.images", column = "proImage"),

            @Result(property = "shop.name", column = "shopName"),

            @Result(property = "orderDetail.quatity", column = "orderQuantity"),
            @Result(property = "orderDetail.itemId", column = "item_id"),
            @Result(property = "orderDetail.orderDate", column = "orderDate"),
            @Result(property = "shop.status", column = "status"),
            @Result(property = "orderDetail.checkoutStatus", column = "is_checkout"),

    })
    List<OrderDto> getAllOrdersHistoryByUserId(int uId, @Param("pagination") Pagination pagination);

    @SelectProvider(type = OrderProvider.class, method = "countId")
    int countId();


    @SelectProvider(type = OrderProvider.class, method = "getOrderByOrderId")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "order_id", property = "orderId"),
    }
    )
    OrderDto getOrderByOrderId(@Param("orderId") String orderId);


}
