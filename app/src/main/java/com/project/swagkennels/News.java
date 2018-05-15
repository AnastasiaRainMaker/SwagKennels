package com.project.swagkennels;

public class News {

    private String imageLink;

    private String date;

    private String description;

    private String youtubeLink;

    public News(String imageLink, String date, String description, String youtubeLink) {
        this.imageLink = imageLink;
        this.date = date;
        this.description = description;
        this.youtubeLink = youtubeLink;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }
}
