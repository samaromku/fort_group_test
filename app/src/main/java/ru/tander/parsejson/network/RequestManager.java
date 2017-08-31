package ru.tander.parsejson.network;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.tander.parsejson.entities.News;
import ru.tander.parsejson.json.TypeAdapterNews;

/**
 * Created by savchenko on 31.08.17.
 */

public class RequestManager {
    private static Retrofit retrofit;

    public static void init() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(News.class, new TypeAdapterNews())
                .enableComplexMapKeySerialization()
                .serializeNulls()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .setVersion(1.0)
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://fgsoft.ru/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

    public static RetrofitService getRetrofitService() {
        return createService(RetrofitService.class);
    }

}
