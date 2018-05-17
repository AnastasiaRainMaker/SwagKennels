package com.project.swagkennels;

import com.project.swagkennels.presenters.DogsPresenter;
import com.project.swagkennels.presenters.NewsPresenter;

public interface FireBaseRepository {

    void getNews(final NewsPresenter.NewsCallbacks listener);

    void getDogs(final DogsPresenter.DogsCallbacks listener);

    void getPuppies();

    void getBreedings();

    void getShopItems();
}
