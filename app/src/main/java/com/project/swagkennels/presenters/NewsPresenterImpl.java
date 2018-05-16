package com.project.swagkennels.presenters;

import com.project.swagkennels.FireBaseRepository;
import com.project.swagkennels.News;
import com.project.swagkennels.fragments.NewsFragment;

import java.util.ArrayList;

public class NewsPresenterImpl implements NewsPresenter, NewsPresenter.NewsCallbacks {

    private NewsView view;
    private FireBaseRepository fireBaseRepository;

    public NewsPresenterImpl(NewsView view, FireBaseRepository fireBaseRepository) {
        this.view = view;
        this.fireBaseRepository = fireBaseRepository;
    }

    @Override
    public void loadData() {
        fireBaseRepository.getNews(this);
    }

    @Override
    public void onDataLoaded(ArrayList<News> data) {
        if (data != null && data.size() > 0){
            view.displayData(data);
        } else {
            view.displayNoData();
        }
    }
}
