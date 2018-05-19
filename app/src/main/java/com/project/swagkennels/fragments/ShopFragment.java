package com.project.swagkennels.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.swagkennels.repository.FireBaseRepositoryImpl;
import com.project.swagkennels.models.Item;
import com.project.swagkennels.R;
import com.project.swagkennels.adapters.ShopFragmentAdapter;
import com.project.swagkennels.presenters.ShopPresenter;
import com.project.swagkennels.presenters.ShopPresenterImpl;

import java.util.ArrayList;

public class ShopFragment extends Fragment implements ShopPresenter.ShopView {

    private ShopPresenter presenter;
    private ShopFragmentAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_shop, container, false);
        setUpViews(view);
        presenter = new ShopPresenterImpl(this, new FireBaseRepositoryImpl());
        presenter.loadData();
        return view;
    }

    @Override
    public void displayData(ArrayList<Item> data) {
        recyclerView.setVisibility(View.VISIBLE);
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void displayNoData() {
        recyclerView.setVisibility(View.INVISIBLE);
    }

    public void setUpViews(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setHasFixedSize(true);

        adapter = new ShopFragmentAdapter(new ArrayList<Item>(), getContext());
        recyclerView.setAdapter(adapter);
    }
}
