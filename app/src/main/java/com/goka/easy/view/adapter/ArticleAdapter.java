package com.goka.easy.view.adapter;

import com.goka.easy.databinding.ItemArticleListBinding;
import com.goka.easy.model.entity.Article;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * Created by katsuyagoto on 15/07/31.
 */
public class ArticleAdapter extends ArrayAdapter<Article> {

    public ArticleAdapter(Context context) {
        super(context, 0);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ItemArticleListBinding binding;
        if (convertView == null) {
            binding = ItemArticleListBinding.inflate(LayoutInflater.from(getContext()));
            convertView = binding.getRoot();
            convertView.setTag(binding);
        } else {
            binding = (ItemArticleListBinding) convertView.getTag();
        }

        Article article = getItem(position);
        binding.setArticle(article);
        return convertView;
    }

}