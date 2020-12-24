package com.kshrd.derphsar_api.repository.dto;

public class CategoryDto {
    private int id;
    private String catId;
    private String name;

    public CategoryDto(){}

    public CategoryDto(int id, String catId, String name) {
        this.id = id;
        this.catId = catId;
        this.name = name;
    }

    @Override
    public String toString() {
        return "CategoryDto{" +
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
