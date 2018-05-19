package com.project.swagkennels.presenters;

import com.project.swagkennels.room.PurchasedShopItem;

import java.util.ArrayList;

public interface ShopBinPresenter {
    void loadData();
    void onDestroy();

    interface ShopBinView {
        void displayData(ArrayList<PurchasedShopItem> data);

        void displayNoData();
    }
}
