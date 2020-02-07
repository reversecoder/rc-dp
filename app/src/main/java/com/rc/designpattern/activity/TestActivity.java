package com.rc.designpattern.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.rc.designpattern.R;

public class TestActivity extends AppCompatActivity implements View.OnTouchListener {

    View view_r, view_g, view_b, view_o;
    int xDelta, yDelta;
    RelativeLayout rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        rootLayout = (RelativeLayout) findViewById(R.id.rlroot);

//        view_r = findViewById(R.id.v_red);
//        view_b = findViewById(R.id.v_blue);
//        view_g = findViewById(R.id.v_green);
//        view_o = findViewById(R.id.v_o);

        View view_r = new View(TestActivity.this);
        view_r.setBackgroundColor(ContextCompat.getColor(TestActivity.this, android.R.color.holo_red_dark));
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(100, 100);
//        lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
//        lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        view_r.setLayoutParams(lp);

        view_r.setOnTouchListener(this);
//        view_b.setOnTouchListener(this);
//        view_g.setOnTouchListener(this);
//        view_o.setOnTouchListener(this);

        rootLayout.addView(view_r);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        final int X = (int) event.getRawX();
        final int Y = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
                if (lParams.getRule(RelativeLayout.ALIGN_PARENT_BOTTOM) == RelativeLayout.TRUE) {
                    lParams.topMargin = v.getTop();
                    lParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 0);
                    Log.d("MainActivity", "added Rule bottom");
                }
                if (lParams.getRule(RelativeLayout.ALIGN_PARENT_TOP) == RelativeLayout.TRUE) {
                    lParams.bottomMargin = v.getBottom();
                    lParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
                    Log.d("MainActivity", "added Rule top");
                }
                if (lParams.getRule(RelativeLayout.ALIGN_PARENT_LEFT) == RelativeLayout.TRUE) {
                    lParams.rightMargin = v.getRight();
                    lParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 0);
                    Log.d("MainActivity", "added Rule left");
                }
                if (lParams.getRule(RelativeLayout.ALIGN_PARENT_RIGHT) == RelativeLayout.TRUE) {
                    lParams.leftMargin = v.getLeft();//rootLayout.getMeasuredWidth()-v.getWidth();
                    lParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0);
                    Log.d("MainActivity", "added Rule right");
                }

                Log.d("MainActivity", "leftPos:" + v.getLeft() + "topPos:" + v.getTop());

                xDelta = X - lParams.leftMargin;
                yDelta = Y - lParams.topMargin;

                Log.d("MainActivity", "Action_Down:X=" + X + ",Y=" + Y + ",xD=" + xDelta + ",yD=" + yDelta + ",lm=" + lParams.leftMargin + ",tm=" + lParams.topMargin);
                break;
            case MotionEvent.ACTION_UP:
                Log.d("MainActivity", "Action_up");
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                Log.d("MainActivity", "Action_Pointer_Down");
                break;
            case MotionEvent.ACTION_POINTER_UP:
                Log.d("MainActivity", "Action_Pointer_Up");
                break;
            case MotionEvent.ACTION_MOVE:
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
                layoutParams.leftMargin = X - xDelta;
                layoutParams.topMargin = Y - yDelta;
                layoutParams.rightMargin = 0;
                layoutParams.bottomMargin = 0;

                v.setLayoutParams(layoutParams);
                //v.animate().x(X-xDelta).y(Y-yDelta).setDuration(0).start();
                Log.d("MainActivity", "Action_Move:X=" + X + ",Y=" + Y + ",xD=" + xDelta + ",yD=" + yDelta);
                break;
        }
        rootLayout.invalidate();
        return true;
    }
}
