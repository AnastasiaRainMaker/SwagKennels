package com.project.swagkennels.presenters;

import com.project.swagkennels.repository.FireBaseRepository;
import com.project.swagkennels.models.Breeding;
import java.util.ArrayList;

public class BreedingPresenterImpl implements BreedingPresenter, BreedingPresenter.BreedingCallback {

    private FireBaseRepository repository;
    private BreedingPresenter.BreedingView view;

    public BreedingPresenterImpl(FireBaseRepository repository, BreedingPresenter.BreedingView view) {
        this.repository = repository;
        this.view = view;
    }

    @Override
    public void loadData() {
        repository.getBreedings(this);
    }

    @Override
    public void onDataLoaded(ArrayList<Breeding> data) {
        if (data != null && data.size() > 0) {
            view.displayData(data);
        } else {
            view.displayNoData();
        }
    }
}
