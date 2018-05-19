package com.project.swagkennels.pojo;

import java.io.Serializable;

public class BreedingItem implements Serializable {

    private String price;

    private String available;

    private String peak;

    public BreedingItem() {}


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getPeak() {
        return peak;
    }

    public void setPeak(String peak) {
        this.peak = peak;
    }
}
