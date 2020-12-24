package com.kshrd.derphsar_api.rest.category.request;

public class CategoryRequestModel {
    private String catId;
    private String name;

    public CategoryRequestModel(){}

    public CategoryRequestModel(String catId, String name) {
        this.catId = catId;
        this.name = name;
    }


    @Override
    public String toString() {
        return "CategoryRequestModel{" +
                "catId='" + catId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
