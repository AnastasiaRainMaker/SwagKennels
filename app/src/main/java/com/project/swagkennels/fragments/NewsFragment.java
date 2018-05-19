package com.project.swagkennels.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.project.swagkennels.FireBaseRepositoryImpl;
import com.project.swagkennels.pojo.News;
import com.project.swagkennels.R;
import com.project.swagkennels.adapters.NewsFragmentAdapter;
import com.project.swagkennels.presenters.NewsPresenter;
import com.project.swagkennels.presenters.NewsPresenterImpl;

import java.util.ArrayList;
import java.util.Objects;

public class NewsFragment extends Fragment implements NewsPresenter.NewsView {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    NewsFragmentAdapter adapter;
    ArrayList<News> newsList = new ArrayList<>();
    NewsPresenter presenter = null;
    View view;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_news_list, container, false);
        setUpViews(view);
        showProgress(true);
        presenter = new NewsPresenterImpl(this, new FireBaseRepositoryImpl());
        presenter.loadData();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("fragment", " onDestroy called for fragment News");
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
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBarLoading);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new NewsFragmentAdapter(newsList, getContext());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void displayData(ArrayList<News> data) {
        showProgress(false);
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void displayNoData() {
        showProgress(false);
        recyclerView.setVisibility(View.INVISIBLE);
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
