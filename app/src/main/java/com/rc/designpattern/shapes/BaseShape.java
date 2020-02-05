package com.rc.designpattern.shapes;

import android.content.Context;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.widget.RelativeLayout;

public abstract class BaseShape implements Shape {

    int x, y;
    Paint paint;
    RelativeLayout frame;
    private int dx = 0, dy = 0;
    private int color;
    private boolean selected = false;
    private Context context;
    private int displayWidth, displayHeight;

    BaseShape(int x, int y, int color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    public int getShapeX() {
        return x;
    }

    @Override
    public int getShapeY() {
        return y;
    }

    @Override
    public int getShapeWidth() {
        return 0;
    }

    @Override
    public int getShapeHeight() {
        return 0;
    }

    @Override
    public void drag() {
        dx = x;
        dy = y;
    }

    @Override
    public void moveTo(int x, int y) {
        this.x = dx + x;
        this.y = dy + y;
    }

    @Override
    public void moveBy(int x, int y) {
        this.x += x;
        this.y += y;
    }

    @Override
    public void drop() {
        this.x = dx;
        this.y = dy;
    }

    @Override
    public boolean isInsideBounds(int x, int y) {
        return x > getShapeX() && x < (getShapeX() + getShapeWidth()) &&
                y > getShapeY() && y < (getShapeY() + getShapeHeight());
    }

    @Override
    public int getShapeColor() {
        return color;
    }

    @Override
    public void setShapeColor(int color) {
        this.color = color;
    }

    @Override
    public void select() {
        selected = true;
    }

    @Override
    public void unSelect() {
        selected = false;
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    void enableSelectionStyle() {
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2f);
        paint.setPathEffect(new DashPathEffect(new float[]{5, 10}, 0));

//        Graphics2D g2 = (Graphics2D) graphics;
//        float dash1[] = {2.0f};
//        g2.setStroke(new BasicStroke(1.0f,
//                BasicStroke.CAP_BUTT,
//                BasicStroke.JOIN_MITER,
//                2.0f, dash1, 0.0f));
    }

    void disableSelectionStyle() {
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
//        Graphics2D g2 = (Graphics2D) graphics;
//        g2.setStroke(new BasicStroke());
    }

    @Override
    public void drawShape(RelativeLayout mFrame, Context aContext) {
        frame = mFrame;
        context = aContext;
        displayHeight = mFrame.getHeight();
        displayWidth = mFrame.getWidth();

        if (isSelected()) {
            enableSelectionStyle();
        } else {
            disableSelectionStyle();
        }
    }
}