package com.goka.easy.view.activity;

import com.goka.easy.R;
import com.goka.easy.presenter.MainPresenter;

public class MainActivity extends BaseActivity {

    @Override
    protected Class componentClass() {
        return MainPresenter.Component.class;
    }

    @Override
    protected int layoutResID() {
        return R.layout.activity_main;
    }


}
