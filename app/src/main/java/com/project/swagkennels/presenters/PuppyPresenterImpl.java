package com.project.swagkennels.presenters;

import com.project.swagkennels.repository.FireBaseRepository;
import com.project.swagkennels.models.Puppy;

import java.util.ArrayList;

public class PuppyPresenterImpl implements PuppyPresenter, PuppyPresenter.PuppyCallback {

    private FireBaseRepository repository;
    private PuppyPresenter.PuppyView view;

    public PuppyPresenterImpl(FireBaseRepository repository, PuppyPresenter.PuppyView view) {
        this.repository = repository;
        this.view = view;
    }

    @Override
    public void loadData() {
        repository.getPuppies(this);
    }

    @Override
    public void onDataLoaded(ArrayList<Puppy> data) {
        if (data != null && data.size() > 0){
            view.displayData(data);
        } else {
            view.displayNoData();
        }
    }
}
