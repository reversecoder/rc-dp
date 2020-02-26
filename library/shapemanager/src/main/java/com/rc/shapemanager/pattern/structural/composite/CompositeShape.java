package com.rc.shapemanager.pattern.structural.composite;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import androidx.core.content.ContextCompat;

import com.rc.shapemanager.R;
import com.rc.shapemanager.pattern.creational.abstractfactory.Shape;
import com.rc.shapemanager.pattern.creational.abstractfactory.ShapeView;
import com.rc.shapemanager.pattern.structural.bridge.CompositeShapeProperty;
import com.rc.shapemanager.pattern.structural.bridge.Property;
import com.rc.shapemanager.pattern.structural.decorator.enumeration.ShapeType;
import com.rc.shapemanager.pattern.structural.facade.PropertyKeeper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public class CompositeShape extends ShapeView {

    private CompositeShapeProperty property;

    //collection of Shapes
    private List<Shape> shapes = new ArrayList<Shape>();

    public CompositeShape(Context context) {
        super(context);
        init(null);
    }

    public CompositeShape(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CompositeShape(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attributes) {
        initProperty(attributes);
    }

    private void initProperty(AttributeSet attributes) {
        setWillNotDraw(false);

        if (property == null) {
            property = new PropertyKeeper(getContext()).getCompositeShapeProperty();
        }

        if (attributes != null) {

        } else {
            setDefaultBackgroundColor(ContextCompat.getColor(getContext(), R.color.circle_default_background_color));
            setBorderColor(ContextCompat.getColor(getContext(), R.color.circle_default_border_color));
            setBorderWidth(getResources().getDimensionPixelSize(R.dimen.default_stroke_size));
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        removeAllViews();
        for (Shape shape : shapes) {
          addView(shape.getShapeView());
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    public void updateSubscriber(Property item) {

    }

    @Override
    public ShapeType getShapeType() {
        return ShapeType.COMPOSITE;
    }

    @Override
    public Property getProperty() {
        return property;
    }

    @Override
    public ShapeView getShapeView() {
        return this;
    }

    @Override
    public void refreshShape() {
        invalidate();
    }

    /**********************************
     * Property Getter/Setter methods *
     **********************************/
    public int getDefaultBackgroundColor() {
        return property.getBackgroundColor();
    }

    public void setDefaultBackgroundColor(int defaultBackgroundColor) {
        property.setBackgroundColor(defaultBackgroundColor);
    }

    public void setSelector(boolean isSelected) {
        property.setSelector(isSelected);
    }

    public boolean isSelected() {
        return property.isSelector();
    }

    public void setIdentity(int identity) {
        property.setIdentity(identity);
    }

    public int getIdentity() {
        return property.getIdentity();
    }

    public void setProperty(Property property) {
        if (property != null) {
            this.property = (CompositeShapeProperty) property;
            refreshShape();
        }
    }

    public void setBorderWidth(int borderWidth) {
        property.setBorderWidth(borderWidth);
    }

    public void setBorderColor(int borderColor) {
        property.setBorderColor(borderColor);
    }

    /***************************
     * Composite Helper Methods *
     ****************************/
    //adding shape to drawing
    public void add(Shape shape) {
        this.shapes.add(shape);
        addView(shape.getShapeView());
        invalidate();
    }

    //removing shape from drawing
    public void remove(Shape shape) {
        shapes.remove(shape);
        removeView(shape.getShapeView());
        invalidate();
    }

    //removing all the shapes
    public void clear() {
        shapes.clear();
        removeAllViews();
        invalidate();
    }
}
