package com.kshrd.derphsar_api.rest.promotion.request;

import java.sql.Date;

public class PromotionRequestModel {

    private String title;
    private boolean isApply;
    private Date startDate;
    private Date endDate;
    private String cover;
    private double startRank;
    private double endRank;
    private int shop_id;
    private boolean status;

    public PromotionRequestModel(){}

    public PromotionRequestModel(boolean status,String title, boolean isApply, Date startDate, Date endDate, String cover, double startRank, double endRank, int shop_id) {

        this.status = status;
        this.title = title;
        this.isApply = isApply;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cover = cover;
        this.startRank = startRank;
        this.endRank = endRank;
        this.shop_id = shop_id;
    }


    @Override
    public String toString() {
        return "PromotionRequestModel{" +
                "title='" + title + '\'' +
                ", isApply=" + isApply +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", cover='" + cover + '\'' +
                ", startRank=" + startRank +
                ", endRank=" + endRank +
                ", shop_id=" + shop_id +
                ", status=" + status +
                '}';
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isApply() {
        return isApply;
    }

    public void setApply(boolean apply) {
        isApply = apply;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }



    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
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

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }
}
