package com.kshrd.derphsar_api.rest.orderdetail;

import java.sql.Date;

public class OrderDetailFirstRequest {

    private double quatity;
    private Object detail;
    private Object image;
    private int order_id;
    private int pro_id;

    public OrderDetailFirstRequest(double quatity, Object detail, Object image, int order_id, int pro_id) {
        this.quatity = quatity;
        this.detail = detail;
        this.image = image;
        this.order_id = order_id;
        this.pro_id = pro_id;
    }


    @Override
    public String toString() {
        return "OrderDetailFirstRequest{" +
                "quatity=" + quatity +
                ", detail=" + detail +
                ", image=" + image +
                ", order_id=" + order_id +
                ", pro_id=" + pro_id +
                '}';
    }


    public double getQuatity() {
        return quatity;
    }

    public void setQuatity(double quatity) {
        this.quatity = quatity;
    }

    public Object getDetail() {
        return detail;
    }

    public void setDetail(Object detail) {
        this.detail = detail;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }


    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getPro_id() {
        return pro_id;
    }

    public void setPro_id(int pro_id) {
        this.pro_id = pro_id;
    }
}
