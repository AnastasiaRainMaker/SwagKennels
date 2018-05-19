package com.project.swagkennels;

import com.project.swagkennels.models.News;
import com.project.swagkennels.presenters.NewsPresenter;
import com.project.swagkennels.presenters.NewsPresenterImpl;
import com.project.swagkennels.repository.FireBaseRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import java.util.ArrayList;
import java.util.Arrays;

public class NewsPresenterTest {

    private FireBaseRepository repository;
    private NewsPresenter.NewsView view;
    private NewsPresenterImpl presenter;
    private ArrayList<News> data = new ArrayList<>(Arrays.asList(new News(), new News(), new News()));
    private ArrayList<News> emptyData = new ArrayList<>();

    @Before
    public void setUp(){
        repository = Mockito.mock(FireBaseRepository.class);
        view = Mockito.mock(NewsPresenter.NewsView.class);
        presenter = new NewsPresenterImpl(view, repository);
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
        }).when(repository).getNews(presenter);

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
                presenter.onDataLoaded(emptyData);
                return null;
            }
        }).when(repository).getNews(presenter);

        // do
        presenter.loadData();

        // after
        Mockito.verify(view).displayNoData();
    }


}
