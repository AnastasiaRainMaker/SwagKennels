package com.project.swagkennels.presenters;

import com.project.swagkennels.pojo.News;
import java.util.ArrayList;

public interface NewsPresenter {

    void loadData();

    interface NewsView {
        void displayData(ArrayList<News> data);
        void displayNoData();
    }

    interface NewsCallbacks {
        void onDataLoaded(ArrayList<News> data);
    }
}
