package com.goka.easy.model.net;

import com.squareup.okhttp.OkHttpClient;

import android.content.Context;

import java.util.concurrent.TimeUnit;

/**
 * Created by katsuyagoto on 15/08/18.
 */
public class BaseClient {

    protected static OkHttpClient sOkHttpClient;

    private static final long TIMEOUT = 60;

    private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;

    protected String mEndpoint;

    protected Context mContext;

    public BaseClient(Context context, String endpoint) {
        this.mContext = context;
        this.mEndpoint = endpoint;

        setupOkHttpClient();
    }

    protected OkHttpClient setupOkHttpClient(Long timeout, TimeUnit timeUnit) {
        sOkHttpClient = new OkHttpClient();
        sOkHttpClient.setReadTimeout(timeout, timeUnit);
        sOkHttpClient.setConnectTimeout(timeout, timeUnit);
        return sOkHttpClient;
    }

    private OkHttpClient setupOkHttpClient() {
        return this.setupOkHttpClient(TIMEOUT, TIME_UNIT);
    }

}
