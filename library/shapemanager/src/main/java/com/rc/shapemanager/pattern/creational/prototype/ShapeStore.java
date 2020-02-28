package com.rc.shapemanager.pattern.creational.prototype;

import android.content.Context;
import android.widget.RelativeLayout;

import com.rc.shapemanager.pattern.creational.factory.ShapeFactory;
import com.rc.shapemanager.pattern.structural.bridge.CompositeShapeProperty;
import com.rc.shapemanager.pattern.structural.composite.CompositeShape;
import com.rc.shapemanager.pattern.structural.composite.CircleViewGroup;
import com.rc.shapemanager.pattern.creational.abstractfactory.Shape;
import com.rc.shapemanager.pattern.structural.bridge.CircleViewGroupProperty;
import com.rc.shapemanager.pattern.structural.bridge.Property;
import com.rc.shapemanager.pattern.structural.decorator.enumeration.ShapeType;
import com.rc.shapemanager.pattern.structural.facade.PropertyKeeper;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public class ShapeStore {

    private Context context;
    private Map<String, Shape> shapeMap = new HashMap<String, Shape>();
    private ShapeFactory shapeFactory;
    private PropertyKeeper propertyKeeper;

    public ShapeStore(Context context) {
        this.context = context;
        shapeFactory = new ShapeFactory();
        propertyKeeper = new PropertyKeeper(context);

        loadShape();
    }

    private void loadShape() {
        shapeMap.put(CircleViewGroup.class.getSimpleName(), getCircleShape(context));
        shapeMap.put(CompositeShape.class.getSimpleName(), getCompositeShape(context));
//        shapeMap.put(Rectangle.class.getSimpleName(), new Rectangle());
//        shapeMap.put(Triangle.class.getSimpleName(), new Triangle());
    }

    public Shape getShape(String tagName) {
        Shape shape = shapeMap.get(tagName);
        if (shape != null) {
            Shape newShape = shape.getShapeView().clone();
            Property property = newShape.getProperty();
            switch (newShape.getShapeType()) {
                case CIRCLE:
                    CircleViewGroupProperty circleViewGroupProperty = (CircleViewGroupProperty) property;
                    circleViewGroupProperty.setIdentity(circleViewGroupProperty.getIdentity() + 1);
                    break;
                case COMPOSITE:
                    CompositeShapeProperty compositeShapeProperty = (CompositeShapeProperty) property;
                    compositeShapeProperty.setIdentity(compositeShapeProperty.getIdentity() + 1);
                    break;
            }
            return newShape;
        }
        return null;
    }

    public void addShape(String tagName, Shape shape) {
        if (!shapeMap.containsKey(tagName)) {
            shapeMap.put(tagName, shape);
        }
    }

    private CircleViewGroup getCircleShape(Context context) {

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(200, 200);
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

        // Facade implementation
        CircleViewGroupProperty circleViewGroupProperty = propertyKeeper.getCircleViewGroupProperty();

        // Factory implementation
        CircleViewGroup circleViewGroup = (CircleViewGroup) shapeFactory.createShape(context, ShapeType.CIRCLE, circleViewGroupProperty);
        circleViewGroup.setLayoutParams(params);

        return circleViewGroup;
    }

    private CompositeShape getCompositeShape(Context context) {

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(200, 200);
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

        // Facade implementation
        CompositeShapeProperty compositeShapeProperty = propertyKeeper.getCompositeShapeProperty();

        // Factory implementation
        CompositeShape compositeShape = (CompositeShape) shapeFactory.createShape(context, ShapeType.COMPOSITE, compositeShapeProperty);
        compositeShape.setLayoutParams(params);

        return compositeShape;
    }
}