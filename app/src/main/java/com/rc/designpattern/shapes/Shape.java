package com.rc.designpattern.shapes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.RelativeLayout;

import com.rc.designpattern.view.DragLayout;

import java.io.Serializable;

public interface Shape extends Serializable {

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
    void selectShape();

    void unselectShape();

    boolean isShapeSelected();

    void setShapeColor(int color);

    void drawShape(Canvas canvas);
}