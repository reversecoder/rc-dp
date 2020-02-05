package com.rc.designpattern.shapes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;
import android.widget.RelativeLayout;

public class Triangle extends BaseShape {

    private int width;
    private int height;

    public Triangle(int x, int y, int width, int height, int color) {
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
    public void drawShape(RelativeLayout mFrame, Context aContext) {
        super.drawShape(mFrame, aContext);

        TriangleView triangleView = new TriangleView(aContext, getShapeX(), getShapeY());
        mFrame.addView(triangleView);
    }

    public class TriangleView extends View implements ShapeView {

        private float mXPos, mYPos;

        public TriangleView(Context context, float x, float y) {
            super(context);
            this.mXPos = x;
            this.mYPos = y;
        }

        @Override
        protected synchronized void onDraw(Canvas canvas) {
            drawTriangle(canvas, paint, getShapeX(), getShapeY(), getShapeWidth());
        }

        public void drawTriangle(Canvas canvas, Paint paint, int x, int y, int width) {
            int halfWidth = width / 2;

            Path path = new Path();
            path.moveTo(x, y - halfWidth); // Top
            path.lineTo(x - halfWidth, y + halfWidth); // Bottom left
            path.lineTo(x + halfWidth, y + halfWidth); // Bottom right
            path.lineTo(x, y - halfWidth); // Back to Top
            path.close();

            canvas.drawPath(path, paint);
        }
    }
}