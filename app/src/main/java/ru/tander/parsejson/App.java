package ru.tander.parsejson;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import ru.tander.parsejson.network.RequestManager;

/**
 * Created by savchenko on 31.08.17.
 */

public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        RequestManager.init();
        initRealm();
    }

    private void initRealm() {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("news").build();
        Realm.setDefaultConfiguration(config);
    }
}
