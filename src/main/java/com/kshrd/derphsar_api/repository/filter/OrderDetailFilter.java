package com.kshrd.derphsar_api.repository.filter;

public class OrderDetailFilter {
//    private Integer orderId;
    private Integer userId;

    public OrderDetailFilter(){}

    public OrderDetailFilter( Integer userId) {
//        this.orderId = orderId;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "OrderDetailFilter{" +
                ", userId=" + userId +
                '}';
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
