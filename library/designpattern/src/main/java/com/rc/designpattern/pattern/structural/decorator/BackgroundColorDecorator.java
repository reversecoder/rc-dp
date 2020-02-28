//package com.rc.designpattern.pattern.structural.decorator;
//
//import android.graphics.Canvas;
//import android.view.View;
//
//import com.rc.designpattern.pattern.behavioural.state.ShapeType;
//import com.rc.designpattern.pattern.creational.abstractfactory.Shape;
//import com.rc.designpattern.pattern.structural.bridge.Property;
//import com.rc.designpattern.pattern.structural.decorator.enumeration.Color;
//
///**
// * @author Md. Rashadul Alam
// * Email: rashed.droid@gmail.com
// */
//public class BackgroundColorDecorator extends ShapeDecorator {
//
//    private Color color;
//    private int colorValue = 0;
//
//    public BackgroundColorDecorator(Shape decoratedShape, Color color) {
//        super(decoratedShape);
//        this.color = color;
//    }
//
//    public BackgroundColorDecorator(Shape decoratedShape, int colorValue) {
//        super(decoratedShape);
//        this.colorValue = colorValue;
//    }
//
//    public int getColorValue() {
//        if (color != null) {
//            return color.getValue();
//        } else if (colorValue != 0) {
//            return colorValue;
//        }
//        return -1;
//    }
//
////    @Override
////    public ShapeType getShapeType() {
////        return getDecoratedShape().getShapeType();
////    }
////
////    @Override
////    public Property getProperty() {
////        return getDecoratedShape().getProperty();
////    }
////
////    @Override
////    public ShapeView getShapeView() {
////        return getDecoratedShape().getShapeView();
////    }
////
////    @Override
////    public void refreshShape() {
////        if (getProperty() != null) {
////            switch (getShapeType()) {
////                case CIRCLE:
////                    ((Circle) getDecoratedShape()).setDefaultBackgroundColor(getColorValue());
////                    getDecoratedShape().refreshShape();
////                    break;
////            }
////        }
////    }
//
//
//    @Override
//    public ShapeType getShapeType() {
//        return getDecoratedShape().getShapeType();
//    }
//
//    @Override
//    public Property getShapeProperty() {
//        return getDecoratedShape().getShapeProperty();
//    }
//
//    @Override
//    public void setShapeProperty(Property shapeProperty) {
//        getDecoratedShape().setShapeProperty(shapeProperty);
//    }
//
//    @Override
//    public Shape getShape() {
//        return getDecoratedShape();
//    }
//
//    @Override
//    public View getShapeView() {
//        return getDecoratedShape().getShapeView();
//    }
//
//    @Override
//    public void drawShape(Canvas canvas) {
//        getDecoratedShape().drawShape(canvas);
//    }
//
//    @Override
//    public void refreshView() {
//        getDecoratedShape().refreshView();
//    }
//}