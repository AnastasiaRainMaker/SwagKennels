package com.project.swagkennels;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


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
        toolbar = findViewById(R.id.toolbar_news_item);
        setSupportActionBar(toolbar);

        imageView = findViewById(R.id.image);
        descriptionView = findViewById(R.id.description);
        dateView = findViewById(R.id.date);
        youtubeLinkView = findViewById(R.id.link);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            String imgUrl = extras.getString("imageUrl",null);
            if (imgUrl == null) {
                imageView.setBackgroundResource(R.drawable.dog_img);
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

}
