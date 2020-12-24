package com.kshrd.derphsar_api.rest.user.response;

public class UserWishListResponse {
    private String userId;
    private String name;


    public UserWishListResponse(){
    }

    public UserWishListResponse(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }


    @Override
    public String toString() {
        return "UserWishListResponse{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
