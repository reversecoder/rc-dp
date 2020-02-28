//package com.rc.designpattern.pattern.structural.decorator;
//
//
//import com.rc.designpattern.pattern.behavioural.state.ShapeType;
//import com.rc.designpattern.pattern.creational.abstractfactory.Shape;
//import com.rc.designpattern.pattern.creational.abstractfactory.ShapeView;
//import com.rc.designpattern.pattern.structural.bridge.Property;
//import com.rc.designpattern.pattern.structural.decorator.enumeration.Color;
//
///**
// * @author Md. Rashadul Alam
// * Email: rashed.droid@gmail.com
// */
//public class LineColorDecorator extends ShapeDecorator {
//
//    private Color color;
//
//    public LineColorDecorator(Shape decoratedShape, Color color) {
//        super(decoratedShape);
//        this.color = color;
//    }
//
//    public Color getColor() {
//        return color;
//    }
//
//    @Override
//    public ShapeType getShapeType() {
//        return getDecoratedShape().getShapeType();
//    }
//
//    @Override
//    public Property getProperty() {
//        return getDecoratedShape().getShapeProperty();
//    }
//
//    @Override
//    public ShapeView getShapeView() {
//        return getDecoratedShape().getShapeView();
//    }
//
//    @Override
//    public void refreshShape() {
//        getDecoratedShape().refreshShape();
//    }
//}