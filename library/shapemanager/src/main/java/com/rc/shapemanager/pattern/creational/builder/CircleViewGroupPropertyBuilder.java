package com.rc.shapemanager.pattern.creational.builder;

import android.graphics.drawable.Drawable;

import com.rc.shapemanager.pattern.structural.bridge.CircleViewGroupProperty;

/**
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public class CircleViewGroupPropertyBuilder {

    private int radius = 0, borderWidth = 0, borderColor = 0, backgroundColor = 0, highLightColor = 0;
    private Drawable mainIconDrawable, closeIconDrawable;
    private boolean isSelector = false;
    private int identity = 0;

    public CircleViewGroupPropertyBuilder setRadius(int radius) {
        this.radius = radius;
        return this;
    }

    public CircleViewGroupPropertyBuilder setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
        return this;
    }

    public CircleViewGroupPropertyBuilder setBorderColor(int borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    public CircleViewGroupPropertyBuilder setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public CircleViewGroupPropertyBuilder setHighLightColor(int highLightColor) {
        this.highLightColor = highLightColor;
        return this;
    }

    public CircleViewGroupPropertyBuilder setMainIconDrawable(Drawable mainIconDrawable) {
        this.mainIconDrawable = mainIconDrawable;
        return this;
    }

    public CircleViewGroupPropertyBuilder setCloseIconDrawable(Drawable closeIconDrawable) {
        this.closeIconDrawable = closeIconDrawable;
        return this;
    }

    public CircleViewGroupPropertyBuilder setIsSelector(boolean isSelector) {
        this.isSelector = isSelector;
        return this;
    }

    public CircleViewGroupPropertyBuilder setIdentity(int identity) {
        this.identity = identity;
        return this;
    }

    public int getRadius() {
        return radius;
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

    public int getHighLightColor() {
        return highLightColor;
    }

    public Drawable getMainIconDrawable() {
        return mainIconDrawable;
    }

    public Drawable getCloseIconDrawable() {
        return closeIconDrawable;
    }

    public boolean isSelector() {
        return isSelector;
    }

    public int getIdentity() {
        return identity;
    }

    public CircleViewGroupProperty createCircleViewGroupProperty() {
        return new CircleViewGroupProperty(radius, borderWidth, borderColor, backgroundColor, highLightColor, mainIconDrawable, closeIconDrawable, isSelector, identity);
    }
}