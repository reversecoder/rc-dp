package com.rc.shapemanager.pattern.behavioral.command;

import com.rc.shapemanager.pattern.creational.abstractfactory.Shape;
import com.rc.shapemanager.pattern.structural.bridge.Property;

/**
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public abstract class Command {

    public abstract void execute(Property property);

    public abstract void undo(Shape shape, Property property);

    public abstract void redo(Shape shape, Property property);

    public abstract String getName();

    public abstract Property getProperty();
}