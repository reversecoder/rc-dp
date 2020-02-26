package com.rc.shapemanager.pattern.creational.builder;

import com.rc.shapemanager.pattern.structural.bridge.CompositeShapeProperty;
import com.rc.shapemanager.pattern.structural.bridge.Property;

/**
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public class CompositeShapePropertyBuilder {

    private int borderWidth = 0, borderColor = 0, backgroundColor = 0;
    private boolean isSelector = false;
    private int identity = 0;

    public CompositeShapePropertyBuilder setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
        return this;
    }

    public CompositeShapePropertyBuilder setBorderColor(int borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    public CompositeShapePropertyBuilder setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public CompositeShapePropertyBuilder setIsSelector(boolean isSelector) {
        this.isSelector = isSelector;
        return this;
    }

    public CompositeShapePropertyBuilder setIdentity(int identity) {
        this.identity = identity;
        return this;
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public int getBorderColor() {
        return borderColor;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public boolean isSelector() {
        return isSelector;
    }

    public int getIdentity() {
        return identity;
    }

    public CompositeShapeProperty createCompositeProperty() {
        return new CompositeShapeProperty(borderWidth, borderColor, backgroundColor, isSelector, identity);
    }
}