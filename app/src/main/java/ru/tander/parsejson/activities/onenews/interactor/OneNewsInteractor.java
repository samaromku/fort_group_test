package ru.tander.parsejson.activities.onenews.interactor;

/**
 * Created by Andrey on 31.08.2017.
 */

public interface OneNewsInteractor {
    void setId(int id, OnChangeListener onChangeListener);

    interface OnChangeListener {
        void setTitleText(String text);

        void setRubricText(String text);

        void setTypeText(String text);

        void setPictureImage(String imageText);

        void setVideoImage(String videoImage);
    }
}
