package com.kshrd.derphsar_api.rest.promotion.response;

public class PromotionOrderDetailResponse {
    private String promoId;
    private String title;
    private double startRank;
    private double endRank;

    public PromotionOrderDetailResponse(){}

    public PromotionOrderDetailResponse(String promoId, String title, double startRank, double endRank) {
        this.promoId = promoId;
        this.title = title;
        this.startRank = startRank;
        this.endRank = endRank;
    }

    @Override
    public String toString() {
        return "PromotionOrderDetailResponse{" +
                "promoId='" + promoId + '\'' +
                ", title='" + title + '\'' +
                ", startRank=" + startRank +
                ", endRank=" + endRank +
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

    public double getStartRank() {
        return startRank;
    }

    public void setStartRank(double startRank) {
        this.startRank = startRank;
    }

    public double getEndRank() {
        return endRank;
    }

    public void setEndRank(double endRank) {
        this.endRank = endRank;
    }
}