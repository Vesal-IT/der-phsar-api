package com.kshrd.derphsar_api.repository.dto;


import java.sql.Date;

public class OrderDetailDto {

    private int id;
    private String itemId;
    private double quatity;
    private Object detail;
    private Object image;
    private boolean status;
    private Date orderDate;
    private boolean checkoutStatus;
    private int order_id;
    private int pro_id;

    private ProductDto product;
    private OrderDto order;

    public OrderDetailDto(){}

    public OrderDetailDto(int id, String itemId, double quatity, Object detail, Object image, boolean status, Date orderDate, boolean checkoutStatus, int order_id, int pro_id, ProductDto product, OrderDto order) {
        this.id = id;
        this.itemId = itemId;
        this.quatity = quatity;
        this.detail = detail;
        this.image = image;
        this.status = status;
        this.orderDate = orderDate;
        this.checkoutStatus = checkoutStatus;
        this.order_id = order_id;
        this.pro_id = pro_id;
        this.product = product;
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderDetailDto{" +
                "id=" + id +
                ", itemId='" + itemId + '\'' +
                ", quatity=" + quatity +
                ", detail=" + detail +
                ", image=" + image +
                ", status=" + status +
                ", orderDate=" + orderDate +
                ", checkoutStatus=" + checkoutStatus +
                ", order_id=" + order_id +
                ", pro_id=" + pro_id +
                ", product=" + product +
                ", order=" + order +
                '}';
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isCheckoutStatus() {
        return checkoutStatus;
    }

    public void setCheckoutStatus(boolean checkoutStatus) {
        this.checkoutStatus = checkoutStatus;
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

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public OrderDto getOrder() {
        return order;
    }

    public void setOrder(OrderDto order) {
        this.order = order;
    }
}
