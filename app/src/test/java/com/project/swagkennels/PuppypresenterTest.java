package com.project.swagkennels;

import com.project.swagkennels.models.Puppy;
import com.project.swagkennels.presenters.PuppyPresenter;
import com.project.swagkennels.presenters.PuppyPresenterImpl;
import com.project.swagkennels.repository.FireBaseRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;

public class PuppypresenterTest {

    private FireBaseRepository repository;
    private PuppyPresenter.PuppyView view;
    private PuppyPresenterImpl presenter;
    private ArrayList<Puppy> data = new ArrayList<>(Arrays.asList(new Puppy(), new Puppy(), new Puppy()));
    private ArrayList<Puppy> noData = new ArrayList<>();

    @Before
    public void setUp() {
        repository = Mockito.mock(FireBaseRepository.class);
        view = Mockito.mock(PuppyPresenter.PuppyView.class);
        presenter = new PuppyPresenterImpl(repository, view);
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
        }).when(repository).getPuppies(presenter);

        // do
        presenter.loadData();

        // after
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
        }).when(repository).getPuppies(presenter);

        presenter.loadData();

        Mockito.verify(view).displayNoData();
    }
}
