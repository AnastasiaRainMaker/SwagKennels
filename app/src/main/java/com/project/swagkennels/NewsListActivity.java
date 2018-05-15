package com.project.swagkennels;

import android.annotation.SuppressLint;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.project.swagkennels.fragments.BreedingFragment;
import com.project.swagkennels.fragments.DogsFragment;
import com.project.swagkennels.fragments.NewsFragment;
import com.project.swagkennels.fragments.PuppiesFragment;
import com.project.swagkennels.fragments.ShopFragment;

public class NewsListActivity extends AppCompatActivity {

    BottomNavigationViewEx bottomNavigationView;
    FragmentManager fragmentManager;
    Fragment fragment;
    Toolbar toolbar;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        fragmentManager = getSupportFragmentManager();
        fragment = new NewsFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.frame_news, fragment, "newsFragment")
                .commit();
        setUpBottomNav();
        toolbar = findViewById(R.id.toolbar_news);
        setSupportActionBar(toolbar);
    }

   public void setUpBottomNav() {
       bottomNavigationView = findViewById(R.id.bottom_navigation);
       bottomNavigationView.enableAnimation(true);
       bottomNavigationView.enableShiftingMode(false);
       bottomNavigationView.enableItemShiftingMode(false);

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
}
