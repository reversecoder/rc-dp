package com.rc.shapemanager.pattern.structural.bridge;

/**
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public class CompositeShapeProperty implements Property {

    private int borderWidth = 0, borderColor = 0, backgroundColor = 0;
    private boolean isSelector = false;
    private int identity = 0;

    public CompositeShapeProperty(int borderWidth, int borderColor, int backgroundColor, boolean isSelector, int identity) {
        this.borderWidth = borderWidth;
        this.borderColor = borderColor;
        this.backgroundColor = backgroundColor;
        this.isSelector = isSelector;
        this.identity = identity;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public boolean isSelector() {
        return isSelector;
    }

    public void setSelector(boolean selector) {
        isSelector = selector;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    @Override
    public int getIdentity() {
        return identity;
    }

    @Override
    public int getBorderWidth() {
        return borderWidth;
    }

    @Override
    public int getBorderColor() {
        return borderColor;
    }

    @Override
    public int getBackgroundColor() {
        return backgroundColor;
    }
}
