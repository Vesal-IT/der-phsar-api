package com.kshrd.derphsar_api.rest.promotion.response;

public class PromotionWishListResponse {

    private String promoId;
    private String title;
    private String startRank;
    private String endRank;

    public PromotionWishListResponse(){}

    public PromotionWishListResponse(String promoId, String title, String startRank, String endRank) {
        this.promoId = promoId;
        this.title = title;
        this.startRank = startRank;
        this.endRank = endRank;
    }


    @Override
    public String toString() {
        return "PromotionWishListResponse{" +
                "promoId='" + promoId + '\'' +
                ", title='" + title + '\'' +
                ", startRank='" + startRank + '\'' +
                ", endRank='" + endRank + '\'' +
                '}';
    }

    public String getPromoId() {
        return promoId;
    }

    public void setPromoId(String promoId) {
        this.promoId = promoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartRank() {
        return startRank;
    }

    public void setStartRank(String startRank) {
        this.startRank = startRank;
    }

    public String getEndRank() {
        return endRank;
    }

    public void setEndRank(String endRank) {
        this.endRank = endRank;
    }
}

