package com.rc.designpattern.composite;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.view.View;

import com.rc.designpattern.state.ShapeState;
import com.rc.designpattern.tools.RandomManager;

public abstract class BaseShape extends View implements Shape {

    //    int shapeX, shapeY;
//    Paint borderPaint, backgroundPaint;
//    DragLayout frame;
//    Context context;
//    private int dx = 0, dy = 0;
//    private int color;
//    private boolean selected = false;
//    private int displayWidth, displayHeight;
//
//    BaseShape(int x, int y, int color) {
//        this.shapeX = x;
//        this.shapeY = y;
//        this.color = color;
//        this.borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//    }
//

    @Override
    public int getShapeX() {
        return shapeX;
    }

    @Override
    public int getShapeY() {
        return shapeY;
    }

    @Override
    public void setShapeX(int shapeX) {
        this.shapeX = shapeX;
    }

    @Override
    public void setShapeY(int shapeY) {
        this.shapeY = shapeY;
    }

    @Override
    public int getShapeWidth() {
        return 0;
    }

    @Override
    public int getShapeHeight() {
        return 0;
    }
//
//    @Override
//    public void drag() {
//        dx = shapeX;
//        dy = shapeY;
//    }
//
//    @Override
//    public void moveTo(int x, int y) {
//        this.shapeX = dx + x;
//        this.shapeY = dy + y;
//    }
//
//    @Override
//    public void moveBy(int x, int y) {
//        this.shapeX += x;
//        this.shapeY += y;
//    }
//
//    @Override
//    public void drop() {
//        this.shapeX = dx;
//        this.shapeY = dy;
//    }
//
//    @Override
//    public boolean isInsideBounds(int x, int y) {
//        return x > getShapeX() && x < (getShapeX() + getShapeWidth()) &&
//                y > getShapeY() && y < (getShapeY() + getShapeHeight());
//    }
//
//    @Override
//    public int getShapeColor() {
//        return color;
//    }
//
//    @Override
//    public void setShapeColor(int color) {
//        this.color = color;
//    }
//
//    @Override
//    public void selectShape() {
//        selected = true;
//    }
//
//    @Override
//    public void unselectShape() {
//        selected = false;
//    }
//
//    @Override
//    public boolean isShapeSelected() {
//        return selected;
//    }
//
//    void enableSelectionStyle() {
//        borderPaint.setColor(Color.RED);
//        borderPaint.setStyle(Paint.Style.STROKE);
//        borderPaint.setStrokeWidth(5f);
//        borderPaint.setPathEffect(new DashPathEffect(new float[]{5, 10}, 0));
//
////        Graphics2D g2 = (Graphics2D) graphics;
////        float dash1[] = {2.0f};
////        g2.setStroke(new BasicStroke(1.0f,
////                BasicStroke.CAP_BUTT,
////                BasicStroke.JOIN_MITER,
////                2.0f, dash1, 0.0f));
//    }
//
//    void disableSelectionStyle() {
//        borderPaint.setColor(color);
//        borderPaint.setStyle(Paint.Style.STROKE);
//        borderPaint.setStrokeWidth(5f);
////        Graphics2D g2 = (Graphics2D) graphics;
////        g2.setStroke(new BasicStroke());
//    }
//
//    @Override
//    public void drawShape(DragLayout frame, Context context) {
//        this.frame = frame;
//        this.context = context;
//        this.displayHeight = frame.getHeight();
//        this.displayWidth = frame.getWidth();
//
//        if (isShapeSelected()) {
//            enableSelectionStyle();
//        } else {
//            disableSelectionStyle();
//        }
//    }


    int shapeX, shapeY;
    Paint borderPaint;
    //    private boolean selected = false;
    private int color;
    private ShapeState shapeState;
    private int shapeId;

    public BaseShape(Context context, int x, int y) {
        super(context);
        this.shapeX = x;
        this.shapeY = y;
        this.color = Color.BLACK;
        this.shapeState = ShapeState.UNSELECTED;
        this.borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.shapeId = RandomManager.getRandom(9);
        setId(shapeId);
    }

//    public BaseShape(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    public BaseShape(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }
//
//    public BaseShape(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }

    //    @Override
//    public void selectShape() {
//        selected = true;
//    }
//
//    @Override
//    public void unselectShape() {
//        selected = false;
//    }
//
    @Override
    public boolean isShapeSelected() {
        return (shapeState == ShapeState.SELECTED);
    }

    @Override
    public void setShapeState(ShapeState shapeState) {
        this.shapeState = shapeState;
    }

    @Override
    public ShapeState getShapeState() {
        return shapeState;
    }

    @Override
    public void setShapeColor(int color) {
        this.color = color;
    }

    @Override
    public void refreshView() {
        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        if (isShapeSelected()) {
            enableSelectionStyle();
        } else {
            disableSelectionStyle();
        }

        drawShape(canvas);
    }

    void enableSelectionStyle() {
        borderPaint.setColor(Color.RED);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(5f);
        borderPaint.setPathEffect(new DashPathEffect(new float[]{5, 10}, 0));
    }

    void disableSelectionStyle() {
        borderPaint.setColor(color);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(5f);
        borderPaint.setPathEffect(null);
    }
}