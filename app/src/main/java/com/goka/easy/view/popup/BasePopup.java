package com.goka.easy.view.popup;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;

/**
 * Created by katsuyagoto on 15/08/12.
 */
public abstract class BasePopup {

    private Context mContext;

    protected AlertDialog mDialog;

    public BasePopup(Context context) {
        mContext = context;
    }

    protected abstract View getView();

    protected abstract AlertDialog getDialog();

    public boolean isShowing() {
        return mDialog != null;
    }

    public void show() {
        if (mDialog != null) {
            return;
        }

        mDialog = getDialog();

        mDialog.show();
    }

    public void dismiss() {
        if (mDialog == null) {
            return;
        }

        mDialog.dismiss();
        mDialog = null;
    }

    public Context getContext() {
        return mContext;
    }
}
