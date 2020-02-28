package com.rc.designpattern.pattern.structural.composite;

import android.content.Context;
import android.graphics.Canvas;

import com.rc.designpattern.pattern.behavioural.state.ShapeType;
import com.rc.designpattern.pattern.structural.bridge.CircleProperty;
import com.rc.designpattern.pattern.structural.bridge.Property;

public class Circle extends ShapeView {

    public Circle(Context context, Property property) {
        super(context, property);
    }

    @Override
    public ShapeType getShapeType() {
        return ShapeType.CIRCLE;
    }

    @Override
    public void drawShape(Canvas canvas) {
        Property property = getShapeProperty();
        if (property != null) {
            canvas.drawCircle((float) property.getShapeWidth() / 2, (float) property.getShapeHeight() / 2, ((CircleProperty) property).getShapeRadius() - 10, property.getShapePaint());
        }
    }
}
