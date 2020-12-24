package com.kshrd.derphsar_api.rest.order.response;

public class OrderOneResponse {

    private int id;
    private String orderId;

    private int user_id;
    private int shop_id;

    public OrderOneResponse(){}

    public OrderOneResponse(int id, String orderId, int user_id, int shop_id) {
        this.id = id;
        this.orderId = orderId;
        this.user_id = user_id;
        this.shop_id = shop_id;
    }

    @Override
    public String toString() {
        return "OrderOneResponse{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", user_id=" + user_id +
                ", shop_id=" + shop_id +
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }
}
