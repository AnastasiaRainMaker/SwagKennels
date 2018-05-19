package com.project.swagkennels.presenters;

import com.project.swagkennels.repository.RoomRepository;
import com.project.swagkennels.room.PurchasedShopItem;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ShopBinPresenterImpl implements ShopBinPresenter {

    private ShopBinPresenter.ShopBinView shopBinView;
    private RoomRepository roomRepository;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public ShopBinPresenterImpl(ShopBinPresenter.ShopBinView shopBinView, RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
        this.shopBinView = shopBinView;
    }

    @Override
    public void loadData() {
        Flowable<List<PurchasedShopItem>> items = roomRepository.getAllPurchasedItems();
        compositeDisposable.add(items.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<PurchasedShopItem>>() {
            @Override
            public void accept(List<PurchasedShopItem> purchasedShopItems) {
                if (purchasedShopItems != null && purchasedShopItems.size() > 0) {
                    shopBinView.displayData((ArrayList<PurchasedShopItem>) purchasedShopItems);
                } else {
                    shopBinView.displayNoData();
                }
            }
        }));
    }

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
    }
}
