package com.kshrd.derphsar_api.repository.dto;

import org.springframework.security.core.userdetails.User;

import java.util.Date;

public class WishListDto {

    private int id;
    private String wishlistId;
    private Date favDate;
    private int u_id;
    private int pro_id;
    private boolean status;

    private UserDto user;
    private ProductDto product;

    public WishListDto() {
    }

    public WishListDto(int id, String wishlistId, Date favDate, int u_id, int pro_id, boolean status, UserDto user, ProductDto product) {
        this.id = id;
        this.wishlistId = wishlistId;
        this.favDate = favDate;
        this.u_id = u_id;
        this.pro_id = pro_id;
        this.status = status;
        this.user = user;
        this.product = product;
    }

    @Override
    public String toString() {
        return "WishListDto{" +
                "id=" + id +
                ", wishlistId='" + wishlistId + '\'' +
                ", favDate=" + favDate +
                ", u_id=" + u_id +
                ", pro_id=" + pro_id +
                ", status=" + status +
                ", user=" + user +
                ", product=" + product +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }
}
