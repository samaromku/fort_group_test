package ru.tander.parsejson.activities.main.view;

import java.util.List;

import ru.tander.parsejson.entities.News;

/**
 * Created by savchenko on 31.08.17.
 */

public interface MainView {
    void setDataNotifyAdapter(List<News>data);

    void updateData(List<News>data);

    void showProgressBar();

    void hideProgressBar();

    void toastError();

    void stopSwipe();
}
