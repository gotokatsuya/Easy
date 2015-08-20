package com.goka.easy.view;

import android.app.Activity;

/**
 * Created by katsuyagoto on 15/08/19.
 */
public interface ViewImpl<T extends Activity> {

    T getActivity();
}
