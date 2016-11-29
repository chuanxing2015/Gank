package com.zc.gank.drawtest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;

import com.zc.gank.R;

/**
 * Created by chuanzhang on 2016/10/21.
 */

public class DrawTesTActivity extends Activity {

    private ImageView mIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.draw_test_activity);
        mIv = (ImageView) findViewById(R.id.iv);
        DrawOnBitmap();
    }

    private void DrawOnBitmap(){
        Bitmap bitmap = Bitmap.createBitmap(800,400, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);//创建Canvas需要传递一个bitmap对象来保存像素信息
        canvas.drawColor(Color.GREEN);
        Paint paint=new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(60);
        canvas.drawText("hello , everyone", 150, 200, paint);
        mIv.setImageBitmap(bitmap);
    }
}
