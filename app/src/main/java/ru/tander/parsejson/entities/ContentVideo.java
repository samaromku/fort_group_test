package ru.tander.parsejson.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by savchenko on 31.08.17.
 */

public class ContentVideo extends RealmObject{
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("poster")
    @Expose
    private String poster;

    @Override
    public String toString() {
        return "ContentVideo{" +
                "url='" + url + '\'' +
                ", source='" + source + '\'' +
                ", poster='" + poster + '\'' +
                '}';
    }

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

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
