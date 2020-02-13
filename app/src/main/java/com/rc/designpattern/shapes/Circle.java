package com.rc.designpattern.shapes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.view.View;
import android.widget.RelativeLayout;

import com.rc.designpattern.util.Util;
import com.rc.designpattern.view.DragLayout;

public class Circle extends BaseShape {

    private int radius = 0;

    public Circle(Context context, int x, int y, int radius) {
        super(context, x, y);
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

    //
//    @Override
//    public void drawShape(DragLayout frame, Context context) {
//        super.drawShape(frame, context);
//
//        CircleView aCircleView = new CircleView(context, getShapeX(), getShapeY());
//        frame.addShapeView(this, aCircleView);
//    }

    @Override
    public void drawShape(Canvas canvas) {
        canvas.drawCircle(getShapeWidth()/2, getShapeHeight()/2, radius-Util.dpToPx(5, getContext()), borderPaint);

//        int left = getShapeX() - getShapeWidth()/ 2;
//        int top = getShapeY() - getShapeHeight()/ 2;
//        int right = left + getShapeWidth();
//        int bottom = top + getShapeHeight();
//
//        canvas.drawOval(new RectF(left, top, right, bottom), borderPaint);
    }

//    private class CircleView extends View implements ShapeView {
//
//        private float mXPos, mYPos;
//
//        public CircleView(Context context, float x, float y) {
//            super(context);
//            this.mXPos = x;
//            this.mYPos = y;
//        }
//
//        @Override
//        protected synchronized void onDraw(Canvas canvas) {
//            canvas.drawCircle(mXPos, mYPos, radius / 2, borderPaint);
//        }
//    }
//
//    @Override
//    public String toString() {
//        return "Circle{" +
//                "radius=" + radius +
//                ", shapeX=" + shapeX +
//                ", shapeY=" + shapeY +
//                '}';
//    }
}
