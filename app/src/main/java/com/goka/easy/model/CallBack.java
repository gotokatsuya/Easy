package com.goka.easy.model;

/**
 * Created by katsuyagoto on 15/08/10.
 */
public class CallBack {

    public interface Object<T> {

        void call(T obj);
    }

    public interface List<T> {

        void call(java.util.List<T> list);
    }

}
