package com.kshrd.derphsar_api.rest.product.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kshrd.derphsar_api.repository.dto.ShopDto;
import com.kshrd.derphsar_api.rest.promotion.response.PromotionProductResponse;
import com.kshrd.derphsar_api.rest.shop.response.ShopProductResponse;
import com.kshrd.derphsar_api.rest.shop.response.ShopResponseModel;

import java.sql.Date;

public class ProductResponseModel {

    private int id;
    private String proId;
    private String name;
    private Double price;
    private String description;
    private boolean status;
    private boolean isSold;
    private int viewCount;
    private Date postDate;
    private Double discount;


    private Object images;
    private Object details;

    private ShopResponseModel shop;
    private PromotionProductResponse promotion;


    public ProductResponseModel(){}


    public ProductResponseModel(int id, String proId, String name, Double price, String description, boolean status, boolean isSold, int viewCount, Date postDate, Double discount, Object images, Object details, ShopResponseModel shop, PromotionProductResponse promotion) {
        this.id = id;
        this.proId = proId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.status = status;
        this.isSold = isSold;
        this.viewCount = viewCount;
        this.postDate = postDate;
        this.discount = discount;
        this.images = images;
        this.details = details;
        this.shop = shop;
        this.promotion = promotion;
    }

    @Override
    public String toString() {
        return "ProductResponseModel{" +
                "id=" + id +
                ", proId='" + proId + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", isSold=" + isSold +
                ", viewCount=" + viewCount +
                ", postDate=" + postDate +
                ", discount=" + discount +
                ", images=" + images +
                ", details=" + details +
                ", shop=" + shop +
                ", promotion=" + promotion +
                '}';
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
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

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
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

    public ShopResponseModel getShop() {
        return shop;
    }

    public void setShop(ShopResponseModel shop) {
        this.shop = shop;
    }

    public PromotionProductResponse getPromotion() {
        return promotion;
    }

    public void setPromotion(PromotionProductResponse promotion) {
        this.promotion = promotion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
