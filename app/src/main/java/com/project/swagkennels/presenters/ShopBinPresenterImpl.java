package com.project.swagkennels.presenters;

import android.util.Log;

import com.project.swagkennels.repository.RoomRepository;
import com.project.swagkennels.room.PurchasedShopItem;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static android.support.constraint.Constraints.TAG;

public class ShopBinPresenterImpl implements ShopBinPresenter {

    private ShopBinPresenter.ShopBinView shopBinView;
    private RoomRepository roomRepository;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private List<PurchasedShopItem> purchasedShopItems;

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
                ShopBinPresenterImpl.this.purchasedShopItems = purchasedShopItems;
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

    @Override
    public void removeItem(PurchasedShopItem item) {
        roomRepository.removePurchasedShopItem(item);
        shopBinView.onItemRemoved(item);
    }

    @Override
    public Integer getTotalAmount() {
        return purchasedShopItems != null ? purchasedShopItems.size() : 0;
    }

    @Override
    public Float getTotalAmountPrice() {
        Float res = 0.0f;
        for (PurchasedShopItem item: purchasedShopItems) {
            try {
                res += Float.parseFloat(item.getPrice());
            } catch (Exception e){
                Log.e(TAG, "can not parse the price");
            }
        }
        return res;
    }
}
