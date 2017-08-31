package ru.tander.parsejson.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by savchenko on 31.08.17.
 */

public class ContentImage extends RealmObject{
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("source")
    @Expose
    private String source;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "ContentImage{" +
                "url='" + url + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
