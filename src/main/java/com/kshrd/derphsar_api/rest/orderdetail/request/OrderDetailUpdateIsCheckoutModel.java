package com.kshrd.derphsar_api.rest.orderdetail.request;

public class OrderDetailUpdateIsCheckoutModel {
    private boolean checkoutStatus;

    public OrderDetailUpdateIsCheckoutModel(){}

    public OrderDetailUpdateIsCheckoutModel(boolean checkoutStatus) {
        this.checkoutStatus = checkoutStatus;
    }

    @Override
    public String toString() {
        return "OrderDetailUpdateIsCheckoutModel{" +
                "checkoutStatus=" + checkoutStatus +
                '}';
    }

    public boolean isCheckoutStatus() {
        return checkoutStatus;
    }

    public void setCheckoutStatus(boolean checkoutStatus) {
        this.checkoutStatus = checkoutStatus;
    }
}
