package com.kshrd.derphsar_api.repository.dto;

import java.util.Date;
import java.util.List;

public class ShopDto {

    private int id;
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

    private UserDto user;
    private CategoryDto category;
    private PromotionDto promotion;
//    private List<PromotionDto> promotion;


    public ShopDto() {
    }

    public ShopDto(int id, String shopId, String name, String address, String description, String profile, String cover, boolean openStatus, boolean status, int u_id, int cat_id, Date openTime, Date closeTime, UserDto user, CategoryDto category, PromotionDto promotion) {
        this.id = id;
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
        this.user = user;
        this.category = category;
        this.promotion = promotion;
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

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }


    public PromotionDto getPromotion() {
        return promotion;
    }

    public void setPromotion(PromotionDto promotion) {
        this.promotion = promotion;
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


    @Override
    public String toString() {
        return "ShopDto{" +
                "id=" + id +
                ", shopId='" + shopId + '\'' +
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
                ", user=" + user +
                ", category=" + category +
                ", promotion=" + promotion +
                '}';
    }
}
