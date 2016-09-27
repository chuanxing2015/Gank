package com.zc.gank.view;

/**
 * Created by chuanzhang on 2016/9/27.
 *  抽象出出力业务逻辑需要执行的一些方法
 */

public interface BaseView<T> {

    void showDate(T a);//加载数据

    void showDialog();

    void hideDialog();
}
