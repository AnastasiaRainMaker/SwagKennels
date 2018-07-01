package com.project.swagkennels.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.project.swagkennels.R;
import com.project.swagkennels.models.Item;
import com.project.swagkennels.models.ShopItemSize;
import com.project.swagkennels.repository.RoomRepository;
import com.project.swagkennels.room.PurchasedShopItem;
import java.util.ArrayList;

public class ItemDetailsActivity extends AppCompatActivity{
    private Toolbar toolbar;
    private ImageView imageView;
    private TextView descriptionView;
    private TextView priceView;
    private TextView quantityStrView;
    private TextView availableCountView;
    private EditText orderCountView;
    private Spinner spinner;
    private ArrayList<ShopItemSize> sizesItems;
    private ArrayList<String> availSizes;
    private Item item;
    private String availCountStr;
    private Button addToCartBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sizesItems = new ArrayList<>();
        availSizes = new ArrayList<>();
        imageView = findViewById(R.id.image);
        descriptionView = findViewById(R.id.description);
        priceView = findViewById(R.id.price);
        spinner = findViewById(R.id.sizes_spinner);
        quantityStrView = findViewById(R.id.quantityStr);
        availableCountView = findViewById(R.id.available_count);
        orderCountView = findViewById(R.id.order_quantity);
        addToCartBtn = findViewById(R.id.add_to_cart_button);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null){
            item = extras.getParcelable("item");
        } else {
            finish();
        }

        if (item != null) {
            availSizes.add("Size");
            sizesItems = item.getSize();
            for (ShopItemSize size : sizesItems) {
                availSizes.add(size.getName());
            }

            RequestOptions requestOptions = new RequestOptions();
            requestOptions = requestOptions.placeholder(new ColorDrawable(imageView.getContext().getResources().getColor(R.color.lightGrey)));
            requestOptions = requestOptions.error(getResources().getDrawable(R.drawable.no_item_image));
            requestOptions = requestOptions.centerCrop();

            Glide.with(imageView.getContext())
                    .setDefaultRequestOptions(requestOptions)
                    .load(item.getImageUrl())
                    .into(imageView);

            quantityStrView.setVisibility(View.INVISIBLE);
            availableCountView.setVisibility(View.INVISIBLE);
            orderCountView.setVisibility(View.INVISIBLE);
            priceView.setText(item.getPrice());
            descriptionView.setText(item.getDescription());

        }
        ArrayAdapter<String> adapter = new ArrayAdapter<> (this, android.R.layout.simple_spinner_item, availSizes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // todo
        addToRoom();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                quantityStrView.setVisibility(View.VISIBLE);
                availableCountView.setVisibility(View.VISIBLE);
                orderCountView.setVisibility(View.VISIBLE);
                availCountStr = null;
                if (spinner.getItemAtPosition(i).equals("Size")) {
                    orderCountView.setFocusable(false);
                    orderCountView.setClickable(false);
                } else {
                    orderCountView.setFocusableInTouchMode(true);
                    orderCountView.setClickable(true);
                }
                if(item != null) {
                    for (ShopItemSize size : sizesItems) {
                        if (size != null && spinner.getItemAtPosition(i) == size.getName()) {
                            availCountStr = size.getCount().toString();
                            availableCountView.setText(availCountStr + " available");
                        }
                    }

                    orderCountView.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void afterTextChanged(Editable editable) {
                            if (!editable.toString().equals("") && Integer.valueOf(editable.toString()) > Integer.valueOf(availCountStr)) {
                                orderCountView.setTextColor(getResources().getColor(R.color.red));
                                addToCartBtn.setClickable(false);
                                addToCartBtn.setTextColor(getResources().getColor(R.color.colorPrimary));

                            } else {
                                orderCountView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                                addToCartBtn.setClickable(true);
                                addToCartBtn.setTextColor(getResources().getColor(R.color.colorAccent));
                            }
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    private void addToRoom() {
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

