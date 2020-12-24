package com.kshrd.derphsar_api.rest.shop.response;

import com.kshrd.derphsar_api.rest.category.response.CategoryResponseModel;
import com.kshrd.derphsar_api.rest.user.response.UserByShopResponse;
import com.kshrd.derphsar_api.rest.user.response.UserResponseModel;

import java.util.Date;

public class ShopPromotionResponse {
    private String shopid;
    private String name;
    private String address;
    private String description;
    private String profile;
    private String cover;
    private boolean openStatus;
    private boolean status;
    private int cat_id;
    private int u_id;
    private Date openTime;
    private Date closeTime;

    private UserByShopResponse user;
    private CategoryResponseModel category;

    public ShopPromotionResponse(){}


    public ShopPromotionResponse(String shopid, String name, String address, String description, String profile, String cover, boolean openStatus, boolean status, int cat_id, int u_id, Date openTime, Date closeTime, UserByShopResponse user, CategoryResponseModel category) {
        this.shopid = shopid;
        this.name = name;
        this.address = address;
        this.description = description;
        this.profile = profile;
        this.cover = cover;
        this.openStatus = openStatus;
        this.status = status;
        this.cat_id = cat_id;
        this.u_id = u_id;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.user = user;
        this.category = category;
    }


    @Override
    public String toString() {
        return "ShopPromotionResponse{" +
                "shopid='" + shopid + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", profile='" + profile + '\'' +
                ", cover='" + cover + '\'' +
                ", openStatus=" + openStatus +
                ", status=" + status +
                ", cat_id=" + cat_id +
                ", u_id=" + u_id +
                ", openTime=" + openTime +
                ", closeTime=" + closeTime +
                ", user=" + user +
                ", category=" + category +
                '}';
    }

    public UserByShopResponse getUser() {
        return user;
    }

    public void setUser(UserByShopResponse user) {
        this.user = user;
    }

    public CategoryResponseModel getCategory() {
        return category;
    }

    public void setCategory(CategoryResponseModel category) {
        this.category = category;
    }

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
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

    public boolean isOpenStatus() {
        return openStatus;
    }

    public void setOpenStatus(boolean openStatus) {
        this.openStatus = openStatus;
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


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
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
