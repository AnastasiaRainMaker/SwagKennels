package com.project.swagkennels.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.swagkennels.Item;
import com.project.swagkennels.R;
import com.project.swagkennels.adapters.ShopFragmentAdapter;

import java.util.ArrayList;

public class ShopFragment extends Fragment {

    ArrayList<Item> itemsList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_shop, container, false);
        itemsList = new ArrayList<>();
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setHasFixedSize(true);

        itemsList.add(new Item(null, "T-shirt (S)", "10.99$"));
        itemsList.add(new Item(null, "T-shirt (S)", "10.99$"));
        itemsList.add(new Item(null, "T-shirt (S)", "10.99$"));
        itemsList.add(new Item(null, "T-shirt (S)", "10.99$"));
        itemsList.add(new Item(null, "T-shirt (S)", "10.99$"));
        itemsList.add(new Item(null, "T-shirt (S)", "10.99$"));
        itemsList.add(new Item(null, "T-shirt (S)", "10.99$"));
        itemsList.add(new Item(null, "T-shirt (S)", "10.99$"));
        itemsList.add(new Item(null, "T-shirt (S)", "10.99$"));
        itemsList.add(new Item(null, "T-shirt (S)", "10.99$"));
        itemsList.add(new Item(null, "T-shirt (S)", "10.99$"));
        itemsList.add(new Item(null, "T-shirt (S)", "10.99$"));
        itemsList.add(new Item(null, "T-shirt (S)", "10.99$"));
        itemsList.add(new Item(null, "T-shirt (S)", "10.99$"));

        recyclerView.setAdapter(new ShopFragmentAdapter(itemsList, getContext()));

        return view;
    }
}
