package ru.tander.parsejson.activities.onenews.presenter;

import ru.tander.parsejson.activities.onenews.interactor.OneNewsInteractor;
import ru.tander.parsejson.activities.onenews.interactor.OneNewsInteractorImpl;
import ru.tander.parsejson.activities.onenews.view.OneNewsView;

/**
 * Created by Andrey on 31.08.2017.
 */

public class OneNewsPresenterImpl implements OneNewsPresenter, OneNewsInteractor.OnChangeListener {
    private OneNewsInteractor interactor;
    private OneNewsView view;

    public OneNewsPresenterImpl(OneNewsView view) {
        this.interactor = new OneNewsInteractorImpl();
        this.view = view;
    }

    @Override
    public void setId(int id) {
        interactor.setId(id, this);
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void setTitleText(String text) {
        view.setTitleText(text);
    }

    @Override
    public void setRubricText(String text) {
        view.setRubricText(text);
    }

    @Override
    public void setTypeText(String text) {
        view.setTypeText(text);
    }

    @Override
    public void setPictureImage(String imageText) {
        view.setPictureImage(imageText);
    }

    @Override
    public void setVideoImage(String videoImage) {
        view.setVideoImage(videoImage);
    }
}
