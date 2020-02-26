package com.rc.shapemanager.pattern.structural.decorator;

import com.rc.shapemanager.pattern.structural.decorator.enumeration.Color;
import com.rc.shapemanager.pattern.structural.decorator.enumeration.ShapeType;
import com.rc.shapemanager.pattern.structural.bridge.Property;
import com.rc.shapemanager.pattern.structural.composite.CircleViewGroup;
import com.rc.shapemanager.pattern.creational.abstractfactory.Shape;
import com.rc.shapemanager.pattern.creational.abstractfactory.ShapeView;

/**
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public class BackgroundColorDecorator extends ShapeDecorator {

    private Color color;
    private int colorValue = 0;

    public BackgroundColorDecorator(Shape decoratedShape, Color color) {
        super(decoratedShape);
        this.color = color;
    }

    public BackgroundColorDecorator(Shape decoratedShape, int colorValue) {
        super(decoratedShape);
        this.colorValue = colorValue;
    }

    public int getColorValue() {
        if (color != null) {
            return color.getValue();
        } else if (colorValue != 0) {
            return colorValue;
        }
        return -1;
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
        if (getProperty() != null) {
            switch (getShapeType()) {
                case CIRCLE:
                    ((CircleViewGroup) getDecoratedShape()).setDefaultBackgroundColor(getColorValue());
                    getDecoratedShape().refreshShape();
                    break;
            }
        }
    }
}