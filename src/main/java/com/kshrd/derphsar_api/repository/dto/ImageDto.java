package com.kshrd.derphsar_api.repository.dto;

public class ImageDto {
    private Object imageUrl;

    public ImageDto(){}

    public ImageDto(Object imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Object getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Object imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "ImageDto{" +
                "imageUrl=" + imageUrl +
                '}';
    }
}
