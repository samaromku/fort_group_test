package ru.tander.parsejson.activities.main.interactor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.tander.parsejson.entities.News;
import ru.tander.parsejson.network.RequestManager;
import ru.tander.parsejson.repository.NewsRepository;

/**
 * Created by savchenko on 31.08.17.
 */

public class MainInsteractorImpl implements MainInteractor {
    private OnChangesListener listener;
    private List<News>newsList;
    private int counter = 2;
    private long lastUpdate;

    public MainInsteractorImpl(OnChangesListener onChangesListener) {
        listener = onChangesListener;
        newsList = new ArrayList<>();
    }

    @Override
    public News getNewsByPosition(int position) {
        return newsList.get(position);
    }

    @Override
    public void getNewsList() {
        lastUpdate = new Date().getTime();
        RequestManager.getRetrofitService().getListObs("file_1")
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> listener.showProgressBar())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> listener.hideProgressBar())
                .subscribe(this::onSuccess, this::onError);
    }

    @Override
    public void getNewsFromBase() {
        listener.setData(new NewsRepository().getAllNews());
        newsList.clear();
        newsList.addAll(new NewsRepository().getAllNews());
    }

    private void onError(Throwable throwable) {
        throwable.printStackTrace();
        listener.toastError();
    }

    private void onSuccess(List<News> newses) {
        newsList.clear();
        newsList.addAll(newses);
        listener.setData(newses);
        listener.hideProgressBar();
        new NewsRepository().addListNews(newses);
    }

    @Override
    public void updateData() {
        if(counter<10){
            RequestManager.getRetrofitService().getListObs("file_" + counter)
                    .subscribeOn(Schedulers.io())
                    .doOnSubscribe(disposable -> listener.showProgressBar())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .doFinally(() -> listener.hideProgressBar())
                    .subscribe(this::successUpdate, this::errorUpdate);
            counter = counter+1;
        }
    }

    private void successUpdate(List<News> newses) {
        new NewsRepository().addListNews(newses);
        newsList.addAll(newses);
        listener.updateData(newses);
    }

    private void errorUpdate(Throwable throwable) {
        throwable.printStackTrace();
        listener.toastError();
    }

    @Override
    public void swipeRefresh() {
        if(!checkDate()) {
            getNewsList();
            counter = 2;
        }
        listener.stopSwipe();
    }

    private boolean checkDate(){
        long now = new Date().getTime();
        return now-lastUpdate < (1000*60*5);
    }
}
