package com.project.swagkennels.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;

import static com.project.swagkennels.Constants.PURCHASED_ITEMS_TABLE_NAME;

@Dao
public interface PurchasedShopItemDao {

    @Query("SELECT * FROM purchased_items")
    Flowable<List<PurchasedShopItem>> getAllPurchasedItemsLiveData();

    @Query("SELECT * FROM purchased_items")
    List<PurchasedShopItem> getAllPurchasedItems();

//    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
//    List<PurchasedShopItem> loadAllByIds(int[] itemIds);
//
//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND "
//            + "last_name LIKE :last LIMIT 1")
//    PurchasedShopItem findByName(String first, String last);

    @Insert
    void insert(PurchasedShopItem... item);

    @Insert
    void insertMultipleListRecord(List<PurchasedShopItem> items);

    @Delete
    void delete(PurchasedShopItem items);

}
