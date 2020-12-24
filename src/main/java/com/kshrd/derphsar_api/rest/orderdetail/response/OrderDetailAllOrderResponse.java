package com.kshrd.derphsar_api.rest.orderdetail.response;

import java.sql.Date;

public class OrderDetailAllOrderResponse {

    private boolean checkoutStatus;
    private boolean status;
    private Date orderDate;
    private double quatity;


    public OrderDetailAllOrderResponse(){}

    public double getQuatity() {
        return quatity;
    }

    public void setQuatity(double quatity) {
        this.quatity = quatity;
    }

    @Override
    public String toString() {
        return "OrderDetailAllOrderResponse{" +
                "checkoutStatus=" + checkoutStatus +
                ", status=" + status +
                ", orderDate=" + orderDate +
                ", quatity=" + quatity +
                '}';
    }

    public OrderDetailAllOrderResponse(boolean checkoutStatus, boolean status, Date orderDate, double quatity) {
        this.checkoutStatus = checkoutStatus;
        this.status = status;
        this.orderDate = orderDate;
        this.quatity = quatity;
    }


    public boolean isCheckoutStatus() {
        return checkoutStatus;
    }

    public void setCheckoutStatus(boolean checkoutStatus) {
        this.checkoutStatus = checkoutStatus;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
