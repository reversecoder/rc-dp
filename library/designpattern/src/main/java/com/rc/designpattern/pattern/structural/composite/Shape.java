package com.rc.designpattern.pattern.structural.composite;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.rc.designpattern.pattern.behavioural.state.ShapeType;
import com.rc.designpattern.pattern.structural.bridge.Property;

import java.io.Serializable;

public interface Shape extends Serializable {

    ShapeType getShapeType();

    Property getShapeProperty();

    void setShapeProperty(Property shapeProperty);

    Shape getShape();

    View getShapeView();

    void drawShape(Canvas canvas);

    void refreshView();

//    int getShapeId();
//
//    int getShapeX();
//
//    int getShapeY();
//
//    void setShapeX(int shapeX);
//
//    void setShapeY(int shapeY);
//
//    int getShapeWidth();
//
//    int getShapeHeight();
//
//    boolean isShapeSelected();
//
//    void setShapeState(ShapeState shapeState);
//
//    ShapeState getShapeState();
//
//    void setShapeColor(int color);

}