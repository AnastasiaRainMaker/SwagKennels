package com.project.swagkennels.repository;

import com.project.swagkennels.presenters.BreedingPresenter;
import com.project.swagkennels.presenters.DogsPresenter;
import com.project.swagkennels.presenters.NewsPresenter;
import com.project.swagkennels.presenters.PuppyPresenter;
import com.project.swagkennels.presenters.ShopPresenter;

public interface FireBaseRepository {

    void getNews(final NewsPresenter.NewsCallbacks listener);

    void getDogs(final DogsPresenter.DogsCallbacks listener);

    void getPuppies(final PuppyPresenter.PuppyCallback listener);

    void getBreedings(final BreedingPresenter.BreedingCallback listener);

    void getShopItems(ShopPresenter.ShopCallbacks listener);
}
