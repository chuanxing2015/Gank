package com.zc.gank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zc.gank.adapter.MyAdapter;
import com.zc.gank.drawtest.DrawTesTActivity;
import com.zc.gank.view.BaseView;
import com.zc.gank.view.MyListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BaseView<String>,View.OnClickListener{

    private Button mBtnDraw;
    private MyListView listView;
    private MyAdapter adapter;
    private List<String> contentList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnDraw = (Button) findViewById(R.id.btn_draw_test);
        listView = (MyListView) findViewById(R.id.lv);
        initEvents();
        initList();

        listView.setOnDeleteListener(new MyListView.OnDeleteListener() {
            @Override
            public void onDelete(int index) {
                contentList.remove(index);
                adapter.notifyDataSetChanged();
            }
        });
        adapter = new MyAdapter(this, 0, contentList);
        listView.setAdapter(adapter);
    }

    protected void initEvents(){
        mBtnDraw.setOnClickListener(this);
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
                startActivity(new Intent(this, JsActivity.class));
                break;
        }
    }

    private void initList() {
        contentList.add("Content Item 1");
        contentList.add("Content Item 2");
        contentList.add("Content Item 3");
        contentList.add("Content Item 4");
        contentList.add("Content Item 5");
        contentList.add("Content Item 6");
        contentList.add("Content Item 7");
        contentList.add("Content Item 8");
        contentList.add("Content Item 9");
        contentList.add("Content Item 10");
        contentList.add("Content Item 11");
        contentList.add("Content Item 12");
        contentList.add("Content Item 13");
        contentList.add("Content Item 14");
        contentList.add("Content Item 15");
        contentList.add("Content Item 16");
        contentList.add("Content Item 17");
        contentList.add("Content Item 18");
        contentList.add("Content Item 19");
        contentList.add("Content Item 20");
    }
}
