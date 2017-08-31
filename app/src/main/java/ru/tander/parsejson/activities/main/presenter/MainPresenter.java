package ru.tander.parsejson.activities.main.presenter;

import ru.tander.parsejson.entities.News;

/**
 * Created by savchenko on 31.08.17.
 */

public interface MainPresenter {
    News getNewsByPosition(int position);

    void onDestroy();

    void getNewsList();

    void updateData();

    void swipeRefresh();

    void getNewsFromBase();
}
