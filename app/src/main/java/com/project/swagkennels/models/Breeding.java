package com.project.swagkennels.models;

import java.io.Serializable;
import java.util.HashMap;

public class Breeding implements Serializable {

    private String title;

    private String imageUrlMale;

    private String imageUrlFemale;

    private HashMap<String, BreedingItem> males;

    private HashMap<String, BreedingItem> females;

    public Breeding() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrlMale() {
        return imageUrlMale;
    }

    public void setImageUrlMale(String imageUrlMale) {
        this.imageUrlMale = imageUrlMale;
    }

    public String getImageUrlFemale() {
        return imageUrlFemale;
    }

    public void setImageUrlFemale(String imageUrlFemale) {
        this.imageUrlFemale = imageUrlFemale;
    }

    public HashMap<String, BreedingItem> getMales() {
        return males;
    }

    public void setMales(HashMap<String, BreedingItem> males) {
        this.males = males;
    }

    public HashMap<String, BreedingItem> getFemales() {
        return females;
    }

    public void setFemales(HashMap<String, BreedingItem> females) {
        this.females = females;
    }
}
