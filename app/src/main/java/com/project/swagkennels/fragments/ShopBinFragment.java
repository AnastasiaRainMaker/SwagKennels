package com.project.swagkennels.fragments;

import android.content.Context;
import android.content.DialogInterface;
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

import com.project.swagkennels.R;
import com.project.swagkennels.adapters.ShopBinFragmentAdapter;
import com.project.swagkennels.presenters.ShopBinPresenter;
import com.project.swagkennels.presenters.ShopBinPresenterImpl;
import com.project.swagkennels.repository.RoomRepository;
import com.project.swagkennels.room.PurchasedShopItem;

import java.util.ArrayList;

public class ShopBinFragment extends Fragment implements ShopBinPresenter.ShopBinView, ShopBinFragmentAdapter.ShopBinAdapterCallback {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private ShopBinFragmentAdapter adapter;
    private ShopBinPresenter presenter;
    private AppCompatActivity mActivity;
    private TextView emptyCart, totalTextView;
    private Button buyButton;

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
