package com.rc.designpattern.shapes;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.widget.RelativeLayout;

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
    public void drawShape(RelativeLayout frame, Context context) {
        super.drawShape(frame, context);

        RectangleView rectangleView = new RectangleView(context, getShapeX(), getShapeY());
        frame.addView(rectangleView);
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
            canvas.drawRect(mXPos - width, mYPos - width / 2, mXPos + width, mYPos + width / 2, borderPaint);
        }
    }
}
