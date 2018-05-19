package com.project.swagkennels.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.project.swagkennels.R;
import com.project.swagkennels.models.Item;
import com.project.swagkennels.repository.RoomRepository;
import com.project.swagkennels.room.PurchasedShopItem;

import java.util.ArrayList;

public class ItemDetailsActivity extends AppCompatActivity{
    Toolbar toolbar;
    ImageView imageView;
    TextView descriptionView;
    TextView priceView;
    Spinner spinner;
    ArrayList<String> sizes;
    private Item item = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sizes = new ArrayList<>();
        imageView = findViewById(R.id.image);
        descriptionView = findViewById(R.id.description);
        priceView = findViewById(R.id.price);
        spinner = findViewById(R.id.sizes_spinner);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null){
            item = extras.getParcelable("item");
        } else {
            // todo handle -> finish
        }

        if (extras != null) {
            String imgUrl = extras.getString("imageUrl",null);
            if (imgUrl == null) {
                imageView.setBackgroundResource(R.drawable.no_item_image);
            } else {
                //handle image url
            }
            String description = extras.getString("description", null);
            if (description == null) {
                descriptionView.setVisibility(View.GONE);
            } else {
                descriptionView.setText(description);
            }
            String date = extras.getString("date", null);

            String price = extras.getString("price", null);
            if (price == null) {
                priceView.setVisibility(View.GONE);
            } else {
                priceView.setText(price);
            }
        }
        sizes.add("Size");
        sizes.add("S");
        sizes.add("M");
        ArrayAdapter<String> adapter = new ArrayAdapter<> (this, android.R.layout.simple_spinner_item, sizes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        // todo
        final RoomRepository roomRepository = new RoomRepository(getApplication());

        Button submitButton = findViewById(R.id.add_to_cart_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PurchasedShopItem purchasedShopItem = new PurchasedShopItem();
                purchasedShopItem.setKey(item.getKey());
                purchasedShopItem.setTitle(item.getTitle());
                purchasedShopItem.setPrice(item.getPrice());
                purchasedShopItem.setImage(item.getImageUrl());
                purchasedShopItem.setSize("M"); // todo
                // todo etc
                roomRepository.insert(purchasedShopItem);

                // todo
                finish();
            }
        });
    }

}

