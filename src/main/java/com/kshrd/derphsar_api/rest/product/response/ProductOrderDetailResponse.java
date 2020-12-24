package com.kshrd.derphsar_api.rest.product.response;

public class ProductOrderDetailResponse {

    private String proId;
    private String name;
    private double price;
    private boolean soldStatus;
    private Double discount;

    public ProductOrderDetailResponse(){}

    public ProductOrderDetailResponse(String proId, String name, double price, boolean soldStatus, Double discount) {
        this.proId = proId;
        this.name = name;
        this.price = price;
        this.soldStatus = soldStatus;
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "ProductOrderDetailResponse{" +
                "proId='" + proId + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", soldStatus=" + soldStatus +
                ", discount=" + discount +
                '}';
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isSoldStatus() {
        return soldStatus;
    }

    public void setSoldStatus(boolean soldStatus) {
        this.soldStatus = soldStatus;
    }
}
