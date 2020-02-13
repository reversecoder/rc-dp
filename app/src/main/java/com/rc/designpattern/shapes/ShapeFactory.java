package com.rc.designpattern.shapes;

import com.rc.designpattern.enumeration.ShapeType;
import com.rc.designpattern.tools.Generator;

import java.util.HashMap;

public class ShapeFactory {
    public static final int MINWIDTH = 50;
    public static final int MAXWIDTH = 100;
    private static final HashMap<ShapeType, Shape> shapes = new HashMap<ShapeType, Shape>();

    public static Shape getShape(ShapeType type) {
        Shape shapeImpl = shapes.get(type);

        if (shapeImpl == null) {
            if (type.equals(ShapeType.RECTANGLE)) {
//                shapeImpl = new Rectangle(Generator.generateColor(),Generator.randInt(MINWIDTH,MAXWIDTH));
            } else if (type.equals(ShapeType.CIRCLE)) {
//                shapeImpl = new Circle(Generator.randInt(100, 500), Generator.randInt(100, 800), Generator.randInt(50, 100), Generator.generateColor());
            } else if (type.equals(ShapeType.TRIANGLE)) {
//                shapeImpl = new Triangle(Generator.generateColor(),Generator.randInt(MINWIDTH,MAXWIDTH));
            }
            shapes.put(type, shapeImpl);
        }
        return shapeImpl;
    }
}