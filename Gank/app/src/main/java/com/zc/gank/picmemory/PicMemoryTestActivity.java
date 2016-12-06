package com.zc.gank.picmemory;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.zc.gank.R;

/**
 * Created by chuanzhang on 2016/12/6.
 */



public class PicMemoryTestActivity extends Activity{

    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;
    private boolean isLocal = true;

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_memory);
        float density = getResources().getDisplayMetrics().density;
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        int screenHeight = getResources().getDisplayMetrics().heightPixels;
        int deviceDpi = getResources().getDisplayMetrics().densityDpi;
        Log.e("PicMemoryTestActivity","density : "+density+ "  screenWidth : "+ screenWidth+"  screenHeight : "+ screenHeight+ " deviceDpi : "+ deviceDpi);

        iv1 = (ImageView) findViewById(R.id.iv1);
        iv2 = (ImageView) findViewById(R.id.iv2);
        iv3 = (ImageView) findViewById(R.id.iv3);


        Bitmap factory = BitmapFactory.decodeResource(getResources(),R.mipmap.cc);
        Log.e("PicMemoryTestActivity","ByteCount : "+factory.getByteCount());

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap factory1 = BitmapFactory.decodeResource(getResources(),R.mipmap.cc,options);
        Log.e("PicMemoryTestActivity","width : "+options.outWidth + " height : "+options.outHeight);
       // options.inSampleSize = options.outWidth/dp2px(100);
        options.inJustDecodeBounds = false;

//        options.outWidth = 512;  不能通过Options来改变获取图片的宽高
//        options.outHeight = 341;

        factory1 = BitmapFactory.decodeResource(getResources(),R.mipmap.cc,options);

        BitmapFactory.Options options1 = new BitmapFactory.Options();
        options1.inJustDecodeBounds = true;
        Bitmap factory2 = BitmapFactory.decodeResource(getResources(),R.mipmap.cc,options1);
        Log.e("PicMemoryTestActivity","width : "+options1.outWidth + " height : "+options1.outHeight);
      // options1.inSampleSize = options1.outWidth/dp2px(50);
        options1.inJustDecodeBounds = false;
        factory2 = BitmapFactory.decodeResource(getResources(),R.mipmap.cc,options1);


        if(isLocal){

            iv1.setImageBitmap(factory);
            iv2.setImageBitmap(resizeImage(factory1,512,341));
            iv3.setImageBitmap(resizeImage(factory2,256,171));

        }else{
            Picasso.with(this).load("http://pic41.nipic.com/20140524/18849442_210536184142_2.jpg")
                    .into(iv1);

            Picasso.with(this).load("http://pic41.nipic.com/20140524/18849442_210536184142_2.jpg")
                    .fit().into(iv2);

            Picasso.with(this).load("http://pic41.nipic.com/20140524/18849442_210536184142_2.jpg")
                    .fit().into(iv3);
        }

        iv1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR1)
            @Override
            public void onClick(View v) {
                Bitmap bm =((BitmapDrawable)iv1.getDrawable()).getBitmap();
                Log.e("PicMemoryTestActivity","ByteCount : "+bm.getByteCount());
                Log.e("PicMemoryTestActivity","width : "+bm.getWidth() + " height : "+bm.getHeight());
            }
        });
        iv2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR1)
            @Override
            public void onClick(View v) {
//                Picasso.with(getApplicationContext()).load("http://pic41.nipic.com/20140524/18849442_210536184142_2.jpg")
//                        .into(iv1);
                Bitmap bm =((BitmapDrawable)iv2.getDrawable()).getBitmap();
                Log.e("PicMemoryTestActivity","ByteCount : "+bm.getByteCount());
                Log.e("PicMemoryTestActivity","width : "+bm.getWidth() + " height : "+bm.getHeight());
            }
        });
        iv3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB_MR1)
            @Override
            public void onClick(View v) {
                Bitmap bm =((BitmapDrawable)iv3.getDrawable()).getBitmap();
                Log.e("PicMemoryTestActivity","ByteCount : "+bm.getByteCount());
                Log.e("PicMemoryTestActivity","width : "+bm.getWidth() + " height : "+bm.getHeight());
            }
        });
    }

    public  int dp2px(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, getResources().getDisplayMetrics());
    }

    //使用Bitmap加Matrix来缩放
    public static Bitmap resizeImage(Bitmap bitmap, int w, int h)
    {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int newWidth = w;
        int newHeight = h;

        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width,
                height, matrix, true);

        if(resizedBitmap == null){
            return bitmap;
        }else{
            bitmap.recycle();
            bitmap = null;
            return resizedBitmap;
        }
    }

    /**
     * 笔记：
     * 1、放在内存中dpi文件夹下边图片内存计算，以图片以格式ARGB_8888存储时的计算方式
     * 计算公式： 占用内存= 图片长* 图片宽 * 4字节
     * 这里图片的原始长度在 不同设备 和放在  不同dpi文件夹 所体现的长度是不同的。与设备dpi = getResources().getDisplayMetrics().densityDpi；
     *                                                                       文件夹dpi :MDPI=160,HDPI=240,XHDPI=320,XXHDPI=480,XXXHDPI=640;
     * 图片长 = 图片原始长 (设备DPI/文件夹DPI)
     * 图片宽 = 图片原始宽(设备DPI/文件夹DPI)
     *
     * 2、网络图片没有文件夹DPI,图片是怎么样计算内存的呢？
     * 网络图片文件夹DPI会放在和设备DPI相等，所以网络请求的图片大小只和图片的分辨率相关，公式：占用内存= 图片长（原始长）* 图片宽（原始宽） * 4字节
     *
     * 3、不能通过Options来改变图片的宽高，只能通过options.inSampleSize计算缩放比
     *
     * 4、bitmapBitmap加Matrix来缩放,可以缩放到指定宽高，相当于重新创建了一个Bitmap,注意回收之前的BitMap，避免内存溢出
     */
}
