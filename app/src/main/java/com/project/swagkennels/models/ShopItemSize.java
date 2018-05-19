package com.project.swagkennels.models;

import android.os.Parcel;
import android.os.Parcelable;

class ShopItemSize implements Parcelable {

    private String name;
    private Integer count;

    ShopItemSize(){}

    protected ShopItemSize(Parcel in) {
        name = in.readString();
        count = in.readInt();
    }

    public static final Creator<ShopItemSize> CREATOR = new Creator<ShopItemSize>() {
        @Override
        public ShopItemSize createFromParcel(Parcel in) {
            return new ShopItemSize(in);
        }

        @Override
        public ShopItemSize[] newArray(int size) {
            return new ShopItemSize[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.count);
    }
}
