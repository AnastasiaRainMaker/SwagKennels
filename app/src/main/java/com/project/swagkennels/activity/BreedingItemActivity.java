package com.project.swagkennels.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.project.swagkennels.R;
import com.project.swagkennels.models.Breeding;
import com.project.swagkennels.models.BreedingItem;

import java.util.HashMap;

public class BreedingItemActivity extends AppCompatActivity {

    TextView title;
    ImageView maleImageView;
    ImageView femaleImageView;
    LinearLayout maleFrameLayout;
    LinearLayout femaleFrameLayout;
    Breeding breeding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breeding_item);
        getBreedingIntent();
        setUpView();
    }


    private void getBreedingIntent() {
        Intent intent = getIntent();
        breeding = (Breeding) intent.getSerializableExtra("breedingItem");
    }

    private void setUpView() {
        maleImageView = findViewById(R.id.imageMale);
        femaleImageView = findViewById(R.id.imageFemale);
        title = findViewById(R.id.title);
        maleFrameLayout = findViewById(R.id.frame_males);
        femaleFrameLayout = findViewById(R.id.frame_females);
        if (breeding != null) {
            title.setText(breeding.getTitle());

            maleImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(BreedingItemActivity.this, TransparentImageActivity.class);
                    intent.putExtra("imageUrl",breeding.getImageUrlMale());
                    String transitionName = getString(R.string.transition_shared_element);

                    ActivityOptionsCompat options =
                            ActivityOptionsCompat.makeSceneTransitionAnimation(BreedingItemActivity.this,
                                    maleImageView,   // Starting view
                                    transitionName    // The String
                            );
                    ActivityCompat.startActivity(BreedingItemActivity.this, intent, options.toBundle());
                }
            });

            femaleImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(BreedingItemActivity.this, TransparentImageActivity.class);
                    intent.putExtra("imageUrl",breeding.getImageUrlFemale());
                    String transitionName = getString(R.string.transition_shared_element);

                    ActivityOptionsCompat options =
                            ActivityOptionsCompat.makeSceneTransitionAnimation(BreedingItemActivity.this,
                                    femaleImageView,   // Starting view
                                    transitionName    // The String
                            );
                    ActivityCompat.startActivity(BreedingItemActivity.this, intent, options.toBundle());
                }
            });


            RequestOptions requestOptions = new RequestOptions();
            requestOptions = requestOptions.placeholder(new ColorDrawable(femaleImageView.getContext().getResources().getColor(R.color.lightGrey)));
            requestOptions = requestOptions.error(new ColorDrawable(femaleImageView.getContext().getResources().getColor(R.color.colorPrimary)));
            requestOptions = requestOptions.centerCrop();

            Glide.with(femaleImageView.getContext())
                    .setDefaultRequestOptions(requestOptions)
                    .load(breeding.getImageUrlFemale())
                    .into(femaleImageView);

            Glide.with(maleImageView.getContext())
                    .setDefaultRequestOptions(requestOptions)
                    .load(breeding.getImageUrlMale())
                    .into(maleImageView);
            HashMap<String, BreedingItem> males = breeding.getMales();
            HashMap<String, BreedingItem> females = breeding.getFemales();

            int i = 1;
            int j = 1;
            TextView tv;
            LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            while (males.size() >= i) {
                BreedingItem item = males.get("male" + i);
                tv = new TextView(this);
                SpannableStringBuilder sb = new SpannableStringBuilder();
                sb.append(item.getPeak()).append(" ").append(item.getPrice());
                tv.setLayoutParams(lparams);
                tv.setTextSize(17.00f);
                if (item.getAvailable().equals("true")) {
                    tv.setTextColor(getResources().getColor(R.color.colorAccent));
                    tv.setText(sb);
                } else {
                    tv.setTextColor(getResources().getColor(R.color.colorPrimary));
                    StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
                    sb.setSpan(
                            strikethroughSpan,
                            0,
                            sb.length(),
                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    tv.setText(sb, TextView.BufferType.EDITABLE);
                    tv.setTextColor(getResources().getColor(R.color.colorPrimary));
                }
                i++;
                maleFrameLayout.addView(tv);
            }
            while (females.size() >= j) {
                BreedingItem item = females.get("female" + j);
                tv = new TextView(this);
                SpannableStringBuilder sb = new SpannableStringBuilder();
                sb.append(item.getPeak()).append(" ").append(item.getPrice());
                tv.setLayoutParams(lparams);
                tv.setTextSize(17.00f);
                if (item.getAvailable().equals("true")) {
                    tv.setTextColor(getResources().getColor(R.color.colorAccent));
                    tv.setText(sb);
                } else {
                    StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
                    sb.setSpan(
                            strikethroughSpan,
                            0,
                            sb.length(),
                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    tv.setText(sb, TextView.BufferType.EDITABLE);
                    tv.setTextColor(getResources().getColor(R.color.colorPrimary));
                }
                j++;
                femaleFrameLayout.addView(tv);
            }
        }
    }

}
