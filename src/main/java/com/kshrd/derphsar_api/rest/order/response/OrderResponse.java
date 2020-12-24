package com.kshrd.derphsar_api.rest.order.response;

import com.kshrd.derphsar_api.rest.orderdetail.response.OrderDetailAllOrderResponse;
import com.kshrd.derphsar_api.rest.orderdetail.response.OrderDetailResponse;
import com.kshrd.derphsar_api.rest.shop.response.ShopAllOrderResponse;
import com.kshrd.derphsar_api.rest.shop.response.ShopOrderDetailResponse;
import com.kshrd.derphsar_api.rest.user.response.UserOrderDetailResponse;

import java.util.List;


public class OrderResponse {

    private int id;
    private String orderId;
//    private int user_id;
//    private int shop_id;

    private UserOrderDetailResponse user;
    private ShopAllOrderResponse shop;
    private OrderDetailAllOrderResponse orderDetail;

    public OrderResponse(){}


    public OrderResponse(int id, String orderId, UserOrderDetailResponse user, ShopAllOrderResponse shop, OrderDetailAllOrderResponse orderDetail) {
        this.id = id;
        this.orderId = orderId;
        this.user = user;
        this.shop = shop;
        this.orderDetail = orderDetail;
    }

    @Override
    public String toString() {
        return "OrderResponse{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", user=" + user +
                ", shop=" + shop +
                ", orderDetail=" + orderDetail +
                '}';
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public UserOrderDetailResponse getUser() {
        return user;
    }

    public void setUser(UserOrderDetailResponse user) {
        this.user = user;
    }

    public ShopAllOrderResponse getShop() {
        return shop;
    }

    public void setShop(ShopAllOrderResponse shop) {
        this.shop = shop;
    }

    public OrderDetailAllOrderResponse getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetailAllOrderResponse orderDetail) {
        this.orderDetail = orderDetail;
    }
}
