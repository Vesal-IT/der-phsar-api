package com.kshrd.derphsar_api.rest.shop.request;

import java.util.Date;

public class ShopRequestModel {

    private String name;
    private String address;
    private String description;
    private String profile;
    private String cover;
    private boolean openStatus;
    private int u_id;
    private int cat_id;
    private Date openTime;
    private Date closeTime;

    public ShopRequestModel() {}

    public ShopRequestModel(String name, String address, String description, String profile, String cover, boolean openStatus, int u_id, int cat_id, Date openTime, Date closeTime) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.profile = profile;
        this.cover = cover;
        this.openStatus = openStatus;
        this.u_id = u_id;
        this.cat_id = cat_id;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }

    @Override
    public String toString() {
        return "ShopRequestModel{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", profile='" + profile + '\'' +
                ", cover='" + cover + '\'' +
                ", openStatus=" + openStatus +
                ", u_id=" + u_id +
                ", cat_id=" + cat_id +
                ", openTime=" + openTime +
                ", closeTime=" + closeTime +
                '}';
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
}
