package com.rc.designpattern.shapes;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.widget.RelativeLayout;

import com.rc.designpattern.view.DragLayout;

public class Rectangle extends BaseShape {

    private int width;
    private int height;

    public Rectangle(int x, int y, int width, int height, int color) {
        super(x, y, color);
        this.width = width;
        this.height = height;
    }

    @Override
    public int getShapeWidth() {
        return width;
    }

    @Override
    public int getShapeHeight() {
        return height;
    }

    @Override
    public void drawShape(DragLayout frame, Context context) {
        super.drawShape(frame, context);

        RectangleView rectangleView = new RectangleView(context, getShapeX(), getShapeY());
        frame.addShapeView(this, rectangleView);
    }

    public class RectangleView extends View implements ShapeView {

        private float mXPos, mYPos;

        public RectangleView(Context context, float x, float y) {
            super(context);

            this.mXPos = x;
            this.mYPos = y;
        }

        @Override
        protected synchronized void onDraw(Canvas canvas) {
            canvas.drawRect(mXPos - width/2, mYPos - height / 2, mXPos + width, mYPos + height / 2, borderPaint);
        }
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", height=" + height +
                ", shapeX=" + shapeX +
                ", shapeY=" + shapeY +
                '}';
    }
}
