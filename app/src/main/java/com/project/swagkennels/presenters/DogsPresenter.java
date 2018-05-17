package com.project.swagkennels.presenters;

import com.project.swagkennels.pojo.Dog;
import java.util.ArrayList;

public interface DogsPresenter {

    void loadData();

    interface DogsView {
        void displayData(ArrayList<Dog> data);
        void displayNoData();
    }

    interface DogsCallbacks {
        void onDataLoaded(ArrayList<Dog> data);
    }
}
