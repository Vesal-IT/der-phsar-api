package com.kshrd.derphsar_api.rest.order.response;

import com.kshrd.derphsar_api.rest.orderdetail.response.OrderDetailResponse;
import com.kshrd.derphsar_api.rest.shop.response.ShopResponseModel;
import com.kshrd.derphsar_api.rest.user.response.UserByShopResponse;

public class OrderByShopResponse {

    private int id;
    private String orderId;
    private UserByShopResponse user;
    private ShopResponseModel shop;
    private OrderDetailResponse orderDetail;

    public OrderByShopResponse(){}

    public OrderByShopResponse(int id, String orderId, UserByShopResponse user, ShopResponseModel shop, OrderDetailResponse orderDetail) {
        this.id = id;
        this.orderId = orderId;
        this.user = user;
        this.shop = shop;
        this.orderDetail = orderDetail;
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

    public UserByShopResponse getUser() {
        return user;
    }

    public void setUser(UserByShopResponse user) {
        this.user = user;
    }

    public ShopResponseModel getShop() {
        return shop;
    }

    public void setShop(ShopResponseModel shop) {
        this.shop = shop;
    }

    public OrderDetailResponse getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetailResponse orderDetail) {
        this.orderDetail = orderDetail;
    }


    @Override
    public String toString() {
        return "OrderByShopResponse{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", user=" + user +
                ", shop=" + shop +
                ", orderDetail=" + orderDetail +
                '}';
    }
}
