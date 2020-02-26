package com.rc.shapemanager.pattern.structural.decorator;

import com.rc.shapemanager.pattern.structural.bridge.Property;
import com.rc.shapemanager.pattern.creational.abstractfactory.Shape;
import com.rc.shapemanager.pattern.creational.abstractfactory.ShapeView;
import com.rc.shapemanager.pattern.structural.decorator.enumeration.LineStyle;
import com.rc.shapemanager.pattern.structural.decorator.enumeration.ShapeType;

/**
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public class LineStyleDecorator extends ShapeDecorator {

    protected LineStyle style;

    public LineStyleDecorator(Shape decoratedShape, LineStyle style) {
        super(decoratedShape);
        this.style = style;
    }

    public LineStyle getStyle() {
        return style;
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
