package com.goka.easy.model.net;

import android.content.Context;

import java.io.File;

/**
 * Created by katsuyagoto on 15/08/18.
 */

@SuppressWarnings("unused")
public class ResponseCache {

    private File mFile;

    private int mMaxCacheSize;

    private int mMaxStale;

    public File getFile() {
        return mFile;
    }

    public int getMaxCacheSize() {
        return mMaxCacheSize;
    }

    public int getMaxStale() {
        return mMaxStale;
    }

    private ResponseCache(Builder builder) {
        this.mFile = builder.mFile;
        this.mMaxCacheSize = builder.mMaxCacheSize;
        this.mMaxStale = builder.mMaxStale;
    }

    public static class Builder {

        private static final String DEFAULT_CACHE_FILE_NAME = "cached_easy_response";

        private static final int DEFAULT_MAX_CACHE_SIZE = 1 * 1024 * 1024; // 1MB

        private static final int DEFAULT_MAX_STALE = 2 * 24 * 60 * 60;     // 2Day

        private File mFile;

        private int mMaxCacheSize = DEFAULT_MAX_CACHE_SIZE;

        private int mMaxStale = DEFAULT_MAX_STALE;

        public Builder file(Context context) {
            return file(context, DEFAULT_CACHE_FILE_NAME);
        }

        public Builder file(Context context, String fileName) {
            return file(new File(context.getCacheDir(), fileName));
        }

        public Builder file(File file) {
            this.mFile = file;
            return this;
        }

        public Builder maxCacheSize(int maxCacheSize) {
            this.mMaxCacheSize = maxCacheSize;
            return this;
        }

        public Builder maxStale(int maxStale) {
            this.mMaxStale = maxStale;
            return this;
        }

        public ResponseCache build() {
            return new ResponseCache(this);
        }
    }
}
