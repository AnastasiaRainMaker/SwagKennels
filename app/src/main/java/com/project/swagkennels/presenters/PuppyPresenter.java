package com.project.swagkennels.presenters;
;
import com.project.swagkennels.models.Puppy;

import java.util.ArrayList;

public interface PuppyPresenter {

    void loadData();

    interface PuppyView {
        void displayData(ArrayList<Puppy> data);
        void displayNoData();
    }

    interface PuppyCallback {
        void onDataLoaded(ArrayList<Puppy> data);
    }
}
