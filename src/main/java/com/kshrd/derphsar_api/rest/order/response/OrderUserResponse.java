package com.kshrd.derphsar_api.rest.order.response;

public class OrderUserResponse {

    private int id;
    private String orderId;
    private int userId;

    public OrderUserResponse(){}

    public OrderUserResponse(int id, String orderId, int userId) {
        this.id = id;
        this.orderId = orderId;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "OrderUserResponse{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", userId=" + userId +
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
