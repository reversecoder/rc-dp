package com.rc.shapemanager.pattern.creational.abstractfactory;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.rc.shapemanager.pattern.behavioral.observer.Subscriber;
import com.rc.shapemanager.pattern.structural.bridge.Property;

/**
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public abstract class ShapeView extends ViewGroup implements Shape, Subscriber<Property>, Cloneable {

    public ShapeView(Context context) {
        super(context);
    }

    public ShapeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ShapeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public abstract void onDraw(Canvas canvas);

    @Override
    public ShapeView clone() {
        ShapeView clone = null;
        try {
            clone = (ShapeView) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}