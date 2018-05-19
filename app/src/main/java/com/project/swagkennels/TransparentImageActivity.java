package com.project.swagkennels;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class TransparentImageActivity extends AppCompatActivity {

    ImageView imageView;
    ImageView cancelView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transparent_image);
        imageView = findViewById(R.id.image_zoom);
//        cancelView = findViewById(R.id.cancel);
        Intent intent = getIntent();
        String url = intent.getStringExtra("imageUrl");
        if (url != null) {
            RequestOptions requestOptions = new RequestOptions();
            requestOptions = requestOptions.placeholder(new ColorDrawable(imageView.getContext().getResources().getColor(R.color.lightGrey)));
            requestOptions = requestOptions.error(new ColorDrawable(imageView.getContext().getResources().getColor(R.color.colorPrimary)));
            requestOptions = requestOptions.centerCrop();

            Glide.with(imageView.getContext())
                    .setDefaultRequestOptions(requestOptions)
                    .load(url)
                    .into(imageView);
        }

//        cancelView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onBackPressed();
//            }
//        });
    }
}
