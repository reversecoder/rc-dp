package com.rc.shapemanager.pattern.structural.decorator;

import com.rc.shapemanager.pattern.creational.abstractfactory.Shape;

/**
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public abstract class ShapeDecorator implements Shape {

    private Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape) {
        super();
        this.decoratedShape = decoratedShape;
    }

    public Shape getDecoratedShape() {
        return decoratedShape;
    }
}