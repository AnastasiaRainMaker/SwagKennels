package com.project.swagkennels.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Item implements Parcelable {

    private String price;
    private String available;
    private ArrayList<ShopItemSize> size;
    private String title;
    private String type;
    private String description;
    private String imageUrl;
    private String key;

    private Item(Parcel in) {
        price = in.readString();
        key = in.readString();
        available = in.readString();
        title = in.readString();
        type = in.readString();
        description = in.readString();
        imageUrl = in.readString();

        ArrayList<ShopItemSize> temp = new ArrayList<>();
        in.readTypedList(temp, ShopItemSize.CREATOR);
        size = temp;
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public String getKey() {
        return key;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public ArrayList<ShopItemSize> getSize() {
        return size;
    }

    public void setSize(ArrayList<ShopItemSize> size) {
        this.size = size;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Item(){}

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

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.price);
        dest.writeString(this.key);
        dest.writeString(this.available);
        dest.writeString(this.title);
        dest.writeString(this.type);
        dest.writeString(this.description);
        dest.writeString(this.imageUrl);
        dest.writeTypedList(this.size);
    }
}
