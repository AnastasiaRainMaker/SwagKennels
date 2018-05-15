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

import com.project.swagkennels.News;
import com.project.swagkennels.R;
import com.project.swagkennels.adapters.NewsFragmentAdapter;

import java.util.ArrayList;

public class NewsFragment extends Fragment {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    NewsFragmentAdapter adapter;
    ArrayList<News> newsList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBarLoading);
        newsList = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        newsList.add(new News(null, "05/13/2018", "I'm the cutest dog ever! I'm the cutest dog ever!\n" +
                "             I'm the cutest dog ever!\n" +
                "            I'm the cutest dog ever!\n" +
                "            I'm the cutest dog ever!\n" +
                "            I'm the cutest dog ever!\n" +
                "            I'm the cutest dog ever!\n" +
                "            I'm the cutest dog ever!", "My link to youtube"));
        newsList.add(new News(null, "05/13/2018", "I'm the best puppy in the world. I'm the cutest dog ever!\n" +
                "            I'm the cutest dog ever!\n" +
                "             I'm the cutest dog ever!\n" +
                "            I'm the cutest dog ever!\n" +
                "            I'm the cutest dog ever!\n" +
                "            I'm the cutest dog ever!\n" +
                "            I'm the cutest dog ever!\n" +
                "            I'm the cutest dog ever!", "My link to youtube"));
        newsList.add(new News(null, "05/13/2018", "I'm the best puppy in the world", "My link to youtube"));
        newsList.add(new News(null, "05/13/2018", "I'm the best puppy in the world", null));
        newsList.add(new News(null, "05/13/2018", "I'm the best puppy in the world", "My link to youtube"));
        newsList.add(new News(null, "05/13/2018", "I'm the best puppy in the world", "My link to youtube"));
        newsList.add(new News(null, "05/13/2018", "I'm the best puppy in the world. I'm the cutest dog ever!\n" +
                "            I'm the cutest dog ever!\n" +
                "             I'm the cutest dog ever!\n" +
                "            I'm the cutest dog ever!\n" +
                "            I'm the cutest dog ever!\n" +
                "            I'm the cutest dog ever!\n" +
                "            I'm the cutest dog ever!\n" +
                "            I'm the cutest dog ever!", null));

        adapter = new NewsFragmentAdapter(newsList, getContext());
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
