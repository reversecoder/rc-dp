package com.rc.designpattern.creational.factory;

import com.rc.designpattern.structural.composite.Shape;
import com.rc.designpattern.behavioural.state.ShapeType;

import java.util.HashMap;

public class ShapeFactory {
    public static final int MINWIDTH = 50;
    public static final int MAXWIDTH = 100;
    private static final HashMap<ShapeType, Shape> shapes = new HashMap<ShapeType, Shape>();

    public static Shape getShape(ShapeType type) {
        Shape shapeImpl = shapes.get(type);

        if (shapeImpl == null) {
            if (type.equals(ShapeType.RECTANGLE)) {
//                shapeImpl = new Rectangle(Generator.getRandomColor(),Generator.getRandom(MINWIDTH,MAXWIDTH));
            } else if (type.equals(ShapeType.CIRCLE)) {
//                shapeImpl = new Circle(Generator.getRandom(100, 500), Generator.getRandom(100, 800), Generator.getRandom(50, 100), Generator.getRandomColor());
            } else if (type.equals(ShapeType.TRIANGLE)) {
//                shapeImpl = new Triangle(Generator.getRandomColor(),Generator.getRandom(MINWIDTH,MAXWIDTH));
            }
            shapes.put(type, shapeImpl);
        }
        return shapeImpl;
    }
}