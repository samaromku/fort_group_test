package ru.tander.parsejson;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by Andrey on 31.08.2017.
 */

public class BaseActivity extends AppCompatActivity{

    protected void initToolbar(String title){
        if(getSupportActionBar()==null)return;
        getSupportActionBar().setTitle(title);
    }

}
