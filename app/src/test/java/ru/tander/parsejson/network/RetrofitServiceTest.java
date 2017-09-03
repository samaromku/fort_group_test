package ru.tander.parsejson.network;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import ru.tander.parsejson.entities.News;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Andrey on 03.09.2017.
 */
public class RetrofitServiceTest {
    public static final String TAG = "RetrofitServiceTest";

    @Before
    public void setup(){
        RequestManager.init();
    }

    @Test
    public void getListObs() throws Exception {
        List<News>myNewsList = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            RequestManager.getRetrofitService().getListObs("file_" + i)

                    .subscribe(myNewsList::addAll);
            assertEquals(myNewsList.size(), 10);
            myNewsList.clear();
        }

    }

}