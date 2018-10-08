package com.project.swagkennels.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.project.swagkennels.R;

public class DogItemActivity extends AppCompatActivity {

    ImageView imageView;
    TextView titleView;
    TextView descriptionView;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_item);
        setUpView();
        getNewsInfo();
    }

    private void getNewsInfo() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            String imgUrl = extras.getString("imageUrl",null);
            if (imgUrl == null) {
                imageView.setBackgroundResource(R.mipmap.splash_logo);
            } else {
                RequestOptions requestOptions = new RequestOptions();
                requestOptions = requestOptions.placeholder(new ColorDrawable(imageView.getContext().getResources().getColor(R.color.lightGrey)));
                requestOptions = requestOptions.error(new ColorDrawable(imageView.getContext().getResources().getColor(R.color.black)));
                requestOptions = requestOptions.centerCrop();

                Glide.with(imageView.getContext())
                        .setDefaultRequestOptions(requestOptions)
                        .load(imgUrl)
                        .into(imageView);
            }
            String description = extras.getString("description", null);
            if (description == null) {
                descriptionView.setVisibility(View.GONE);
            } else {
                descriptionView.setText(description);
            }
            String title = extras.getString("title", null);
            if (title == null) {
                titleView.setVisibility(View.GONE);
            } else {
                titleView.setText(title);
            }
        }
    }

    public void setUpView() {
        toolbar = findViewById(R.id.toolbar_news_item);
        setSupportActionBar(toolbar);
        imageView = findViewById(R.id.image);
        descriptionView = findViewById(R.id.description);
        titleView = findViewById(R.id.title);
    }
}
