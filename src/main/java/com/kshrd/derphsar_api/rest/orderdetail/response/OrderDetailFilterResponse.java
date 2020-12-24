package com.kshrd.derphsar_api.rest.orderdetail.response;

import com.kshrd.derphsar_api.rest.order.response.OrderResponse;
import com.kshrd.derphsar_api.rest.product.response.ProductOrderDetailResponse;

import java.sql.Date;

public class OrderDetailFilterResponse {

    private String itemId;
    private double quatity;
    private Object detail;
    private Object image;
    private int order_id;
    private int pro_id;


    private boolean status;
    private Date orderDate;

    private ProductOrderDetailResponse product;
    private OrderResponse order;

    public OrderDetailFilterResponse(){}

    public OrderDetailFilterResponse(String itemId, double quatity, Object detail, Object image, int order_id, int pro_id, boolean status, Date orderDate, ProductOrderDetailResponse product, OrderResponse order) {
        this.itemId = itemId;
        this.quatity = quatity;
        this.detail = detail;
        this.image = image;
        this.order_id = order_id;
        this.pro_id = pro_id;
        this.status = status;
        this.orderDate = orderDate;
        this.product = product;
        this.order = order;
    }


    @Override
    public String toString() {
        return "OrderDetailFilterResponse{" +
                "itemId='" + itemId + '\'' +
                ", quatity=" + quatity +
                ", detail=" + detail +
                ", image=" + image +
                ", order_id=" + order_id +
                ", pro_id=" + pro_id +
                ", status=" + status +
                ", orderDate=" + orderDate +
                ", product=" + product +
                ", order=" + order +
                '}';
    }


    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
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

    public ProductOrderDetailResponse getProduct() {
        return product;
    }

    public void setProduct(ProductOrderDetailResponse product) {
        this.product = product;
    }

    public OrderResponse getOrder() {
        return order;
    }

    public void setOrder(OrderResponse order) {
        this.order = order;
    }
}
