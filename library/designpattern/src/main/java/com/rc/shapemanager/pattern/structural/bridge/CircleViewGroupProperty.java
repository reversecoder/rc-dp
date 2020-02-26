package com.rc.shapemanager.pattern.structural.bridge;

import android.graphics.drawable.Drawable;

import com.rc.shapemanager.pattern.creational.builder.CircleViewGroupPropertyBuilder;

/**
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public class CircleViewGroupProperty implements Property {

    private int radius = 0, borderWidth = 0, borderColor = 0, backgroundColor = 0, highLightColor = 0;
    private Drawable mainIconDrawable, closeIconDrawable;
    private boolean isSelector = false;
    private int identity = 0;

    public CircleViewGroupProperty(int radius, int borderWidth, int borderColor, int backgroundColor, int highLightColor, Drawable mainIconDrawable, Drawable closeIconDrawable, boolean isSelector, int identity) {
        this.radius = radius;
        this.borderWidth = borderWidth;
        this.borderColor = borderColor;
        this.backgroundColor = backgroundColor;
        this.highLightColor = highLightColor;
        this.mainIconDrawable = mainIconDrawable;
        this.closeIconDrawable = closeIconDrawable;
        this.isSelector = isSelector;
        this.identity = identity;
    }

    public CircleViewGroupProperty(int radius) {
        this.radius = radius;
    }

    public CircleViewGroupProperty(CircleViewGroupPropertyBuilder circleViewGroupPropertyBuilder) {
        radius = circleViewGroupPropertyBuilder.getRadius();
        borderWidth = circleViewGroupPropertyBuilder.getBorderWidth();
        borderColor = circleViewGroupPropertyBuilder.getBorderColor();
        backgroundColor = circleViewGroupPropertyBuilder.getBackgroundColor();
        highLightColor = circleViewGroupPropertyBuilder.getHighLightColor();
        mainIconDrawable = circleViewGroupPropertyBuilder.getMainIconDrawable();
        closeIconDrawable = circleViewGroupPropertyBuilder.getCloseIconDrawable();
        isSelector = circleViewGroupPropertyBuilder.isSelector();
        identity = circleViewGroupPropertyBuilder.getIdentity();
    }

    public CircleViewGroupProperty() {
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public int getIdentity() {
        return identity;
    }

    @Override
    public int getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
    }

    @Override
    public int getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
    }

    @Override
    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getHighLightColor() {
        return highLightColor;
    }

    public void setHighLightColor(int highLightColor) {
        this.highLightColor = highLightColor;
    }

    public Drawable getMainIconDrawable() {
        return mainIconDrawable;
    }

    public void setMainIconDrawable(Drawable mainIconDrawable) {
        this.mainIconDrawable = mainIconDrawable;
    }

    public Drawable getCloseIconDrawable() {
        return closeIconDrawable;
    }

    public void setCloseIconDrawable(Drawable closeIconDrawable) {
        this.closeIconDrawable = closeIconDrawable;
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
    public String toString() {
        return "CircleViewGroupProperty{" +
                "radius=" + radius +
                ", borderWidth=" + borderWidth +
                ", borderColor=" + borderColor +
                ", backgroundColor=" + backgroundColor +
                ", highLightColor=" + highLightColor +
                ", mainIconDrawable=" + mainIconDrawable +
                ", closeIconDrawable=" + closeIconDrawable +
                ", isSelector=" + isSelector +
                ", identity=" + identity +
                '}';
    }
}
