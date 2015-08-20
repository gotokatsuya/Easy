package com.goka.easy.view.activity;

import com.goka.easy.Constant;
import com.goka.easy.R;
import com.goka.easy.presenter.ArticlePresenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;

public class ArticleActivity extends BaseActivity {

    @Override
    protected Class componentClass() {
        return ArticlePresenter.Component.class;
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_article;
    }


    public static void launch(Activity activity, Parcelable article) {
        Intent intent = new Intent(activity, ArticleActivity.class);
        intent.putExtra(Constant.Extra.ARTICLE, article);
        activity.startActivity(intent);
    }

}
