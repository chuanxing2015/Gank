package com.zc.gank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zc.gank.adapter.MyAdapter;
import com.zc.gank.drawtest.DrawTesTActivity;
import com.zc.gank.js.JsActivity;
import com.zc.gank.view.BaseView;
import com.zc.gank.view.MyListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BaseView<String>,View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_draw_test).setOnClickListener(this);
        findViewById(R.id.btn_js).setOnClickListener(this);
    }

    @Override
    public void showDate(String a) {

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_draw_test:
                startActivity(new Intent(this, DrawTesTActivity.class));
                break;
            case R.id.btn_js:
                startActivity(new Intent(this, JsActivity.class));
                break;
        }
    }
}
