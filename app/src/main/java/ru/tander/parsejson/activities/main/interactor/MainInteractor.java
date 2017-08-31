package ru.tander.parsejson.activities.main.interactor;

import java.util.List;

import ru.tander.parsejson.entities.News;

/**
 * Created by savchenko on 31.08.17.
 */

public interface MainInteractor {
    News getNewsByPosition(int position);

    void getNewsList();

    void updateData();

    void swipeRefresh();

    void getNewsFromBase();

    interface OnChangesListener{
        void setData(List<News>newsList);

        void updateData(List<News>updateData);

        void showProgressBar();

        void hideProgressBar();

        void toastError();

        void stopSwipe();
    }
}
