package com.project.swagkennels;

import com.project.swagkennels.pojo.Breeding;
import com.project.swagkennels.presenters.BreedingPresenter;
import com.project.swagkennels.presenters.BreedingPresenterImpl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;

public class BreedingPresenterTest {

    private FireBaseRepositoryImpl repository;
    private BreedingPresenter.BreedingView view;
    private BreedingPresenterImpl presenter;
    private ArrayList<Breeding> data = new ArrayList<>(Arrays.asList(new Breeding(), new Breeding(), new Breeding()));
    private ArrayList<Breeding> noData = new ArrayList<>();

    @Before
    public void setUp() {
        repository = Mockito.mock(FireBaseRepositoryImpl.class);
        view = Mockito.mock(BreedingPresenter.BreedingView.class);
        presenter = new BreedingPresenterImpl(repository, view);
    }

    @Test
    public void isDataDesplayed() {
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                presenter.onDataLoaded(data);
                return null;
            }
        }).when(repository).getBreedings(presenter);

        presenter.loadData();

        Mockito.verify(view).displayData(data);
    }

    @Test
    public void isNoDataDisplayed() {
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                presenter.onDataLoaded(noData);
                return null;
            }
        }).when(repository).getBreedings(presenter);

        presenter.loadData();

        Mockito.verify(view).displayNoData();
    }



}
