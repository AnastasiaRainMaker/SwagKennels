package com.project.swagkennels;

import com.project.swagkennels.pojo.Dog;
import com.project.swagkennels.presenters.DogsPresenter;
import com.project.swagkennels.presenters.DogsPresenterImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import java.util.ArrayList;
import java.util.Arrays;

public class DogsPresenterTest {

    private FireBaseRepositoryImpl repository;
    private DogsPresenter.DogsView view;
    private DogsPresenterImpl presenter;
    private ArrayList<Dog> data = new ArrayList<>(Arrays.asList(new Dog(), new Dog(), new Dog()));
    private ArrayList<Dog> noData = new ArrayList<>();

    @Before
    public void setUp(){
        repository = Mockito.mock(FireBaseRepositoryImpl.class);
        view = Mockito.mock(DogsPresenter.DogsView.class);
        presenter = new DogsPresenterImpl(view, repository);
    }

    @Test
    public void isDataDisplayed(){
        // when
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                presenter.onDataLoaded(data);
                return null;
            }
        }).when(repository).getDogs(presenter);

        // do
        presenter.loadData();

        // after
        Mockito.verify(view).displayData(data);
    }

    @Test
    public void isNoDataDisplayed(){
        // when
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                presenter.onDataLoaded(noData);
                return null;
            }
        }).when(repository).getDogs(presenter);

        // do
        presenter.loadData();

        // after
        Mockito.verify(view).displayNoData();
    }
}
