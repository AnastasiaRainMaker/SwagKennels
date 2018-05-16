package com.project.swagkennels.presenters;

import com.project.swagkennels.News;

import java.util.ArrayList;

public interface NewsPresenter {

    void loadData();

    interface NewsView {
        void displayData(ArrayList<News> data);
        void displayNoData();
    }

    public interface NewsCallbacks {
        void onDataLoaded(ArrayList<News> data);
    }
}
