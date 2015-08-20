package com.goka.easy.view.popup;

import com.goka.easy.R;
import com.goka.easy.model.entity.Article;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import de.greenrobot.event.EventBus;

/**
 * Created by katsuyagoto on 15/08/12.
 */
public class ArticlePopup extends BasePopup {

    public Article mArticle;

    public ArticlePopup(Context context, Article article) {
        super(context);
        this.mArticle = article;
    }

    @Override
    protected View getView() {
        final View view = LayoutInflater.from(getContext()).inflate(R.layout.popup_article, null);
        Button back = (Button) view.findViewById(R.id.popup_back_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(v);
                dismiss();
            }
        });
        return view;
    }

    @Override
    protected AlertDialog getDialog() {
        return new AlertDialog.Builder(getContext())
                .setView(getView())
                .setCancelable(true)
                .create();
    }

}