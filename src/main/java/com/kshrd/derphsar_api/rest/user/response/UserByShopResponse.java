package com.kshrd.derphsar_api.rest.user.response;

public class UserByShopResponse {

    private String userId;
    private String name;
    private String phone;
    private String email;
    private boolean status;

    public UserByShopResponse(){}

    public UserByShopResponse(String userId, String name, String phone, String email, boolean status) {
        this.userId = userId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.status = status;
    }


    @Override
    public String toString() {
        return "UserByShopResponse{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
