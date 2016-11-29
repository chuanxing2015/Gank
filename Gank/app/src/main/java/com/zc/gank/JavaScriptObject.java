package com.zc.gank;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import java.io.Serializable;

/**
 * Created by chuanzhang on 2016/11/29.
 */

public class JavaScriptObject implements Serializable {
    Context mContxt;

    //sdk17版本以上加上注解
    public JavaScriptObject(Context mContxt) {
        this.mContxt = mContxt;
    }

    @JavascriptInterface
    public void fun1FromAndroid(String name) {
        Toast.makeText(mContxt, name, Toast.LENGTH_LONG).show();
    }

    @JavascriptInterface
    public void fun2(String name) {
        Toast.makeText(mContxt, "调用fun2:" + name, Toast.LENGTH_SHORT).show();
    }
}
