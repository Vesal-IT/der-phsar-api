package com.kshrd.derphsar_api.rest.shop.response;

public class ShopOfAUserResponse {
    private String name;
    private boolean status;
    public ShopOfAUserResponse() {
    }

    public ShopOfAUserResponse(String name, boolean status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ShopOfAUserResponse{" +
                "name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
