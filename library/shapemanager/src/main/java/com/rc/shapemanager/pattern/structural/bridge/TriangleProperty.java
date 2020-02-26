package com.rc.shapemanager.pattern.structural.bridge;

/**
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public class TriangleProperty implements Property {

    private int width = 0, borderWidth = 0, borderColor = 0, fillerColor = 0, identity = 0;

    public TriangleProperty(int width, int borderWidth, int borderColor, int fillerColor, int identity) {
        this.width = width;
        this.borderWidth = borderWidth;
        this.borderColor = borderColor;
        this.fillerColor = fillerColor;
        this.identity = identity;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
    }

    public void setFillerColor(int fillerColor) {
        this.fillerColor = fillerColor;
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
        return fillerColor;
    }
}