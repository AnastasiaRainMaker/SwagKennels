package com.project.swagkennels.presenters;

import com.project.swagkennels.models.Item;

import java.util.ArrayList;

public interface ShopPresenter {

    void loadData();

    interface ShopView {
        void displayData(ArrayList<Item> data);

        void displayNoData();
    }

    interface ShopCallbacks {
        void onDataLoaded(ArrayList<Item> data);
    }
}
