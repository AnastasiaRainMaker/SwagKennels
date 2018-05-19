package com.project.swagkennels.presenters;

import com.project.swagkennels.repository.FireBaseRepository;
import com.project.swagkennels.models.Item;

import java.util.ArrayList;

public class ShopPresenterImpl implements ShopPresenter, ShopPresenter.ShopCallbacks {

    private ShopPresenter.ShopView view;
    private FireBaseRepository repository;

    public ShopPresenterImpl(ShopPresenter.ShopView view, FireBaseRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void onDataLoaded(ArrayList<Item> data) {
        if (data != null && data.size() > 0){
            view.displayData(data);
        } else {
            view.displayNoData();
        }
    }

    @Override
    public void loadData() {
        repository.getShopItems(this);
    }
}

