package com.goka.easy.view;

import com.goka.easy.DaggerService;
import com.goka.easy.R;
import com.goka.easy.model.entity.Article;
import com.goka.easy.presenter.MainPresenter;
import com.goka.easy.view.activity.MainActivity;
import com.goka.easy.view.adapter.ArticleAdapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by katsuyagoto on 15/08/08.
 */
public class MainView extends FrameLayout implements ViewImpl<MainActivity> {

    @Inject
    public MainPresenter.Presenter mPresenter;

    private ListView mArticleListView;

    private ArticleAdapter mArticleAdapter;

    public MainView(Context context, AttributeSet attrs) {
        super(context, attrs);
        MainPresenter.Component component = DaggerService.getDaggerComponent(context);
        component.inject(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setUpRssListView();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mPresenter.takeView(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        mPresenter.dropView(this);
        super.onDetachedFromWindow();
    }

    public void setUpRssListView() {
        mArticleListView = (ListView) findViewById(R.id.rss_list_view);
        mArticleAdapter = new ArticleAdapter(getContext());
        mArticleListView.setAdapter(mArticleAdapter);
        mArticleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mPresenter.onClickArticle(mArticleAdapter.getItem(position));
            }
        });
    }

    public void setArticles(List<Article> articles) {
        mArticleAdapter.addAll(articles);
    }

    public void clearArticles() {
        mArticleAdapter.clear();
    }

    @Override
    public MainActivity getActivity() {
        return (MainActivity) getContext();
    }

}