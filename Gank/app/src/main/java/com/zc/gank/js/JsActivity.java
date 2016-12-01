package com.zc.gank.js;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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

            mWebView.setWebViewClient(new WebViewClient(){
                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
//                    view.loadUrl("javascript:window.jsObj.setShareContent(document.getElementById('app_title').innerHTML,"
//                            + "document.getElementById('app_desc').innerHTML,"
//                            + "document.getElementById('app_link').innerHTML,"
//                            + "document.getElementById('app_img_url').src,"
//                            + "document.getElementById('app_big_img_url').src)");
                    //自己定义方法获取html中标签中的信息
                    view.loadUrl("javascript:window.jsObj.getTitle(document.getElementById('app_title').innerHTML)");
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
            @JavascriptInterface//注意在版本Android4.2以上，需要在被调用的函数前加上@JavascriptInterface注解。
            public String HtmlcallJava(){
                return "Html call Java";
            }

            @JavascriptInterface
            public String HtmlcallJava2(final String param){
                return "Html call Java : " + param;
            }

            @JavascriptInterface
            public void JavacallHtml(){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mWebView.loadUrl("javascript: showFromHtml()");
                        Toast.makeText(JsActivity.this, "clickBtn", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @JavascriptInterface
            public void JavacallHtml2(){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mWebView.loadUrl("javascript: showFromHtml2('IT-homer blog')");
                        Toast.makeText(JsActivity.this, "clickBtn2", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @JavascriptInterface
            public void getTitle(String title){
                Log.e("chuan","title : "+ title);
                Toast.makeText(JsActivity.this, title, Toast.LENGTH_SHORT).show();
            }
        };

        return insertObj;
    }
}
