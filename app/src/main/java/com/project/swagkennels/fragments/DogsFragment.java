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

import com.project.swagkennels.Dog;
import com.project.swagkennels.News;
import com.project.swagkennels.R;
import com.project.swagkennels.adapters.DogsFragmentAdapter;
import com.project.swagkennels.adapters.NewsFragmentAdapter;

import java.util.ArrayList;

public class DogsFragment extends Fragment {

    ArrayList<Dog> dogList;
    RecyclerView recyclerView;
    DogsFragmentAdapter adapter;
    ProgressBar progressBar;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dogs, container, false);
        dogList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        dogList.add(new Dog(null, "Milady","Our beautiful dog!"));
        dogList.add(new Dog(null, "Heize","Our beautiful dog!"));
        dogList.add(new Dog(null, "Khalissi","Our beautiful dog!"));
        dogList.add(new Dog(null, "Swaggy","Our beautiful dog!"));


        adapter = new DogsFragmentAdapter(dogList, getContext());
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
