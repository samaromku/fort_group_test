package ru.tander.parsejson.activities.main.presenter;

import java.util.List;

import ru.tander.parsejson.activities.main.interactor.MainInsteractorImpl;
import ru.tander.parsejson.activities.main.interactor.MainInteractor;
import ru.tander.parsejson.activities.main.view.MainView;
import ru.tander.parsejson.entities.News;

/**
 * Created by savchenko on 31.08.17.
 */

public class MainPresenterImpl implements MainPresenter, MainInteractor.OnChangesListener {
    private MainInteractor interactor;
    private MainView view;

    public MainPresenterImpl(MainView view) {
        this.interactor = new MainInsteractorImpl(this);
        this.view = view;
    }

    @Override
    public News getNewsByPosition(int position) {
        return interactor.getNewsByPosition(position);
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void getNewsList() {
        interactor.getNewsList();
    }

    @Override
    public void setData(List<News> newsList) {
        view.setDataNotifyAdapter(newsList);
    }

    @Override
    public void swipeRefresh() {
        interactor.swipeRefresh();
    }

    @Override
    public void toastError() {
        view.toastError();
    }

    @Override
    public void getNewsFromBase() {
        interactor.getNewsFromBase();
    }

    @Override
    public void stopSwipe() {
        view.stopSwipe();
    }

    @Override
    public void updateData() {
        interactor.updateData();
    }

    @Override
    public void updateData(List<News> updateData) {
        view.updateData(updateData);
    }

    @Override
    public void showProgressBar() {
        view.showProgressBar();
    }

    @Override
    public void hideProgressBar() {
        view.hideProgressBar();
    }
}
