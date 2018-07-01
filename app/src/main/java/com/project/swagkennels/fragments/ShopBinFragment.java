package com.project.swagkennels.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wallet.AutoResolveHelper;
import com.google.android.gms.wallet.CardRequirements;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.PaymentData;
import com.google.android.gms.wallet.PaymentDataRequest;
import com.google.android.gms.wallet.PaymentMethodTokenizationParameters;
import com.google.android.gms.wallet.PaymentsClient;
import com.google.android.gms.wallet.TransactionInfo;
import com.google.android.gms.wallet.Wallet;
import com.google.android.gms.wallet.WalletConstants;
import com.project.swagkennels.R;
import com.project.swagkennels.adapters.ShopBinFragmentAdapter;
import com.project.swagkennels.presenters.ShopBinPresenter;
import com.project.swagkennels.presenters.ShopBinPresenterImpl;
import com.project.swagkennels.repository.RoomRepository;
import com.project.swagkennels.room.PurchasedShopItem;

import java.util.ArrayList;
import java.util.Arrays;

public class ShopBinFragment extends Fragment implements ShopBinPresenter.ShopBinView, ShopBinFragmentAdapter.ShopBinAdapterCallback {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private ShopBinFragmentAdapter adapter;
    private ShopBinPresenter presenter;
    private AppCompatActivity mActivity;
    private TextView emptyCart, totalTextView;
    private Button buyButton;
    private PaymentsClient mPaymentsClient;
    public static final int LOAD_PAYMENT_DATA_REQUEST_CODE = 7999;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (AppCompatActivity) context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop_bin_list, container, false);
        setUpViews(view);
        presenter = new ShopBinPresenterImpl(this, new RoomRepository(mActivity.getApplication()));
        showProgress(true);
        presenter.loadData();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDestroy();
    }

    public void showProgress(Boolean value) {
        if (value && progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
        if (!value && progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }

    public void setUpViews(View view) {
        buyButton = view.findViewById(R.id.buyButton);
        totalTextView = view.findViewById(R.id.textViewTotal);
        emptyCart = view.findViewById(R.id.textViewEmptyBin);
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBarLoading);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ShopBinFragmentAdapter(new ArrayList<PurchasedShopItem>(), this);
        recyclerView.setAdapter(adapter);
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isReadyToPay();
            }
        });

        mPaymentsClient =
                Wallet.getPaymentsClient(
                        mActivity,
                        new Wallet.WalletOptions.Builder()
                                .setEnvironment(WalletConstants.ENVIRONMENT_TEST)
                                .build()
                );
    }

    private void isReadyToPay() {
        IsReadyToPayRequest request =
                IsReadyToPayRequest.newBuilder()
                        .addAllowedPaymentMethod(WalletConstants.PAYMENT_METHOD_CARD)
                        .addAllowedPaymentMethod(WalletConstants.PAYMENT_METHOD_TOKENIZED_CARD)
                        .build();
        Task<Boolean> task = mPaymentsClient.isReadyToPay(request);
        task.addOnCompleteListener(
                new OnCompleteListener<Boolean>() {
                    public void onComplete(Task<Boolean> task) {
                        try {
                            boolean result = task.getResult(ApiException.class);
                            if (result) {
                                PaymentDataRequest request = createPaymentDataRequest();
                                if (request != null) {
                                    AutoResolveHelper.resolveTask(
                                            mPaymentsClient.loadPaymentData(request),
                                            mActivity,
                                            // LOAD_PAYMENT_DATA_REQUEST_CODE is a constant value
                                            // you define.
                                            LOAD_PAYMENT_DATA_REQUEST_CODE);
                                }
                            } else {
                                String s;
                                // Hide Google as payment option.
                            }
                        } catch (ApiException exception) {
                            String s;
                        }
                    }
                });
    }

    private PaymentDataRequest createPaymentDataRequest() {
        PaymentDataRequest.Builder request =
                PaymentDataRequest.newBuilder()
                        .setTransactionInfo(
                                TransactionInfo.newBuilder()
                                        .setTotalPriceStatus(WalletConstants.TOTAL_PRICE_STATUS_FINAL)
                                        .setTotalPrice("1.00")
                                        .setCurrencyCode("USD")
                                        .build())
                        .addAllowedPaymentMethod(WalletConstants.PAYMENT_METHOD_CARD)
                        .addAllowedPaymentMethod(WalletConstants.PAYMENT_METHOD_TOKENIZED_CARD)
                        .setCardRequirements(
                                CardRequirements.newBuilder()
                                        .addAllowedCardNetworks(
                                                Arrays.asList(
                                                        WalletConstants.CARD_NETWORK_AMEX,
                                                        WalletConstants.CARD_NETWORK_DISCOVER,
                                                        WalletConstants.CARD_NETWORK_VISA,
                                                        WalletConstants.CARD_NETWORK_MASTERCARD))
                                        .build());

        PaymentMethodTokenizationParameters params =
                PaymentMethodTokenizationParameters.newBuilder()
                        .setPaymentMethodTokenizationType(
                                WalletConstants.PAYMENT_METHOD_TOKENIZATION_TYPE_PAYMENT_GATEWAY)
                        .addParameter("gateway", "adyen")
                        .addParameter("gatewayMerchantId", "N1989Account015")
                        .build();

        request.setPaymentMethodTokenizationParameters(params);
        return request.build();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case LOAD_PAYMENT_DATA_REQUEST_CODE:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        PaymentData paymentData = PaymentData.getFromIntent(data);
                        String token = paymentData.getPaymentMethodToken().getToken();
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

    @Override
    public void displayData(ArrayList<PurchasedShopItem> data) {
        showProgress(false);
        emptyCart.setVisibility(View.INVISIBLE);
        buyButton.setVisibility(View.VISIBLE);
        totalTextView.setVisibility(View.VISIBLE);
        adapter.setData(data);
        adapter.notifyDataSetChanged();
        showTotalAmount();
    }

    private void showTotalAmount() {
        Integer totalAmount = presenter.getTotalAmount();
        Float totalPrice = presenter.getTotalAmountPrice();
        totalTextView.setText(String.format(getString(R.string.msg_total), totalPrice, totalAmount));
    }

    @Override
    public void displayNoData() {
        showProgress(false);
        recyclerView.setVisibility(View.INVISIBLE);
        emptyCart.setVisibility(View.VISIBLE);
        buyButton.setVisibility(View.GONE);
        totalTextView.setVisibility(View.GONE);
    }

    @Override
    public void onItemRemoved(PurchasedShopItem item) {
        adapter.removeItem(item);
    }

    private void showRemoveItemDialog(final PurchasedShopItem item) {
        AlertDialog alertDialog = new AlertDialog.Builder(mActivity, R.style.MyDialogTheme).create();
        alertDialog.setMessage(getString(R.string.msg_remove_item));
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        presenter.removeItem(item);
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
    public void onBinIconClicked(PurchasedShopItem item) {
        showRemoveItemDialog(item);
    }

}
