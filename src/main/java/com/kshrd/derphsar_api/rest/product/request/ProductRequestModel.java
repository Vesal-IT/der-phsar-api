package com.kshrd.derphsar_api.rest.product.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kshrd.derphsar_api.repository.dto.ShopDto;

import java.sql.Date;

public class ProductRequestModel {

    private String name;
    private Double price;
    private String description;
    private boolean status;
//    private boolean isSold;
//    private int viewCount;
    private Double discount;
//    private Date postDate;

    private Object images;
    private Object details;

    private int shop_id;
//    private int promo_id;

    public ProductRequestModel(){}


    public ProductRequestModel(String name, Double price, String description, boolean status,Double discount,  Object images, Object details, int shop_id) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.status = status;
        this.discount = discount;
        this.images = images;
        this.details = details;
        this.shop_id = shop_id;
    }


    @Override
    public String toString() {
        return "ProductRequestModel{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", discount=" + discount +
                ", images=" + images +
                ", details=" + details +
                ", shop_id=" + shop_id +
                '}';
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Object getImages() {
        return images;
    }

    public void setImages(Object images) {
        this.images = images;
    }

    public Object getDetails() {
        return details;
    }

    public void setDetails(Object details) {
        this.details = details;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }
}
