package com.kshrd.derphsar_api.rest.image.response;

public class ImageResponse {

    private Object imageUrl;

    public ImageResponse(){}

    public ImageResponse(Object imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "ImageResponse{" +
                "imageUrl='" + imageUrl + '\'' +
                '}';
    }

    public Object getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Object imageUrl) {
        this.imageUrl = imageUrl;
    }
}
