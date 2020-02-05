package com.rc.designpattern.shapes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by enrique on 04/08/14.
 */
public class Circle extends BaseShape {

    private int radius;

    public Circle(int x, int y, int radius, int color) {
        super(x, y, color);
        this.radius = radius;
    }

    @Override
    public int getWidth() {
        return radius * 2;
    }

    @Override
    public int getHeight() {
        return radius * 2;
    }

    @Override
    public void draw(RelativeLayout mFrame, Context aContext) {
       super.draw(mFrame, aContext);

        CircleView aCircleView = new CircleView(aContext, getX(), getY());
        mFrame.addView(aCircleView);
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
            canvas.drawCircle(mXPos, mYPos, radius / 2, paint);
        }
    }


//    @Override
//    public void draw(RelativeLayout mFrame, final float x, final float y, Context aContext) {
//        this.mFrame = mFrame;
//        mDisplayHeight = mFrame.getHeight();
//        mDisplayWidth = mFrame.getWidth();
//        CircleView aCircleView = new CircleView(aContext, x, y);
//        mFrame.addView(aCircleView);
//    }
//
//    @Override
//    public void setColor(int aColor) {
//        this.myColor.setColor(aColor);
//    }
//
//    @Override
//    public void setWidth(int width) {
//        this.width = (float) width;
//    }
//
//
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
//            canvas.drawCircle(mXPos, mYPos, width / 2, myColor);
//        }
//    }
}
