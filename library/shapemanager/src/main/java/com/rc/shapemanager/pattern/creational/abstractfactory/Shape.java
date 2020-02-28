package com.rc.shapemanager.pattern.creational.abstractfactory;

import com.rc.shapemanager.pattern.structural.bridge.Property;
import com.rc.shapemanager.pattern.structural.decorator.enumeration.ShapeType;

/**
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public interface Shape {
    public ShapeType getShapeType();
    public Property getProperty();
    public ShapeView getShapeView();
    public void refreshShape();
}
