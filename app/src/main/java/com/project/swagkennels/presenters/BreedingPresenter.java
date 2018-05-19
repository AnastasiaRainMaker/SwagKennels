package com.project.swagkennels.presenters;

import com.project.swagkennels.models.Breeding;

import java.util.ArrayList;

public interface BreedingPresenter {

    void loadData();

    interface BreedingView {
        void displayData(ArrayList<Breeding> data);
        void displayNoData();
    }

    interface BreedingCallback {
        void onDataLoaded(ArrayList<Breeding> data);
    }
}
