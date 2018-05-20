package com.project.swagkennels.presenters;

import com.project.swagkennels.room.PurchasedShopItem;

import java.util.ArrayList;

public interface ShopBinPresenter {
    void loadData();
    void onDestroy();
    void removeItem(PurchasedShopItem item);
    Integer getTotalAmount();

    Float getTotalAmountPrice();

    interface ShopBinView {
        void displayData(ArrayList<PurchasedShopItem> data);
        void displayNoData();
        void onItemRemoved(PurchasedShopItem item);
    }
}
