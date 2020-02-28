package com.rc.shapemanager.pattern.creational.factory;

import android.content.Context;

import com.rc.shapemanager.pattern.structural.bridge.CompositeShapeProperty;
import com.rc.shapemanager.pattern.structural.composite.CompositeShape;
import com.rc.shapemanager.pattern.structural.decorator.enumeration.ShapeType;
import com.rc.shapemanager.pattern.structural.bridge.Property;
import com.rc.shapemanager.pattern.creational.abstractfactory.Shape;
import com.rc.shapemanager.pattern.structural.bridge.CircleViewGroupProperty;
import com.rc.shapemanager.pattern.structural.composite.CircleViewGroup;
import com.rc.shapemanager.pattern.structural.composite.Rectangle;
import com.rc.shapemanager.pattern.structural.composite.Triangle;

/**
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public class ShapeFactory {

    public Shape createShape(Context context, ShapeType type, Property property) {
        Shape shape = null;
        switch (type) {
            case CIRCLE:
                shape = new CircleViewGroup(context);
                ((CircleViewGroup) shape).setProperty((CircleViewGroupProperty) property);
                break;
            case RECTANGLE:
                shape = new Rectangle(context, property);
                break;
            case TRIANGLE:
                shape = new Triangle(context, property);
                break;
            case COMPOSITE:
                shape = new CompositeShape(context);
                ((CompositeShape) shape).setProperty((CompositeShapeProperty) property);
                break;
        }

        return shape;
    }
//
//    public List<Shape> getShapeList() {
//        return shapeList;
//    }
}