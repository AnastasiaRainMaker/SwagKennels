package com.project.swagkennels.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.project.swagkennels.repository.FireBaseRepositoryImpl;
import com.project.swagkennels.R;
import com.project.swagkennels.adapters.PuppyFragmentAdapter;
import com.project.swagkennels.models.Puppy;
import com.project.swagkennels.presenters.PuppyPresenter;
import com.project.swagkennels.presenters.PuppyPresenterImpl;

import java.util.ArrayList;
import java.util.Objects;

public class PuppiesFragment extends Fragment implements PuppyPresenter.PuppyView {

    ArrayList<Puppy> puppyList;
    RecyclerView recyclerView;
    PuppyFragmentAdapter adapter;
    ProgressBar progressBar;
    PuppyPresenter presenter;
    View view;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_puppies, container, false);
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
        setViewLayout(R.layout.empty_page);
    }

    private void setViewLayout(int id){
        LayoutInflater inflater = (LayoutInflater) Objects.requireNonNull(getActivity()).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            view = inflater.inflate(id, null);
            ViewGroup rootView = (ViewGroup) getView();
            if (rootView != null) {
                rootView.removeAllViews();
                rootView.addView(view);
            }
        }
    }
}
