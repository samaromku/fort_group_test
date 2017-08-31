package ru.tander.parsejson.activities.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import ru.tander.parsejson.BaseActivity;
import ru.tander.parsejson.R;
import ru.tander.parsejson.activities.OneNewsActivity;
import ru.tander.parsejson.activities.main.presenter.MainPresenter;
import ru.tander.parsejson.activities.main.presenter.MainPresenterImpl;
import ru.tander.parsejson.activities.main.view.MainView;
import ru.tander.parsejson.adapters.NewsAdapter;
import ru.tander.parsejson.entities.News;
import ru.tander.parsejson.interfaces.OnItemClickListener;
import ru.tander.parsejson.storage.Utils;

public class MainActivity extends BaseActivity implements MainView, OnItemClickListener{
    public static final String TAG = "MainActivity";
    private MainPresenter presenter;
    private NewsAdapter adapter;
    private ContentLoadingProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
        init();
        initToolbar(getString(R.string.all_news));
    }

    private void init() {
        progressBar = (ContentLoadingProgressBar) findViewById(R.id.progress_bar);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            if(checkNetwork()) {
                presenter.swipeRefresh();
            }else {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private boolean checkNetwork(){
        return Utils.checkNetworkAvaliable(this);
    }

    private void initRecyclerView() {
        RecyclerView rvNews = (RecyclerView) findViewById(R.id.rvNews);
        rvNews.setLayoutManager(new LinearLayoutManager(this));
        presenter = new MainPresenterImpl(this);
        adapter = new NewsAdapter(this);
        if(checkNetwork()) {
            presenter.getNewsList();
        }else {
            presenter.getNewsFromBase();
        }
        rvNews.setAdapter(adapter);
        rvNews.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                recyclerView.post(() -> recyclerView.getAdapter().notifyDataSetChanged());
            }
        });
    }

    @Override
    public void setDataNotifyAdapter(List<News> data) {
        adapter.setData(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void toastError() {
        Toast.makeText(this, "Ошибка при получении данных", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }


    @Override
    public void updateData() {
        if(checkNetwork()) {
            presenter.updateData();
        }
    }

    @Override
    public void updateData(List<News> data) {
        adapter.addNewItems(data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void stopSwipe() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onClick(int position) {
        Intent intent = new Intent(this, OneNewsActivity.class);
        intent.putExtra("id", presenter.getNewsByPosition(position).getId());
        startActivity(intent);
        Log.i(TAG, "onClick: " + position);
    }

    @Override
    public Context getContext() {
        return this;
    }
}
