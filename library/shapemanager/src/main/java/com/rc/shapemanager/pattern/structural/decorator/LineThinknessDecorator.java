package com.rc.shapemanager.pattern.structural.decorator;

import com.rc.shapemanager.pattern.structural.bridge.Property;
import com.rc.shapemanager.pattern.creational.abstractfactory.Shape;
import com.rc.shapemanager.pattern.structural.bridge.CircleViewGroupProperty;
import com.rc.shapemanager.pattern.structural.bridge.RectangleProperty;
import com.rc.shapemanager.pattern.structural.bridge.TriangleProperty;
import com.rc.shapemanager.pattern.creational.abstractfactory.ShapeView;
import com.rc.shapemanager.pattern.structural.decorator.enumeration.ShapeType;

/**
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public class LineThinknessDecorator extends ShapeDecorator {

    private double thickness;

    public LineThinknessDecorator(Shape decoratedShape, double thickness) {
        super(decoratedShape);
        this.thickness = thickness;
    }

    public double getThickness() {
        return thickness;
    }

    @Override
    public ShapeType getShapeType() {
        return getDecoratedShape().getShapeType();
    }

    @Override
    public Property getProperty() {
        return getDecoratedShape().getProperty();
    }

    @Override
    public ShapeView getShapeView() {
        return getDecoratedShape().getShapeView();
    }

    @Override
    public void refreshShape() {
        switch (getShapeType()) {
            case RECTANGLE:
                ((RectangleProperty) getProperty()).setBorderWidth((int) thickness);
                break;
            case CIRCLE:
                ((CircleViewGroupProperty) getProperty()).setBorderWidth((int) thickness);
                break;
            case TRIANGLE:
                ((TriangleProperty) getProperty()).setBorderWidth((int) thickness);
                break;
        }
        getDecoratedShape().refreshShape();
    }
}