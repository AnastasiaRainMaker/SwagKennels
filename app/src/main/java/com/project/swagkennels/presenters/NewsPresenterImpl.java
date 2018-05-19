package com.project.swagkennels.presenters;

import com.project.swagkennels.repository.FireBaseRepository;
import com.project.swagkennels.models.News;
import java.util.ArrayList;

public class NewsPresenterImpl implements NewsPresenter, NewsPresenter.NewsCallbacks {

    private NewsView view;
    private FireBaseRepository fireBaseRepositoryImpl;

    public NewsPresenterImpl(NewsView view, FireBaseRepository fireBaseRepositoryImpl) {
        this.view = view;
        this.fireBaseRepositoryImpl = fireBaseRepositoryImpl;
    }

    @Override
    public void loadData() {
        fireBaseRepositoryImpl.getNews(this);
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
