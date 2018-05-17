package com.project.swagkennels.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.project.swagkennels.pojo.Dog;
import com.project.swagkennels.R;
import com.project.swagkennels.adapters.PuppyFragmentAdapter;

import java.util.ArrayList;

public class PuppiesFragment extends Fragment {

    ArrayList<Dog> puppyList;
    RecyclerView recyclerView;
    PuppyFragmentAdapter adapter;
    ProgressBar progressBar;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_puppies, container, false);
        puppyList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        puppyList.add(new Dog(null, "Phantom","Available now"));
        puppyList.add(new Dog(null, "Tyson","Available now"));

        adapter = new PuppyFragmentAdapter(puppyList, getContext());
        recyclerView.setAdapter(adapter);
        return view;
    }

    public void showProgress(Boolean value) {
        if (value && progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }

        if (!value && progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }
}
