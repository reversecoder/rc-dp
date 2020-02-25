package com.rc.designpattern.composite;

import android.graphics.Canvas;

import com.rc.designpattern.state.ShapeState;

import java.io.Serializable;

public interface Shape extends Serializable {

    Shape getShape();

    int getShapeId();

    int getShapeX();

    int getShapeY();

    void setShapeX(int shapeX);

    void setShapeY(int shapeY);

    int getShapeWidth();

    int getShapeHeight();

    //    void drag();
//
//    void drop();
//
//    void moveTo(int x, int y);
//
//    void moveBy(int x, int y);
//
//    boolean isInsideBounds(int x, int y);
//
//    int getShapeColor();
//
//    float area();
//


//    void selectShape();
//
//    void unselectShape();

    boolean isShapeSelected();

    void setShapeState(ShapeState shapeState);

    ShapeState getShapeState();

    void setShapeColor(int color);

    void drawShape(Canvas canvas);

    void refreshView();
}