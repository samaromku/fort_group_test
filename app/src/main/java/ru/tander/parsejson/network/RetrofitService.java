package ru.tander.parsejson.network;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.tander.parsejson.entities.News;

/**
 * Created by savchenko on 31.08.17.
 */

public interface RetrofitService {
    @GET("upload/api/materialjson/{fileNumber}.json")
    Observable<List<News>> getListObs(@Path("fileNumber")String fileNumber);
}
