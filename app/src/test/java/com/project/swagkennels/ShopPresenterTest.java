package com.project.swagkennels;

import com.project.swagkennels.models.Item;
import com.project.swagkennels.presenters.ShopPresenter;
import com.project.swagkennels.presenters.ShopPresenterImpl;
import com.project.swagkennels.repository.FireBaseRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;

public class ShopPresenterTest {

    private ShopPresenterImpl presenter;
    private ShopPresenter.ShopView view;
    private FireBaseRepository repository;
    private ArrayList<Item> data = new ArrayList<>(Arrays.asList(new Item(), new Item(), new Item()));
    private ArrayList<Item> emptyData = new ArrayList<>();

    @Before
    public void setUp(){
        view = Mockito.mock(ShopPresenter.ShopView.class);
        repository = Mockito.mock(FireBaseRepository.class);
        presenter = new ShopPresenterImpl(view, repository);
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
        }).when(repository).getShopItems(presenter);

        // do
        presenter.loadData();

        // then
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
        }).when(repository).getShopItems(presenter);

        // do
        presenter.loadData();

        // then
        Mockito.verify(view).displayNoData();
    }

}
