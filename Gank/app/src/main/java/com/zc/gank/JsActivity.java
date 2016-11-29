package com.zc.gank;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by chuanzhang on 2016/11/29.
 */

public class JsActivity extends Activity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_js);
//        Button mBtn1 = (Button) findViewById(R.id.btn_js);
//        mWebView = (WebView) findViewById(R.id.webview);
//
//        //设置编码
//        mWebView.getSettings().setDefaultTextEncodingName("utf-8");
//        //支持js
//        mWebView.getSettings().setJavaScriptEnabled(true);
//        //设置背景颜色 透明
//        mWebView.setBackgroundColor(Color.argb(0, 0, 0, 0));
//        //设置本地调用对象及其接口
//        mWebView.addJavascriptInterface(new JavaScriptObject(this), "myObj");
//        //载入js
//        mWebView.loadUrl("file:///android_asset/cc.html");
//
//        //点击调用js中方法
//        mBtn1.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                mWebView.loadUrl("javascript:funFromjs()");
//               // Toast.makeText(JsActivity.this, "调用javascript:funFromjs()", Toast.LENGTH_LONG).show();
//            }
//        });
        showWebView();
    }

    private void showWebView(){     // webView与js交互代码
        try {
            mWebView = new WebView(this);
            setContentView(mWebView);

            mWebView.requestFocus();

            mWebView.setWebChromeClient(new WebChromeClient(){
                @Override
                public void onProgressChanged(WebView view, int progress){
                    JsActivity.this.setTitle("Loading...");
                    JsActivity.this.setProgress(progress);

                    if(progress >= 80) {
                        JsActivity.this.setTitle("JsAndroid Test");
                    }
                }
            });

            mWebView.setOnKeyListener(new View.OnKeyListener() {        // webview can go back
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if(keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
                        mWebView.goBack();
                        return true;
                    }
                    return false;
                }
            });

            WebSettings webSettings = mWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setDefaultTextEncodingName("utf-8");

            mWebView.addJavascriptInterface(getHtmlObject(), "jsObj");
            mWebView.loadUrl("file:///android_asset/cc.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Object getHtmlObject(){
        Object insertObj = new Object(){
            public String HtmlcallJava(){
                return "Html call Java";
            }

            public String HtmlcallJava2(final String param){
                return "Html call Java : " + param;
            }

            public void JavacallHtml(){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mWebView.loadUrl("javascript: showFromHtml()");
                        Toast.makeText(JsActivity.this, "clickBtn", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            public void JavacallHtml2(){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mWebView.loadUrl("javascript: showFromHtml2('IT-homer blog')");
                        Toast.makeText(JsActivity.this, "clickBtn2", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };

        return insertObj;
    }
}
