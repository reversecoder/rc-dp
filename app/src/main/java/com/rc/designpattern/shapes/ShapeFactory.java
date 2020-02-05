//package com.rc.designpattern.shapes;
//
//import java.util.HashMap;
//
//import com.rc.designpattern.tools.Generator;
//
///**
// * Created by enrique on 04/08/14.
// */
//public class ShapeFactory {
//    public  static final int MINWIDTH = 50;
//    public static final int MAXWIDTH = 100;
//    private static final HashMap<ShapeType,Shape> shapes = new HashMap<ShapeType,Shape>();
//
//    public static Shape getShape(ShapeType type) {
//        Shape shapeImpl = shapes.get(type);
//
//        if (shapeImpl == null) {
//            if (type.equals(ShapeType.RECTANGLE)) {
//                shapeImpl = new Rectangle(Generator.generateColor(),Generator.randInt(MINWIDTH,MAXWIDTH));
//            } else if (type.equals(ShapeType.CIRCLE)) {
//                shapeImpl = new Circle(Generator.generateColor(),Generator.randInt(MINWIDTH,MAXWIDTH));
//            } else if (type.equals(ShapeType.TRIANGLE)) {
//                shapeImpl = new Triangle(Generator.generateColor(),Generator.randInt(MINWIDTH,MAXWIDTH));
//            }
//            shapes.put(type, shapeImpl);
//        }
//        return shapeImpl;
//    }
//
//    public static enum ShapeType{
//        COMPOUND, RECTANGLE,CIRCLE,TRIANGLE;
//    }
//}
