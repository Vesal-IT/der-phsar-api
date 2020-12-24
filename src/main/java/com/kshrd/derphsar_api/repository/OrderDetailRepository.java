package com.kshrd.derphsar_api.repository;


import com.kshrd.derphsar_api.page.Pagination;
import com.kshrd.derphsar_api.repository.dto.*;
import com.kshrd.derphsar_api.repository.filter.OrderDetailFilter;
import com.kshrd.derphsar_api.repository.provider.OrderDetailProvider;
import com.kshrd.derphsar_api.repository.provider.SubResourceProvider;
import com.kshrd.derphsar_api.repository.provider.UserProvider;
import com.kshrd.derphsar_api.rest.user.response.UserResponseModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository {

    //select all order details
    @Select("SELECT * FROM dp_order_detail WHERE status = 'TRUE' ")
    @Results({
            @Result(column = "item_id", property = "itemId"),
            @Result(column = "order_date", property = "orderDate"),
            @Result(column = "order_id", property = "order", many = @Many(select = "getOrder")),
            @Result(column = "pro_id", property = "product", many = @Many(select = "getProduct"))
    })
    List<OrderDetailDto> getOrderDetails();


    //select all order
    @Select("SELECT * FROM dp_order WHERE id = #{order_id}")
    @Results({
            @Result(column = "id" ,property = "id"),
            @Result(column = "order_id" ,property = "orderId"),
            @Result(column = "user_id", property = "user", many = @Many(select = "getUser")),
            @Result(column = "shop_id", property = "shop", many = @Many(select = "getShop")),

    })
    OrderDto getOrder(int order_id);



    //select user
    @Select("SELECT * FROM dp_users WHERE id = #{user_id} AND status = 'TRUE'")
    @Results({
            @Result(column = "user_id" ,property = "userId")
    })
    UserDto getUser(int user_id);




    //get all shop
    @Select("SELECT * FROM dp_shops WHERE id = #{shop_id} AND status = 'TRUE'")
    @Results({
            @Result(column = "shop_id" ,property = "shopId"),
            @Result(column = "promo_id", property = "promotion", many = @Many(select = "getPromotion")),
    })
    ShopDto getShop(int shop_id);



    //select promotion
    @Select("SELECT * FROM dp_promotion WHERE id = #{promo_id} AND status = 'TRUE'")
    @Results({
            @Result(column = "promo_id" ,property = "promoId"),
            @Result(column = "start_rank" ,property = "startRank"),
            @Result(column = "end_rank" ,property = "endRank"),

    })
    PromotionDto getPromotion(int promo_id);



    @Select("SELECT * FROM dp_products WHERE id = #{pro_id} AND status = 'TRUE'")
    @Results({
            @Result(column = "id" ,property = "id"),
            @Result(column = "pro_id" ,property = "proId")
    })
    ProductDto getProduct(int pro_id);


    /////
    @SelectProvider(type = OrderDetailProvider.class, method = "findAllWithFilter")
    @Results(id ="mapOrderDetail", value = {
            @Result(property = "itemId" , column = "item_id"),
            @Result(property = "orderDate" , column = "order_date"),


            @Result(property = "product.discount", column = "discount"),
            @Result(property = "product.proId", column = "proId"),
            @Result(property = "product.name", column = "proName"),
            @Result(property = "product.price", column = "price"),
            @Result(property = "product.soldStatus", column = "is_sold"),


            @Result(property = "order.orderId", column = "orderId"),

            @Result(property = "order.user.userId", column = "userId"),
            @Result(property = "order.user.name", column = "userName"),
            @Result(property = "order.user.phone", column = "phone"),

            @Result(property = "order.shop.shopId", column = "shopId"),
            @Result(property = "order.shop.name", column = "shopName"),


            @Result(property = "order.shop.promotion.promoId", column = "promoId"),
            @Result(property = "order.shop.promotion.title", column = "title"),
            @Result(property = "order.shop.promotion.startRank", column = "start_rank"),
            @Result(property = "order.shop.promotion.endRank", column = "end_rank"),

    })
    List<OrderDetailDto> findAllWithFilter(@Param("userId")int userId,@Param("pagination") Pagination pagination);




    @SelectProvider(type = OrderDetailProvider.class, method = "findOrderDetailByOrderId")
         @ResultMap("mapOrderDetail")
    List<OrderDetailDto> findOrderDetailByOrderId(@Param("orderId")int orderId,@Param("pagination") Pagination pagination);





//    List<OrderDetailDto> findOrderDetailByOrderId(int orderId, @Param(""))

    //delete order detail
    @Delete("UPDATE dp_order_detail SET status = FALSE WHERE item_id = #{order_detail_id}")
    boolean deleteOrderDetail(String order_detail_id);

    //count all orders
    @Select("SELECT COUNT(id) FROM dp_order_detail WHERE status = 'true'")
    int countId();

    //List<OrderDetailDto> findAllWithFilter(OrderDetailFilter orderDetailFilter);


    @Insert("INSERT INTO dp_order_detail (item_id , quatity, status, order_date, is_checkout, order_id, pro_id, image, detail)"+
    "VALUES (#{itemId}, #{quatity}, TRUE, #{orderDate}, FALSE, #{order_id}, #{pro_id}, #{image, jdbcType=OTHER, typeHandler=com.kshrd.derphsar_api.mybatis.JSONTypeHandlerPg}, #{detail, jdbcType=OTHER, typeHandler=com.kshrd.derphsar_api.mybatis.JSONTypeHandlerPg})")
    boolean insertOrderDetail(OrderDetailDto orderDetailDto);


    @SelectProvider(type = OrderDetailProvider.class, method = "getOrderDetailByItemId")
    OrderDetailDto getOrderDetailByItemId(String itemId);

    //update isCheckout to true
    @Update("UPDATE dp_order_detail SET is_checkout = TRUE WHERE order_id = #{orderId}")
    boolean updateIsCheckout(int  orderId, OrderDetailDto orderDetailDto);

}
