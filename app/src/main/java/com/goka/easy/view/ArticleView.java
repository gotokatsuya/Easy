package com.goka.easy.view;

import com.goka.easy.BuildConfig;
import com.goka.easy.DaggerService;
import com.goka.easy.presenter.ArticlePresenter;
import com.goka.easy.view.activity.ArticleActivity;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import javax.inject.Inject;

/**
 * Created by katsuyagoto on 15/08/14.
 */
public class ArticleView extends WebView implements ViewImpl<ArticleActivity> {

    @Inject
    ArticlePresenter.Presenter mPresenter;

    public ArticleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ArticlePresenter.Component component = DaggerService.getDaggerComponent(context);
        component.inject(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        setUpWebViewDefaults();
        setUseTextAutoSize();
    }

    private void setUpWebViewDefaults() {
        WebSettings settings = this.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(BuildConfig.DEBUG);
        }

        setWebViewClient(new WebViewClient());

        setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    WebView webView = (WebView) v;
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_BACK:
                            if (webView.canGoBack()) {
                                webView.goBack();
                                return true;
                            }
                            break;
                    }
                }
                return false;
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void setUseTextAutoSize() {
        WebSettings settings = this.getSettings();
        WebSettings.LayoutAlgorithm layoutAlgorithm = WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING;
        settings.setLayoutAlgorithm(layoutAlgorithm);
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

    @Override
    public ArticleActivity getActivity() {
        return (ArticleActivity) getContext();
    }

}
