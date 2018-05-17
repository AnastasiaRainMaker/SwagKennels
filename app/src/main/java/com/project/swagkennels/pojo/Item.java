package com.project.swagkennels.pojo;

public class Item {

    private String price;

    private String description;

    private String imageUrl;

    public Item (String imageUrl, String description, String price) {
        this.imageUrl = imageUrl;
        this.description = description;
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
