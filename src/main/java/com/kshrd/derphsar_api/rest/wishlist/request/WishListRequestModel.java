package com.kshrd.derphsar_api.rest.wishlist.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class WishListRequestModel {

    @JsonIgnore
    private String wishlistId;
    @JsonIgnore
    private Date favDate;
    private int u_id;
    private int pro_id;
    @JsonIgnore
    private boolean status;

    public WishListRequestModel() {
    }

    public WishListRequestModel(String wishlistId, Date favDate, int u_id, int pro_id, boolean status) {
        this.wishlistId = wishlistId;
        this.favDate = favDate;
        this.u_id = u_id;
        this.pro_id = pro_id;
        this.status = status;
    }


    @Override
    public String toString() {
        return "WishListRequestModel{" +
                "wishlistId='" + wishlistId + '\'' +
                ", favDate=" + favDate +
                ", u_id=" + u_id +
                ", pro_id=" + pro_id +
                ", status=" + status +
                '}';
    }


    public String getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(String wishlistId) {
        this.wishlistId = wishlistId;
    }

    public Date getFavDate() {
        return favDate;
    }

    public void setFavDate(Date favDate) {
        this.favDate = favDate;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
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
}
