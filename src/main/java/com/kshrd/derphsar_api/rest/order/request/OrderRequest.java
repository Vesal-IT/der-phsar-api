package com.kshrd.derphsar_api.rest.order.request;

public class OrderRequest {
    private int user_id;
    private int shop_id;

    public OrderRequest(){}

    public OrderRequest(int user_id, int shop_id) {
        this.user_id = user_id;
        this.shop_id = shop_id;
    }

    @Override
    public String toString() {
        return "OrderRequest{" +
                "user_id=" + user_id +
                ", shop_id=" + shop_id +
                '}';
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