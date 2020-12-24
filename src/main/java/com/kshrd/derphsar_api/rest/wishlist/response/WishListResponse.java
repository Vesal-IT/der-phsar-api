package com.kshrd.derphsar_api.rest.wishlist.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kshrd.derphsar_api.repository.dto.ProductDto;
import com.kshrd.derphsar_api.repository.dto.UserDto;
import com.kshrd.derphsar_api.rest.product.response.ProductResponseModel;
import com.kshrd.derphsar_api.rest.product.response.ProductWishListResponse;
import com.kshrd.derphsar_api.rest.user.response.UserResponseModel;
import com.kshrd.derphsar_api.rest.user.response.UserWishListResponse;

import java.io.Serializable;
import java.util.Date;

public class WishListResponse {

    private String wishlistId;
    private Date favDate;
    private boolean status;
    private UserWishListResponse user;
    private ProductWishListResponse product;

    public WishListResponse(){}

    public WishListResponse(String wishlistId, Date favDate, boolean status, UserWishListResponse user, ProductWishListResponse product) {

        this.wishlistId = wishlistId;
        this.favDate = favDate;
        this.status = status;
        this.user = user;
        this.product = product;
    }

    @Override
    public String toString() {
        return "WishListResponse{" +

                ", wishlistId='" + wishlistId + '\'' +
                ", favDate=" + favDate +
                ", status=" + status +
                ", user=" + user +
                ", product=" + product +
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public UserWishListResponse getUser() {
        return user;
    }

    public void setUser(UserWishListResponse user) {
        this.user = user;
    }

    public ProductWishListResponse getProduct() {
        return product;
    }

    public void setProduct(ProductWishListResponse product) {
        this.product = product;
    }
}
