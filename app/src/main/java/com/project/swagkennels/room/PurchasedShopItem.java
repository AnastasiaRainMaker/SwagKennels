package com.project.swagkennels.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import static com.project.swagkennels.Constants.PURCHASED_ITEMS_TABLE_NAME;
import static com.project.swagkennels.Constants.PURCHASED_ITEM_IMAGE;
import static com.project.swagkennels.Constants.PURCHASED_ITEM_KEY;
import static com.project.swagkennels.Constants.PURCHASED_ITEM_NAME;
import static com.project.swagkennels.Constants.PURCHASED_ITEM_PRICE;
import static com.project.swagkennels.Constants.PURCHASED_ITEM_SIZE;
import static com.project.swagkennels.Constants.PURCHASED_ITEM_TITLE;

@Entity(tableName = "purchased_items")
public class PurchasedShopItem {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = PURCHASED_ITEM_NAME)
    private String name;

    @ColumnInfo(name = PURCHASED_ITEM_PRICE)
    private String price;

    @ColumnInfo(name = PURCHASED_ITEM_KEY)
    private String key;

    @ColumnInfo(name = PURCHASED_ITEM_TITLE)
    private String title;

    @ColumnInfo(name = PURCHASED_ITEM_SIZE)
    private String size;

    @ColumnInfo(name = PURCHASED_ITEM_IMAGE)
    private String image;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
