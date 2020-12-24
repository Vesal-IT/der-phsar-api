package com.kshrd.derphsar_api.rest.shop.response;

import com.kshrd.derphsar_api.repository.dto.ShopDto;
import com.kshrd.derphsar_api.rest.category.response.CategoryResponseModel;
import com.kshrd.derphsar_api.rest.promotion.response.PromotionResponseModel;
import com.kshrd.derphsar_api.rest.user.response.UserResponseModel;

import java.util.Date;
import java.util.List;

public class ShopResponseModel {

    private int id;
    private String shopId;
    private String name;
    private String address;
    private String description;
    private String profile;
    private String cover;
    private boolean openStatus;
    private boolean status;
    private Date openTime;
    private Date closeTime;

    private UserResponseModel user;
    private CategoryResponseModel category;
    //private List<PromotionResponseModel> promotion;

    public ShopResponseModel() {
    }


    public ShopResponseModel(int id, String shopId, String name, String address, String description, String profile, String cover, boolean openStatus, boolean status, Date openTime, Date closeTime, UserResponseModel user, CategoryResponseModel category) {
        this.id = id;
        this.shopId = shopId;
        this.name = name;
        this.address = address;
        this.description = description;
        this.profile = profile;
        this.cover = cover;
        this.openStatus = openStatus;
        this.status = status;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.user = user;
        this.category = category;
    }


    @Override
    public String toString() {
        return "ShopResponseModel{" +
                "id=" + id +
                ", shopId='" + shopId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", profile='" + profile + '\'' +
                ", cover='" + cover + '\'' +
                ", openStatus=" + openStatus +
                ", status=" + status +
                ", openTime=" + openTime +
                ", closeTime=" + closeTime +
                ", user=" + user +
                ", category=" + category +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public UserResponseModel getUser() {
        return user;
    }

    public void setUser(UserResponseModel user) {
        this.user = user;
    }

    public CategoryResponseModel getCategory() {
        return category;
    }

    public void setCategory(CategoryResponseModel category) {
        this.category = category;
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
