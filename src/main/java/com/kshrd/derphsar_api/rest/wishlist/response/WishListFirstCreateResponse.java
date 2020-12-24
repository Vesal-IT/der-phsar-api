package com.kshrd.derphsar_api.rest.wishlist.response;

import java.util.Date;

public class WishListFirstCreateResponse {

    private String wishlistId;
    private Date favDate;
    private boolean status;
    private int u_id;
    private int pro_id;

    public WishListFirstCreateResponse(){
    }

    public WishListFirstCreateResponse(String wishlistId, Date favDate, boolean status, int user_id, int pro_id) {
        this.wishlistId = wishlistId;
        this.favDate = favDate;
        this.status = status;
        this.u_id = user_id;
        this.pro_id = pro_id;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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


    @Override
    public String toString() {
        return "WishListFirstCreateResponse{" +
                "wishlistId='" + wishlistId + '\'' +
                ", favDate=" + favDate +
                ", status=" + status +
                ", user_id=" + u_id +
                ", pro_id=" + pro_id +
                '}';
    }
}
