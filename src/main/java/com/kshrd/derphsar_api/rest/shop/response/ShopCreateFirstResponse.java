package com.kshrd.derphsar_api.rest.shop.response;

import java.util.Date;

public class ShopCreateFirstResponse {

    private String shopId;
    private String name;
    private String address;
    private String description;
    private String profile;
    private String cover;
    private boolean openStatus;
    private boolean status;
    private int u_id;
    private int cat_id;
    private Date openTime;
    private Date closeTime;

    public ShopCreateFirstResponse(){}

    public ShopCreateFirstResponse(String shopId, String name, String address, String description, String profile, String cover, boolean openStatus, boolean status, int u_id, int cat_id, Date openTime, Date closeTime) {
        this.shopId = shopId;
        this.name = name;
        this.address = address;
        this.description = description;
        this.profile = profile;
        this.cover = cover;
        this.openStatus = openStatus;
        this.status = status;
        this.u_id = u_id;
        this.cat_id = cat_id;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }

    @Override
    public String toString() {
        return "ShopCreateFirstResponse{" +
                "shopId='" + shopId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", profile='" + profile + '\'' +
                ", cover='" + cover + '\'' +
                ", openStatus=" + openStatus +
                ", status=" + status +
                ", u_id=" + u_id +
                ", cat_id=" + cat_id +
                ", openTime=" + openTime +
                ", closeTime=" + closeTime +
                '}';
    }


    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public boolean isOpenStatus() {
        return openStatus;
    }

    public void setOpenStatus(boolean openStatus) {
        this.openStatus = openStatus;
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

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }
}
