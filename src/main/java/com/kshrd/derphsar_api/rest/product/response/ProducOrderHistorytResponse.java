package com.kshrd.derphsar_api.rest.product.response;

public class ProducOrderHistorytResponse {
    private String name;
    private Double price;
    private Object images;

    public ProducOrderHistorytResponse() {
    }

    public ProducOrderHistorytResponse(String name, Double price, Object images) {
        this.name = name;
        this.price = price;
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Object getImages() {
        return images;
    }

    public void setImages(Object images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "ProducOrderHistorytResponse{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", images=" + images +
                '}';
    }
}
