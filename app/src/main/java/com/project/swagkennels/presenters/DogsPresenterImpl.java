package com.project.swagkennels.presenters;

import com.project.swagkennels.FireBaseRepository;
import com.project.swagkennels.pojo.Dog;

import java.util.ArrayList;

public class DogsPresenterImpl implements DogsPresenter, DogsPresenter.DogsCallbacks {

    private DogsPresenter.DogsView view;
    private FireBaseRepository fireBaseRepository;

    public DogsPresenterImpl(DogsPresenter.DogsView view, FireBaseRepository fireBaseRepository) {
        this.view = view;
        this.fireBaseRepository = fireBaseRepository;
    }

    @Override
    public void loadData() {
        fireBaseRepository.getDogs(this);
    }

    @Override
    public void onDataLoaded(ArrayList<Dog> data) {
        if (data != null && data.size() > 0){
            view.displayData(data);
        } else {
            view.displayNoData();
        }
    }
}
