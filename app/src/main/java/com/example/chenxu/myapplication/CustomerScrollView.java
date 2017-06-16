package com.example.chenxu.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * 可以拖动的ScrollView
 */
public class CustomerScrollView extends ScrollView {

    private static final int size = 4;
    private View inner;
    private float y;
    private Rect normal = new Rect();


    public CustomerScrollView(Context context) {
        super(context);
    }

    public CustomerScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        if (getChildCount() > 0) {
            inner = getChildAt(0);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (inner == null) {
            return super.onTouchEvent(ev);
        } else {
            commOnTouchEvent(ev);
        }
        return super.onTouchEvent(ev);
    }

    public interface SetOnclick {
        void actionDown();

        void actionUp();

        void actionMove();
    }

    public static SetOnclick setOnclick;


    public static void onclick(SetOnclick setOnclick) {
        CustomerScrollView.setOnclick = setOnclick;
    }

    public void commOnTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
//        Log.e("cx", "action="+action);
        switch (action) {
            case MotionEvent.ACTION_DOWN:

                y = ev.getY();
//                Log.e("cx", "滑动结束");
                break;
            case MotionEvent.ACTION_UP:
                if (isNeedAnimation()) {
                    // Log.v("mlguitar", "will up and animation");
                    animation();
                    if (setOnclick != null) {
                        setOnclick.actionUp();
                    }
                    Log.e("cx", "向下滑动");
                }
                break;
            case MotionEvent.ACTION_MOVE:

                final float preY = y;
                float nowY = ev.getY();
                if (nowY-y<0){
                    return;
                }
                /**
                 * size=4 表示 拖动的距离为屏幕的高度的1/4
                 */
                int deltaY = (int) (preY - nowY) / size;
                // 滚动
                // scrollBy(0, deltaY);

                y = nowY;
                if (isNeedMove()) {
                    if (normal.isEmpty()) {
                        normal.set(inner.getLeft(), inner.getTop(),
                                inner.getRight(), inner.getBottom());
                        return;
                    }
                    int yy = inner.getTop() - deltaY;

                    // 移动布局
                    inner.layout(inner.getLeft(), yy, inner.getRight(),
                            inner.getBottom() - deltaY);

                }
                break;
            default:
                break;
        }
    }

    public void animation() {
        TranslateAnimation ta = new TranslateAnimation(0, 0, inner.getTop(),
                normal.top);
        ta.setDuration(200);
        inner.startAnimation(ta);
        inner.layout(normal.left, normal.top, normal.right, normal.bottom);
        normal.setEmpty();
    }

    public boolean isNeedAnimation() {
        return !normal.isEmpty();
    }

    public boolean isNeedMove() {
        int offset = inner.getMeasuredHeight() - getHeight();
        int scrollY = getScrollY();
        if (scrollY == 0 || scrollY == offset) {
            return true;
        }
        return false;
    }


    /**
     * 最小的滑动距离
     */
    private static final int SCROLLLIMIT = 100;

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (oldt > t && oldt - t > SCROLLLIMIT) {// 向下
            Log.e("cx", "向下");
        } else if (oldt < t && t - oldt > SCROLLLIMIT) {// 向上
            Log.e("cx", "向上");
            setOnclick.actionDown();
        }
    }
}
