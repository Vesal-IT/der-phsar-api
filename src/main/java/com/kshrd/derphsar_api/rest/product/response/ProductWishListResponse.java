package com.kshrd.derphsar_api.rest.product.response;

import com.kshrd.derphsar_api.repository.dto.ShopDto;
import com.kshrd.derphsar_api.rest.shop.response.ShopWishListResponse;

public class ProductWishListResponse {
    private String proId;
    private String name;
    private Object images;
    private Double price;

    private ShopWishListResponse shop;

    public ProductWishListResponse(){

    }

    public ProductWishListResponse(String proId, String name, Object images, Double price, ShopWishListResponse shop) {
        this.proId = proId;
        this.name = name;
        this.images = images;
        this.price = price;
        this.shop = shop;
    }

    @Override
    public String toString() {
        return "ProductWishListResponse{" +
                "proId='" + proId + '\'' +
                ", name='" + name + '\'' +
                ", images=" + images +
                ", price=" + price +
                ", shop=" + shop +
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

    public Object getImages() {
        return images;
    }

    public void setImages(Object images) {
        this.images = images;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ShopWishListResponse getShop() {
        return shop;
    }

    public void setShop(ShopWishListResponse shop) {
        this.shop = shop;
    }
}
