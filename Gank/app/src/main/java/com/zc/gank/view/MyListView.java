package com.zc.gank.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.zc.gank.R;

/**
 * Created by chuanzhang on 2016/11/1.
 */

public class MyListView extends ListView implements View.OnTouchListener,GestureDetector.OnGestureListener{

    private int selectedItem;
    private boolean isDeleteShown;
    private GestureDetector gestureDetector;
    private OnDeleteListener listener;
    private View deleteButton;
    private ViewGroup itemLayout;

    public MyListView(Context context) {
        super(context);
        init();
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    public void setOnDeleteListener(OnDeleteListener l) {
        listener = l;
    }

    private void init(){
        gestureDetector = new GestureDetector(getContext(), this);
        setOnTouchListener(this);
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Log.e("chuan","--------------onTouch-----------------");
        if (isDeleteShown) {
            itemLayout.removeView(deleteButton);
            deleteButton = null;
            isDeleteShown = false;
            return false;
        } else {
            return gestureDetector.onTouchEvent(motionEvent);
        }

    }


    /*
    以下是OnGestureListener实现的方法
     */

    //当按下的时候获取当前item的position
    @Override
    public boolean onDown(MotionEvent motionEvent) {
        Log.e("chuan","--------------onDown-----------------");
        if (!isDeleteShown) {
            selectedItem = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
        }
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float velocityX, float velocityY) {
        Log.e("chuan","--------------onFling-----------------");
        if (!isDeleteShown && Math.abs(velocityX) > Math.abs(velocityY)) {
            deleteButton = LayoutInflater.from(getContext()).inflate(
                    R.layout.delete_button, null);
            deleteButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemLayout.removeView(deleteButton);
                    deleteButton = null;
                    isDeleteShown = false;
                    listener.onDelete(selectedItem);
                }
            });
            itemLayout = (ViewGroup) getChildAt(selectedItem
                    - getFirstVisiblePosition());
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            params.addRule(RelativeLayout.CENTER_VERTICAL);
            itemLayout.addView(deleteButton, params);
            isDeleteShown = true;
        }
        return false;
    }

    public interface OnDeleteListener{
        void onDelete(int index);
    }
}
