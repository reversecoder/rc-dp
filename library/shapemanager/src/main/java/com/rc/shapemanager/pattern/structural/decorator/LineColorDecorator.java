package com.rc.shapemanager.pattern.structural.decorator;

import com.rc.shapemanager.pattern.structural.bridge.Property;
import com.rc.shapemanager.pattern.creational.abstractfactory.Shape;
import com.rc.shapemanager.pattern.creational.abstractfactory.ShapeView;
import com.rc.shapemanager.pattern.structural.decorator.enumeration.Color;
import com.rc.shapemanager.pattern.structural.decorator.enumeration.ShapeType;

/**
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public class LineColorDecorator extends ShapeDecorator {

    private Color color;

    public LineColorDecorator(Shape decoratedShape, Color color) {
        super(decoratedShape);
        this.color = color;
    }

    public Color getColor() {
        return color;
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
        getDecoratedShape().refreshShape();
    }
}