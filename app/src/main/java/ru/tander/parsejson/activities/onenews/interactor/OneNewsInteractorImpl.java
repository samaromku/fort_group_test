package ru.tander.parsejson.activities.onenews.interactor;

import ru.tander.parsejson.entities.Json;
import ru.tander.parsejson.entities.News;
import ru.tander.parsejson.repository.NewsRepository;

/**
 * Created by Andrey on 31.08.2017.
 */

public class OneNewsInteractorImpl implements OneNewsInteractor {


    @Override
    public void setId(int id, OnChangeListener onChangeListener) {
        News news = new NewsRepository().getNewsById(id);

        onChangeListener.setTitleText(news.getTitle());

        onChangeListener.setTypeText(news.getType());

        onChangeListener.setRubricText(news.getRubric());

        String imageUrl = null;
        String videoUrl = null;
        for(Json json:news.getJson()){
            if(!json.getContentImage().isEmpty()) {
                imageUrl = json.getContentImage().get(0).getUrl();
            }
            if(!json.getContentVideo().isEmpty()){
                videoUrl = json.getContentVideo().get(0).getPoster();
            }
        }


        onChangeListener.setPictureImage(imageUrl);

        onChangeListener.setVideoImage(videoUrl);
    }
}
