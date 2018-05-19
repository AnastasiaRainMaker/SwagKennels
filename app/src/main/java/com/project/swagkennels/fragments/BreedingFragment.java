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
import com.project.swagkennels.adapters.BreedingFragmentAdapter;
import com.project.swagkennels.pojo.Breeding;
import com.project.swagkennels.pojo.Dog;
import com.project.swagkennels.R;
import com.project.swagkennels.adapters.PuppyFragmentAdapter;
import com.project.swagkennels.presenters.BreedingPresenter;
import com.project.swagkennels.presenters.BreedingPresenterImpl;

import java.util.ArrayList;

public class BreedingFragment extends Fragment implements BreedingPresenter.BreedingView {

    ArrayList<Breeding> puppyList;
    RecyclerView recyclerView;
    BreedingFragmentAdapter adapter;
    ProgressBar progressBar;
    BreedingPresenter presenter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_breeding, container, false);
        puppyList = new ArrayList<>();
        setUpViews(view);
        showProgress(true);
        presenter.loadData();
        return view;
    }

    private void setUpViews(View view) {
        presenter = new BreedingPresenterImpl(new FireBaseRepositoryImpl(), this);
        progressBar = view.findViewById(R.id.progressBarLoading);
        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new BreedingFragmentAdapter(puppyList, getContext());
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
    public void displayData(ArrayList<Breeding> data) {
        showProgress(false);
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void displayNoData() {
        //todo handle no data
    }
}
