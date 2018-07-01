package com.project.swagkennels.activity;

import android.annotation.SuppressLint;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.AutoResolveHelper;
import com.google.android.gms.wallet.PaymentData;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.concurrent.Callable;

import javax.net.ssl.HttpsURLConnection;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.project.swagkennels.fragments.ShopBinFragment.LOAD_PAYMENT_DATA_REQUEST_CODE;

public class NewsListActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private Fragment fragment;
    private  String screenType = "";
    private final CompositeDisposable disposables = new CompositeDisposable();
    private BottomNavigationViewEx bottomNavigationView;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        setUpViews();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("TAG", "OnStop");
    }

    private void setUpBinIcon(final TextView shopBinButton, ImageView shopBinImage) {
        final RoomRepository roomRepository = new RoomRepository(getApplication());
        Flowable<List<PurchasedShopItem>> purchasedShopItems = roomRepository.getAllPurchasedItems();
        disposables.add(purchasedShopItems.observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<PurchasedShopItem>>() {
            @Override
            public void accept(List<PurchasedShopItem> purchasedShopItems) {
                shopBinButton.setText(String.valueOf(purchasedShopItems != null ? purchasedShopItems.size() : 0));
            }
        }));


        shopBinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openShopBinFragment();
            }
        });

        shopBinImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openShopBinFragment();
            }
        });
    }

    private void openShopBinFragment() {
        fragmentManager.beginTransaction()
                .replace(R.id.frame_news, new ShopBinFragment(), "shopBinFragment")
                .commit();
        uncheckBottomNavView(bottomNavigationView);
    }

    private void uncheckBottomNavView(BottomNavigationView bottomNavigationView){
        if (bottomNavigationView != null) {
            Menu menu = bottomNavigationView.getMenu();
            for (int i = 0; i < bottomNavigationView.getMenu().size(); i++) {
                MenuItem menuItem = menu.getItem(i);
                menuItem.setChecked(false);
               // menuItem.setCheckable(false);

            }
        }
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
        ImageView shopBinImage = toolbar.findViewById(R.id.shop_bin_image);
        setUpBinIcon(shopBinButton, shopBinImage);

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
        bottomNavigationView = findViewById(R.id.bottom_navigation);
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
                               item.setChecked(false);
                               Toast.makeText(NewsListActivity.this, "Sorry, this section is still in progress", Toast.LENGTH_SHORT).show();
                               int[][] states = new int[][]{
                                           new int[]{-android.R.attr.state_checked},
                                   };

                                   int[] colors = new int[]{
                                           getResources().getColor(R.color.lightGrey),
                                   };

                                   ColorStateList navigationViewColorStateList = new ColorStateList(states, colors);
                                   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                       item.setIconTintList(navigationViewColorStateList);
                                   }
//
//                               if(!item.isChecked()) {
//
////                                   fragment = new ShopFragment();
////                                   fragmentManager.beginTransaction()
////                                           .replace(R.id.frame_news, fragment, "shopFragment")
////                                           .commit();
//                               } else if (getCurrentNavId() != 4) {
///
////                                       fragment = new ShopFragment();
////                                       fragmentManager.beginTransaction()
////                                               .replace(R.id.frame_news, fragment, "shopFragment")
////                                               .commit();
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
        alertDialog.setMessage(getString(R.string.msg_exit));
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TAG", "OnDestroy");
        disposables.clear();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("TAG", "OnPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("TAG", "onResume");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case LOAD_PAYMENT_DATA_REQUEST_CODE:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        PaymentData paymentData = PaymentData.getFromIntent(data);
                        final String token = paymentData.getPaymentMethodToken().getToken();
                        Completable task = Completable.fromCallable(new Callable<Object>() {
                            @Override
                            public Object call() throws Exception {
                                sendPaymentProcessorRequest2(token);
                                return null;
                            }
                        });
                        task.subscribeOn(Schedulers.io()).subscribe();
                        break;
                    case Activity.RESULT_CANCELED:
                        break;
                    case AutoResolveHelper.RESULT_ERROR:
                        Status status = AutoResolveHelper.getStatusFromIntent(data);
                        // Log the status for debugging.
                        // Generally, there is no need to show an error to
                        // the user as the Google Pay API will do that.
                        break;
                    default:
                        // Do nothing.
                }
                break;
            default:
                // Do nothing.
        }
    }

    private void sendPaymentProcessorRequest2(String token) {
    }

    private void sendPaymentProcessorRequest(String token){

        try {

            URL url = new URL ("https://pal-test.adyen.com/pal/servlet/Payment/v30/authorise");
            JSONObject postDataParams = new JSONObject();
            postDataParams.put("name", "abc");
            postDataParams.put("email", "abc@gmail.com");
            Log.e("params",postDataParams.toString());

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            JSONObject jsonParam = new JSONObject();
            JSONObject amount = new JSONObject();
            amount.put("value", "1");
            amount.put("currency", "USD");
            JSONObject additionalData = new JSONObject();
            additionalData.put("paywithgoogle.token", token);
            jsonParam.put("reference", "adyen");
            jsonParam.put("TestMerchant", "N1989Account015");
            jsonParam.put("amount", amount);
            jsonParam.put("additionalData", additionalData);
            writer.write(jsonParam.toString());

            writer.flush();
            writer.close();
            os.close();

            int responseCode=conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {

                BufferedReader in=new BufferedReader(new
                        InputStreamReader(
                        conn.getInputStream()));

                StringBuffer sb = new StringBuffer("");
                String line="";

                while((line = in.readLine()) != null) {

                    sb.append(line);
                    break;
                }

                in.close();
                String s =  sb.toString();

            }
            else {
                String s = "false : " + responseCode;
            }
        }
        catch(Exception e){
            String s = "Exception: " + e.getMessage();
        }

//            URLConnection urlConn;
//            DataOutputStream printout;
//            DataInputStream input;
//            URL url = new URL ("https://pal-test.adyen.com/pal/servlet/Payment/v30/authorise");
//            urlConn = url.openConnection();
//            urlConn.setDoInput (true);
//            urlConn.setDoOutput (true);
//            urlConn.setUseCaches (false);
//            urlConn.setRequestProperty("Content-Type","application/json");
////            urlConn.setRequestProperty("Host", "android.schoolportal.gr");
//            urlConn.connect();
//            //Create JSONObject here
//            JSONObject jsonParam = new JSONObject();
//            JSONObject amount = new JSONObject();
//            amount.put("value", "1");
//            amount.put("currency", "USD");
//            JSONObject additionalData = new JSONObject();
//            additionalData.put("paywithgoogle.token", token);
//            jsonParam.put("reference", "adyen");
//            jsonParam.put("TestMerchant", "N1989Account015");
//            jsonParam.put("amount", amount);
//            jsonParam.put("additionalData", additionalData);
//            printout = new DataOutputStream(urlConn.getOutputStream ());
//            printout.writeBytes(URLEncoder.encode(jsonParam.toString(),"UTF-8"));
//            printout.flush ();
////            printout.close ();
//
//
//
//            InputStream inputStream = urlConn.getInputStream();
////input stream
//            StringBuffer buffer = new StringBuffer();
//
//            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//
//            String inputLine;
//            while ((inputLine = reader.readLine()) != null)
//                buffer.append(inputLine + "\n");
//            if (buffer.length() == 0) {
//                // Stream was empty. No point in parsing.
//                return;
//            }
//            String JsonResponse = buffer.toString();
//            reader.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

}
