package com.rc.designpattern.shapes;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.widget.RelativeLayout;

import com.rc.designpattern.view.DragLayout;

public class Circle extends BaseShape {

    private int radius;

    public Circle(int x, int y, int radius, int color) {
        super(x, y, color);
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
    public void drawShape(DragLayout frame, Context context) {
        super.drawShape(frame, context);

        CircleView aCircleView = new CircleView(context, getShapeX(), getShapeY());
        frame.addShapeView(this, aCircleView);
    }

    private class CircleView extends View implements ShapeView {

        private float mXPos, mYPos;

        public CircleView(Context context, float x, float y) {
            super(context);
            this.mXPos = x;
            this.mYPos = y;
        }

        @Override
        protected synchronized void onDraw(Canvas canvas) {
            canvas.drawCircle(mXPos, mYPos, radius / 2, borderPaint);
        }
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                ", shapeX=" + shapeX +
                ", shapeY=" + shapeY +
                '}';
    }
}
