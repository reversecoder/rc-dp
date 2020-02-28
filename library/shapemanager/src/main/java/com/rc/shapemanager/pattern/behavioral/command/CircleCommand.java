package com.rc.shapemanager.pattern.behavioral.command;

import android.util.Log;

import com.rc.shapemanager.pattern.creational.abstractfactory.Shape;
import com.rc.shapemanager.pattern.structural.bridge.CircleViewGroupProperty;
import com.rc.shapemanager.pattern.structural.bridge.Property;
import com.rc.shapemanager.pattern.structural.composite.CircleViewGroup;

/**
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public class CircleCommand extends Command {

    private String TAG = "CommandManager";
    private String name;
    private Shape shape;
    private Property property;

    public CircleCommand(String name, Shape shape) {
        this.name = name;
        this.shape = shape;
    }

    @Override
    public void execute(Property property) {
        this.property = property;
        ((CircleViewGroup) shape.getShapeView()).setProperty(property);
        Log.d(TAG, "CircleCommand>>execute>>property: " + property.toString());
    }

    @Override
    public void undo(Shape shape, Property property) {
        this.property = property;
        ((CircleViewGroup) shape.getShapeView()).setProperty(property);
        Log.d(TAG, "CircleCommand>>undo>>property: " + property.toString());
    }

    @Override
    public void redo(Shape shape, Property property) {
        this.property = property;
        ((CircleViewGroup) shape.getShapeView()).setProperty(property);
        Log.d(TAG, "CircleCommand>>redo>>property: " + property.toString());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Property getProperty() {
        return property;
    }

    @Override
    public String toString() {
        return "CircleCommand{" +
                "name='" + name + '\'' +
                ", shape=" + shape +
                ", property=" + property +
                '}';
    }
}
