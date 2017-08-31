package ru.tander.parsejson.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.tander.parsejson.BaseActivity;
import ru.tander.parsejson.R;
import ru.tander.parsejson.activities.onenews.presenter.OneNewsPresenter;
import ru.tander.parsejson.activities.onenews.presenter.OneNewsPresenterImpl;
import ru.tander.parsejson.activities.onenews.view.OneNewsView;

import static ru.tander.parsejson.storage.Const.ID;

/**
 * Created by savchenko on 31.08.17.
 */

public class OneNewsActivity extends BaseActivity implements OneNewsView {
    public static final String TAG = "OneNewsActivity";
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvRubric)
    TextView tvRubric;
    @BindView(R.id.tvType)
    TextView tvType;
    @BindView(R.id.ivPic)
    ImageView ivPic;
    @BindView(R.id.ivVideo)
    ImageView ivVideo;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_news);
        OneNewsPresenter presenter = new OneNewsPresenterImpl(this);
        ButterKnife.bind(this);
        int id = getIntent().getIntExtra(ID, 0);
        presenter.setId(id);
        initToolbar(getString(R.string.one_news));
    }

    @Override
    public void setTitleText(String text) {
        tvTitle.setText(text);
    }

    @Override
    public void setRubricText(String text) {
        tvRubric.setText(text);
    }

    @Override
    public void setTypeText(String text) {
        tvType.setText(text);
    }

    @Override
    public void setPictureImage(String imageText) {
        Picasso.with(this).load(imageText).into(ivPic);
    }

    @Override
    public void setVideoImage(String videoImage) {
        Picasso.with(this).load(videoImage).into(ivVideo);
    }
}
