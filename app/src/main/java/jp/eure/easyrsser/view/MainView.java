package jp.eure.easyrsser.view;

import com.jakewharton.scalpel.ScalpelFrameLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Toast;

import javax.inject.Inject;

import jp.eure.easyrsser.BuildConfig;
import jp.eure.easyrsser.DaggerService;
import jp.eure.easyrsser.presenter.MainPresenter;

/**
 * Created by katsuyagoto on 15/08/08.
 */
public class MainView extends ScalpelFrameLayout {

    @Inject
    MainPresenter.Presenter presenter;

    public MainView(Context context, AttributeSet attrs) {
        super(context, attrs);
        DaggerService.<MainPresenter.Component>getDaggerComponent(context).inject(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setupScalpel(BuildConfig.DEBUG);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        presenter.takeView(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        presenter.dropView(this);
        super.onDetachedFromWindow();
    }

    public void show(int serial) {
        Toast.makeText(getContext(), String.valueOf(serial), Toast.LENGTH_SHORT).show();
    }

    public void setupScalpel(boolean enabled) {
        setLayerInteractionEnabled(enabled);
        setDrawViews(enabled);
        setDrawIds(enabled);
    }

}