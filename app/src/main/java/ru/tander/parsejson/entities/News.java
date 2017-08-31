package ru.tander.parsejson.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by savchenko on 31.08.17.
 */

public class News extends RealmObject{

    @SerializedName("id")
    @Expose
    @PrimaryKey
    private int id;
    @SerializedName("text_id")
    @Expose
    private String textId;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("last_update")
    @Expose
    private String lastUpdate;
    @SerializedName("version")
    @Expose
    private int version;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("rubric")
    @Expose
    private String rubric;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("spb")
    @Expose
    private int spb;
    @SerializedName("json")
    @Expose
    private RealmList<Json> json = null;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("forum_id")
    @Expose
    private int forumId;
    @SerializedName("lead")
    @Expose
    private String lead;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("image_source")
    @Expose
    private String imageSource;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTextId() {
        return textId;
    }

    public void setTextId(String textId) {
        this.textId = textId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRubric() {
        return rubric;
    }

    public void setRubric(String rubric) {
        this.rubric = rubric;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSpb() {
        return spb;
    }

    public void setSpb(int spb) {
        this.spb = spb;
    }

    public List<Json> getJson() {
        return json;
    }

    public void setJson(RealmList<Json> json) {
        this.json =  json;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getForumId() {
        return forumId;
    }

    public void setForumId(int forumId) {
        this.forumId = forumId;
    }

    public String getLead() {
        return lead;
    }

    public void setLead(String lead) {
        this.lead = lead;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", textId='" + textId + '\'' +
                ", date='" + date + '\'' +
                ", lastUpdate='" + lastUpdate + '\'' +
                ", version=" + version +
                ", title='" + title + '\'' +
                ", rubric='" + rubric + '\'' +
                ", type='" + type + '\'' +
                ", spb=" + spb +
                ", json=" + json +
                ", link='" + link + '\'' +
                ", forumId=" + forumId +
                ", lead='" + lead + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", imageSource='" + imageSource + '\'' +
                '}';
    }
}