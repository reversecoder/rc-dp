package com.rc.designpattern.pattern.creational.abstractfactory;

import android.graphics.Canvas;
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