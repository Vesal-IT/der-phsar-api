package com.kshrd.derphsar_api.rest.shop.response;

import com.kshrd.derphsar_api.rest.promotion.response.PromotionWishListResponse;

public class ShopWishListResponse {
    private String shopId;
    private String name;

    private PromotionWishListResponse promotion;

    public ShopWishListResponse(){

    }

    public ShopWishListResponse(String shopId, String name, PromotionWishListResponse promotion) {
        this.shopId = shopId;
        this.name = name;
        this.promotion = promotion;
    }

    @Override
    public String toString() {
        return "ShopWishListResponse{" +
                "shopId='" + shopId + '\'' +
                ", name='" + name + '\'' +
                ", promotion=" + promotion +
                '}';
    }


    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PromotionWishListResponse getPromotion() {
        return promotion;
    }

    public void setPromotion(PromotionWishListResponse promotion) {
        this.promotion = promotion;
    }
}
