package com.kshrd.derphsar_api.rest.user.response;

public class UserOrderDetailResponse {
    private String userId;
    private String name;
    private String phone;

    public UserOrderDetailResponse(){}

    public UserOrderDetailResponse(String userId, String name, String phone) {
        this.userId = userId;
        this.name = name;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UserOrderDetailResponse{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
