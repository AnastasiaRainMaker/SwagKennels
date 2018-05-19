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

import com.project.swagkennels.FireBaseRepositoryImpl;
import com.project.swagkennels.pojo.Dog;
import com.project.swagkennels.R;
import com.project.swagkennels.adapters.DogsFragmentAdapter;
import com.project.swagkennels.presenters.DogsPresenter;
import com.project.swagkennels.presenters.DogsPresenterImpl;
import com.project.swagkennels.presenters.NewsPresenter;
import com.project.swagkennels.presenters.NewsPresenterImpl;

import java.util.ArrayList;
import java.util.Objects;

public class DogsFragment extends Fragment implements DogsPresenter.DogsView {

    ArrayList<Dog> dogList;
    RecyclerView recyclerView;
    DogsFragmentAdapter adapter;
    ProgressBar progressBar;
    DogsPresenter presenter = null;
    View view;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dogs, container, false);
        dogList = new ArrayList<>();
        setUpView(view);
        showProgress(true);
        presenter = new DogsPresenterImpl(this, new FireBaseRepositoryImpl());
        presenter.loadData();
        return view;
    }

    private void setUpView(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new DogsFragmentAdapter(dogList, getContext());
        recyclerView.setAdapter(adapter);
        progressBar = view.findViewById(R.id.progressBarLoading);
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
    public void displayData(ArrayList<Dog> data) {
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
