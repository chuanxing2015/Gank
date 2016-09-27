package com.zc.gank.presenter;

/**
 * Created by chuanzhang on 2016/9/27.
 */

public abstract class BasePresenter<T> {
    abstract void attachView(T view);
    abstract void detachView();
}
