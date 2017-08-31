package ru.tander.parsejson.json;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import ru.tander.parsejson.entities.ContentImage;
import ru.tander.parsejson.entities.ContentVideo;
import ru.tander.parsejson.entities.Json;
import ru.tander.parsejson.entities.News;

/**
 * Created by savchenko on 31.08.17.
 */

public class TypeAdapterNews implements JsonDeserializer {
    Gson gson = new Gson();

    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return parseNews(json);
    }

    private News parseNews(JsonElement json){
        News news = gson.fromJson(json, News.class);
        if(json.getAsJsonObject().get("json")!=null) {
            JsonArray jsonArray = json.getAsJsonObject().get("json").getAsJsonArray();

            for (int j = 0; j < jsonArray.size(); j++) {
                Json oneJson = news.getJson().get(j);
                String type = jsonArray.get(j).getAsJsonObject().get("type").getAsString();
                JsonElement contentElement = jsonArray.get(j).getAsJsonObject().get("content");
                addContent(type, contentElement, oneJson);
            }
        }
        return news;
    }

    private void addContent(String type, JsonElement contentObject, Json oneJson){
        switch (type) {
            case "text":
                String content = contentObject.getAsString();
                oneJson.addContentText(content);
                break;
            case "image":
                List<ContentImage> contentImageList = new Gson().fromJson(
                        contentObject, new TypeToken<List<ContentImage>>() {}.getType());
                for(ContentImage image:contentImageList){
                    oneJson.addContentImage(image);
                }
                break;
            case "video":
                List<ContentVideo> contentVideoList = new Gson().fromJson(
                        contentObject, new TypeToken<List<ContentVideo>>() {}.getType());
                for(ContentVideo video:contentVideoList){
                    oneJson.addContentVideo(video);
                }
                break;
            default:
        }
    }
}
