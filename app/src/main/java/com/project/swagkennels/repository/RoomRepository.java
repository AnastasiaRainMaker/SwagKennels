package com.project.swagkennels.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.project.swagkennels.room.AppDatabase;
import com.project.swagkennels.room.PurchasedShopItem;
import com.project.swagkennels.room.PurchasedShopItemDao;

import java.util.List;

import io.reactivex.Flowable;

public class RoomRepository {

    private PurchasedShopItemDao mPurchasedShopItemDao;
//    private LiveData<List<PurchasedShopItem>> mAllPurchasedItems;

    public RoomRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mPurchasedShopItemDao = db.purchasedShopItemDao();
//        mAllPurchasedItems = mPurchasedShopItemDao.getAllPurchasedItems();
    }

    public Flowable<List<PurchasedShopItem>> getAllPurchasedItems() {
        return mPurchasedShopItemDao.getAllPurchasedItemsLiveData();
    }


    public void insert (PurchasedShopItem item) {
        new insertAsyncTask(mPurchasedShopItemDao).execute(item);
    }

    private static class insertAsyncTask extends AsyncTask<PurchasedShopItem, Void, Void> {

        private PurchasedShopItemDao mAsyncTaskDao;

        insertAsyncTask(PurchasedShopItemDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final PurchasedShopItem... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

}
