package com.rc.shapemanager.pattern.creational.singleton;

import android.content.Context;

import com.rc.shapemanager.pattern.creational.prototype.ShapeStore;
import com.rc.shapemanager.pattern.structural.composite.CompositeShape;
import com.rc.shapemanager.pattern.structural.composite.CircleViewGroup;

/**
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public class ShapeManager {

    private volatile static ShapeManager shapeManager;
    private Context context;
    private ShapeStore shapeStore;

    private ShapeManager(Context context) {
        this.context = context;
        this.shapeStore = new ShapeStore(context);
    }

    public static ShapeManager getInstance(Context context) {
        if (shapeManager == null) {
            // To make thread safe
            synchronized (ShapeManager.class) {
                // check again as multiple threads
                // can reach above step
                if (shapeManager == null)
                    shapeManager = new ShapeManager(context);
            }
        }
        return shapeManager;
    }

    public CircleViewGroup getCircleShape() {
        return (CircleViewGroup) shapeStore.getShape(CircleViewGroup.class.getSimpleName());
    }

    public CompositeShape getCompositeShape() {
        return (CompositeShape) shapeStore.getShape(CompositeShape.class.getSimpleName());
    }

    public void destroyObject(){
        shapeManager = null;
        shapeStore = null;
    }
}