package com.kshrd.derphsar_api.rest.product.request;

public class ProductUpdateDiscountModel {
    private Double discount;

    public ProductUpdateDiscountModel(){}

    public ProductUpdateDiscountModel(Double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "ProductUpdateDiscountModel{" +
                "discount=" + discount +
                '}';
    }


    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
