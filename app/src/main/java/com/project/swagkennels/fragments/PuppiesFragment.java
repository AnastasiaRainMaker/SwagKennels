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

import com.project.swagkennels.FireBaseRepository;
import com.project.swagkennels.FireBaseRepositoryImpl;
import com.project.swagkennels.R;
import com.project.swagkennels.adapters.PuppyFragmentAdapter;
import com.project.swagkennels.pojo.Puppy;
import com.project.swagkennels.presenters.PuppyPresenter;
import com.project.swagkennels.presenters.PuppyPresenterImpl;

import java.util.ArrayList;

public class PuppiesFragment extends Fragment implements PuppyPresenter.PuppyView {

    ArrayList<Puppy> puppyList;
    RecyclerView recyclerView;
    PuppyFragmentAdapter adapter;
    ProgressBar progressBar;
    PuppyPresenter presenter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_puppies, container, false);
        puppyList = new ArrayList<>();
        showProgress(true);
        setUpViews(view);
        presenter = new PuppyPresenterImpl(new FireBaseRepositoryImpl(), this);
        presenter.loadData();
        return view;
    }

    public void setUpViews(View view) {
        progressBar = view.findViewById(R.id.progressBarLoading);
        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PuppyFragmentAdapter(puppyList, getContext());
        recyclerView.setAdapter(adapter);
    }

    public void showProgress(Boolean value) {
        if (value && progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }

        if (!value && progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void displayData(ArrayList<Puppy> data) {
        showProgress(false);
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void displayNoData() {
        //todo handle no data
    }
}
