package com.rc.shapemanager.pattern.structural.decorator.enumeration;

/**
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public enum ShapeType {

    CIRCLE(0), RECTANGLE(1), TRIANGLE(2), COMPOSITE(3);

    private int shapeId;

    ShapeType(int value) {
        this.shapeId = value;
    }

    public int getShapeId() {
        return shapeId;
    }
}