package ru.tander.parsejson.repository;

import java.util.List;

import io.realm.Realm;
import ru.tander.parsejson.entities.News;

/**
 * Created by Andrey on 31.08.2017.
 */

public class NewsRepository {
    private Realm realmInstance(){
        return Realm.getDefaultInstance();
    }

    public void addListNews(List<News>newsList){
        realmInstance().executeTransaction(realm -> {
            realm.insertOrUpdate(newsList);
        });
        realmInstance().close();
    }

    public List<News>getAllNews(){
        List<News>newses = realmInstance()
                .where(News.class)
                .findAll();
        realmInstance().close();
        return newses;
    }

    public News getNewsById(int id){
        News news = realmInstance().where(News.class).equalTo("id", id).findFirst();
        realmInstance().close();
        return news;
    }
}
