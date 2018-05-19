package com.project.swagkennels.activity;

import android.annotation.SuppressLint;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.project.swagkennels.R;
import com.project.swagkennels.fragments.BreedingFragment;
import com.project.swagkennels.fragments.DogsFragment;
import com.project.swagkennels.fragments.NewsFragment;
import com.project.swagkennels.fragments.PuppiesFragment;
import com.project.swagkennels.fragments.ShopBinFragment;
import com.project.swagkennels.fragments.ShopFragment;
import com.project.swagkennels.repository.RoomRepository;
import com.project.swagkennels.room.PurchasedShopItem;

import java.util.List;

public class NewsListActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private Fragment fragment;
    private  String screenType = "";

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        setUpViews();
    }

    private void setUpBinIcon(final TextView shopBinButton) {
        RoomRepository roomRepository = new RoomRepository(getApplication());
        LiveData<List<PurchasedShopItem>> livePurchasedShopItems = roomRepository.getAllPurchasedItems();
        livePurchasedShopItems.observeForever(new Observer<List<PurchasedShopItem>>() {
            @Override
            public void onChanged(@Nullable List<PurchasedShopItem> purchasedShopItems) {
                shopBinButton.setText(String.valueOf(purchasedShopItems != null ? purchasedShopItems.size() : 0));
            }
        });

        shopBinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_news, new ShopBinFragment(), "shopBinFragment")
                        .commit();
            }
        });
    }

    private void setUpViews() {
        if (getIntent() != null){
            screenType = getIntent().getStringExtra("type") != null ? getIntent().getStringExtra("type") : "";
        }
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frame_news, getCurrentScreen(), "newsFragment")
                .commit();
        setUpBottomNav();
        Toolbar toolbar = findViewById(R.id.toolbar_news);
        setSupportActionBar(toolbar);

        TextView shopBinButton = toolbar.findViewById(R.id.shop_bin_button);
        setUpBinIcon(shopBinButton);

    }

    private Fragment getCurrentScreen() {
        switch (screenType){
            case "dogs" : return new DogsFragment();
            case "puppies" : return new PuppiesFragment();
            case "breeding" : return new BreedingFragment();
            case "shop" : return new ShopFragment();
            default : return new NewsFragment();
        }
    }

    public void setUpBottomNav() {
        BottomNavigationViewEx bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.enableAnimation(true);
        bottomNavigationView.enableShiftingMode(false);
        bottomNavigationView.enableItemShiftingMode(false);
        bottomNavigationView.setCurrentItem(getCurrentNavId());
        bottomNavigationView.setOnNavigationItemSelectedListener(
               new BottomNavigationView.OnNavigationItemSelectedListener() {
                   @Override
                   public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                       switch (item.getItemId()) {
                           case R.id.action_news:
                               if (!item.isChecked()) {
                                   fragment = new NewsFragment();
                                   fragmentManager.beginTransaction()
                                           .replace(R.id.frame_news, fragment, "newsFragment")
                                           .commit();
                               }
                               break;
                           case R.id.action_dogs:
                               if (!item.isChecked()) {
                                   fragment = new DogsFragment();
                                   fragmentManager.beginTransaction()
                                           .replace(R.id.frame_news, fragment, "dogsFragment")
                                           .commit();
                               }
                               break;
                           case R.id.action_puppies:
                               if (!item.isChecked()) {
                                   fragment = new PuppiesFragment();
                                   fragmentManager.beginTransaction()
                                           .replace(R.id.frame_news, fragment, "puppiesFragment")
                                           .commit();
                               }
                               break;
                           case R.id.action_breeding:
                               if (!item.isChecked()) {
                                   fragment = new BreedingFragment();
                                   fragmentManager.beginTransaction()
                                           .replace(R.id.frame_news, fragment, "breedingFragment")
                                           .commit();
                               }
                               break;
                           case R.id.action_shop:
                               if(!item.isChecked()) {
                                   fragment = new ShopFragment();
                                   fragmentManager.beginTransaction()
                                           .replace(R.id.frame_news, fragment, "shopFragment")
                                           .commit();
                               }
                               break;
                       }
                       return true;
                   }
               });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_news_menu, menu);
        return true;
    }

    public int getCurrentNavId() {
        switch (screenType){
            case "dogs" : return 1;
            case "puppies" : return 2;
            case "breeding" : return 3;
            case "shop" : return 4;
            default : return 0;
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog alertDialog = new AlertDialog.Builder(NewsListActivity.this, R.style.MyDialogTheme).create();
        alertDialog.setMessage("Do you want to leave?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
