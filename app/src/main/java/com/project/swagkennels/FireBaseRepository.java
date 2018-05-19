package com.project.swagkennels;

import com.project.swagkennels.presenters.BreedingPresenter;
import com.project.swagkennels.presenters.DogsPresenter;
import com.project.swagkennels.presenters.NewsPresenter;
import com.project.swagkennels.presenters.PuppyPresenter;

public interface FireBaseRepository {

    void getNews(final NewsPresenter.NewsCallbacks listener);

    void getDogs(final DogsPresenter.DogsCallbacks listener);

    void getPuppies(final PuppyPresenter.PuppyCallback listener);

    void getBreedings(final BreedingPresenter.BreedingCallback listener);

    void getShopItems();
}
