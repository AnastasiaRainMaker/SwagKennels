package com.project.swagkennels;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemDetailsActivity extends AppCompatActivity{
    Toolbar toolbar;
    ImageView imageView;
    TextView descriptionView;
    TextView dateView;
    TextView priceView;
    Spinner spinner;
    ArrayList<String> sizes;

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
        sizes.add("Choose size");
        sizes.add("S");
        sizes.add("M");
        ArrayAdapter<String> adapter = new ArrayAdapter<> (this, android.R.layout.simple_spinner_item, sizes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}

