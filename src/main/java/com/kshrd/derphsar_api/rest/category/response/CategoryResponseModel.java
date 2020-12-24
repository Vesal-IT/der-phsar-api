package com.kshrd.derphsar_api.rest.category.response;

public class CategoryResponseModel {
    private int id;
    private String catId;
    private String name;

    public CategoryResponseModel(){}


    public CategoryResponseModel(int id, String catId, String name) {
        this.id = id;
        this.catId = catId;
        this.name = name;
    }


    @Override
    public String toString() {
        return "CategoryResponseModel{" +
                "id=" + id +
                ", catId='" + catId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
