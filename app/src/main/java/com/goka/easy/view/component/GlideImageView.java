package com.goka.easy.view.component;

import com.bumptech.glide.Glide;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by katsuyagoto on 15/08/19.
 */
public class GlideImageView extends ImageView {

    public GlideImageView(Context context) {
        super(context);
    }

    public GlideImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GlideImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void loadUri(Uri uri) {
        Glide.with(getContext())
                .load(uri)
                .centerCrop()
                .crossFade()
                .into(this);
    }

    @BindingAdapter("uri")
    public static void loadUri(GlideImageView view, Uri uri) {
        view.loadUri(uri);
    }

}
