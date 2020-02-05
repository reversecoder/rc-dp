package com.rc.designpattern.shapes;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.widget.RelativeLayout;

public class Dot extends BaseShape {

    private final int DOT_SIZE = 10;

    public Dot(int x, int y, int color) {
        super(x, y, color);
    }

    @Override
    public int getShapeWidth() {
        return DOT_SIZE;
    }

    @Override
    public int getShapeHeight() {
        return DOT_SIZE;
    }

    @Override
    public void drawShape(RelativeLayout frame, Context context) {
        super.drawShape(frame, context);

        DotView dotView = new DotView(context, getShapeX(), getShapeY());
        frame.addView(dotView);
    }

    public class DotView extends View implements ShapeView {

        private float mXPos, mYPos;

        public DotView(Context context, float x, float y) {
            super(context);

            this.mXPos = x;
            this.mYPos = y;
        }

        @Override
        protected synchronized void onDraw(Canvas canvas) {
            canvas.drawRect(mXPos - getShapeWidth()/2, mYPos - getShapeHeight() / 2, mXPos + getShapeWidth(), mYPos + getShapeHeight() / 2, borderPaint);
        }
    }
}
