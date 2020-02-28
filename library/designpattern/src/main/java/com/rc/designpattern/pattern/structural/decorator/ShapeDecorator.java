package com.rc.designpattern.pattern.structural.decorator;

import com.rc.designpattern.pattern.creational.abstractfactory.Shape;

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