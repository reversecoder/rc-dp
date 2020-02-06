package com.rc.designpattern.shapes;

import android.content.Context;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;

import com.rc.designpattern.view.DragLayout;

public abstract class BaseShape implements Shape {

    int shapeX, shapeY;
    Paint borderPaint, backgroundPaint;
    DragLayout frame;
    private int dx = 0, dy = 0;
    private int color;
    private boolean selected = false;
    private Context context;
    private int displayWidth, displayHeight;

    BaseShape(int x, int y, int color) {
        this.shapeX = x;
        this.shapeY = y;
        this.color = color;
        this.borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    public int getShapeX() {
        return shapeX;
    }

    @Override
    public int getShapeY() {
        return shapeY;
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
        dx = shapeX;
        dy = shapeY;
    }

    @Override
    public void moveTo(int x, int y) {
        this.shapeX = dx + x;
        this.shapeY = dy + y;
    }

    @Override
    public void moveBy(int x, int y) {
        this.shapeX += x;
        this.shapeY += y;
    }

    @Override
    public void drop() {
        this.shapeX = dx;
        this.shapeY = dy;
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
        borderPaint.setColor(Color.RED);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(5f);
        borderPaint.setPathEffect(new DashPathEffect(new float[]{5, 10}, 0));

//        Graphics2D g2 = (Graphics2D) graphics;
//        float dash1[] = {2.0f};
//        g2.setStroke(new BasicStroke(1.0f,
//                BasicStroke.CAP_BUTT,
//                BasicStroke.JOIN_MITER,
//                2.0f, dash1, 0.0f));
    }

    void disableSelectionStyle() {
        borderPaint.setColor(color);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(5f);
//        Graphics2D g2 = (Graphics2D) graphics;
//        g2.setStroke(new BasicStroke());
    }

    @Override
    public void drawShape(DragLayout frame, Context context) {
        this.frame = frame;
        this.context = context;
        this.displayHeight = frame.getHeight();
        this.displayWidth = frame.getWidth();

        if (isSelected()) {
            enableSelectionStyle();
        } else {
            disableSelectionStyle();
        }
    }
}