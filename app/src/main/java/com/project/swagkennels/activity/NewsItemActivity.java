package com.project.swagkennels.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.project.swagkennels.R;


public class NewsItemActivity extends AppCompatActivity {

    Toolbar toolbar;
    ImageView imageView;
    TextView descriptionView;
    TextView dateView;
    TextView youtubeLinkView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_item);
        setUpView();
        getNewsInfo();
    }

    private void getNewsInfo() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            String imgUrl = extras.getString("imageUrl",null);
            if (imgUrl == null) {
                imageView.setBackgroundResource(R.drawable.dog_img);
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
            String date = extras.getString("date", null);
            if (date == null) {
                dateView.setVisibility(View.GONE);
            } else {
                dateView.setText(date);
            }
            String link = extras.getString("youtubelink", null);
            if (link == null) {
                youtubeLinkView.setVisibility(View.GONE);
            } else {
                youtubeLinkView.setText(link);
            }
        }
    }

    public void setUpView() {
        toolbar = findViewById(R.id.toolbar_news_item);
        setSupportActionBar(toolbar);
        imageView = findViewById(R.id.image);
        descriptionView = findViewById(R.id.description);
        dateView = findViewById(R.id.date);
        youtubeLinkView = findViewById(R.id.link);
    }
}
