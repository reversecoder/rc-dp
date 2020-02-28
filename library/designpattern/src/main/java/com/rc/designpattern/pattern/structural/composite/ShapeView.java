package com.rc.designpattern.pattern.structural.composite;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.view.View;

import androidx.annotation.NonNull;

import com.rc.designpattern.pattern.behavioural.state.ShapeState;
import com.rc.designpattern.pattern.structural.bridge.Property;
import com.rc.designpattern.util.RandomManager;

public abstract class ShapeView extends View implements Shape{

//    @Override
//    public int getShapeX() {
//        return shapeX;
//    }
//
//    @Override
//    public int getShapeY() {
//        return shapeY;
//    }
//
//    @Override
//    public void setShapeX(int shapeX) {
//        this.shapeX = shapeX;
//    }
//
//    @Override
//    public void setShapeY(int shapeY) {
//        this.shapeY = shapeY;
//    }
//
//    @Override
//    public int getShapeWidth() {
//        return 0;
//    }
//
//    @Override
//    public int getShapeHeight() {
//        return 0;
//    }

//    @Override
//    public boolean isInsideBounds(int x, int y) {
//        return x > getShapeX() && x < (getShapeX() + getShapeWidth()) &&
//                y > getShapeY() && y < (getShapeY() + getShapeHeight());
//    }

//    int shapeX, shapeY;
//    private Paint shapePaint;
//    private int color;
//    private ShapeState shapeState;
//    private int shapeId;

    private Property shapeProperty;

    public ShapeView(Context context, Property shapeProperty) {
        super(context);
        this.shapeProperty = shapeProperty;
        setId(shapeProperty.getShapeId());
    }

    @Override
    public Shape getShape() {
        return this;
    }

    @Override
    public Property getShapeProperty() {
        return shapeProperty;
    }

    @Override
    public void setShapeProperty(Property shapeProperty) {
        this.shapeProperty = shapeProperty;
    }

//    @Override
//    public int getShapeId() {
//        return this.shapeId;
//    }
//
//    @Override
//    public boolean isShapeSelected() {
//        return (shapeState == ShapeState.SELECTED);
//    }
//
//    @Override
//    public void setShapeState(ShapeState shapeState) {
//        this.shapeState = shapeState;
//    }
//
//    @Override
//    public ShapeState getShapeState() {
//        return shapeState;
//    }
//
//    @Override
//    public void setShapeColor(int color) {
//        this.color = color;
//    }

    @Override
    public void refreshView() {
        invalidate();
    }

    @Override
    public View getShapeView() {
        return this;
    }

    @Override
    public void onDraw(Canvas canvas) {
        drawShape(canvas);
    }
}