package ru.tander.parsejson.entities;

import io.realm.RealmObject;

/**
 * Created by Andrey on 31.08.2017.
 */

public class ContentText extends RealmObject {
    private String contentText;

    public ContentText() {
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public ContentText(String contentText) {
        this.contentText = contentText;
    }
}
