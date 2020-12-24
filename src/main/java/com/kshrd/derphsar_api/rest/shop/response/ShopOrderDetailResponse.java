package com.kshrd.derphsar_api.rest.shop.response;

import com.kshrd.derphsar_api.rest.promotion.response.PromotionOrderDetailResponse;

public class ShopOrderDetailResponse {
    private String shopId;
    private String name;

    private PromotionOrderDetailResponse promotion;

    public ShopOrderDetailResponse(){

    }

    public ShopOrderDetailResponse(String shopId, String name, PromotionOrderDetailResponse promotion) {
        this.shopId = shopId;
        this.name = name;
        this.promotion = promotion;
    }

    @Override
    public String toString() {
        return "ShopOrderDetailResponse{" +
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

    public PromotionOrderDetailResponse getPromotion() {
        return promotion;
    }

    public void setPromotion(PromotionOrderDetailResponse promotion) {
        this.promotion = promotion;
    }
}
