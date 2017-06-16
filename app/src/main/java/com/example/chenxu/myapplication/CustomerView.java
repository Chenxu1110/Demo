package com.example.chenxu.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 95394 on 2017/6/15.
 */

public class CustomerView extends View{
    public CustomerView(Context context) {
        super(context);
    }

    public CustomerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
