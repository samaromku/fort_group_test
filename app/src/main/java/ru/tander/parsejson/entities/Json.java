package ru.tander.parsejson.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by savchenko on 31.08.17.
 */

public class Json extends RealmObject{

    @SerializedName("type")
    @Expose
    private String type;
    private RealmList<ContentText> contentText;
    private RealmList<ContentImage> contentImage;
    private RealmList<ContentVideo> contentVideo;

    public Json() {
        contentText = new RealmList<>();
        contentImage = new RealmList<>();
        contentVideo = new RealmList<>();
    }

    public List<ContentText> getContentText() {
        return contentText;
    }

    public void setContentText(RealmList<ContentText> contentText) {
        this.contentText =  contentText;
    }

    public List<ContentImage> getContentImage() {
        return contentImage;
    }

    public void setContentImage(RealmList<ContentImage> contentImage) {
        this.contentImage =  contentImage;
    }

    public List<ContentVideo> getContentVideo() {
        return contentVideo;
    }

    public void setContentVideo(RealmList<ContentVideo> contentVideo) {
        this.contentVideo =  contentVideo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

//    public T getContent() {
//        return content;
//    }

    public void addContentText(String text){
        if(text==null)return;
        contentText.add(new ContentText(text));
    }

    public void addContentImage(ContentImage image){
        if(contentImage==null)return;
        contentImage.add(image);
    }

    public void addContentVideo(ContentVideo video){
        if(video==null)return;
        contentVideo.add(video);
    }



//    public void setContent(T content) {
//        this.content = content;
//    }

    @Override
    public String toString() {
        return "Json{" +
                "type='" + type + '\'' +
//                ", content=" + content +
                ", contentText='" + contentText + '\'' +
                ", contentImage=" + contentImage +
                ", contentVideo=" + contentVideo +
                '}';
    }
}