package com.rc.designpattern.shapes;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;

public class Circle extends BaseShape {

    private int radius = 0;

    public Circle(Context context, int x, int y, int radius) {
        super(context, x, y);
        this.radius = radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public int getShapeWidth() {
        return radius * 2;
    }

    @Override
    public int getShapeHeight() {
        return radius * 2;
    }

    @Override
    public void drawShape(Canvas canvas) {
//        canvas.drawCircle(getShapeWidth() / 2, getShapeHeight() / 2, radius - Util.dpToPx(5, getContext()), borderPaint);
        canvas.drawCircle(getShapeWidth() / 2, getShapeHeight() / 2, radius - 10, borderPaint);

//        int left = getShapeX() - getShapeWidth()/ 2;
//        int top = getShapeY() - getShapeHeight()/ 2;
//        int right = left + getShapeWidth();
//        int bottom = top + getShapeHeight();
//
//        canvas.drawOval(new RectF(left, top, right, bottom), borderPaint);
    }

    @Override
    public void refreshView() {
        invalidate();
    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        return false;
//    }
}
