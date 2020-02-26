package com.rc.shapemanager.pattern.structural.composite;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;

import com.rc.shapemanager.pattern.creational.abstractfactory.ShapeView;
import com.rc.shapemanager.pattern.structural.bridge.Property;
import com.rc.shapemanager.pattern.structural.bridge.TriangleProperty;
import com.rc.shapemanager.pattern.structural.decorator.enumeration.ShapeType;

import java.util.Random;

/**
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public class Triangle extends ShapeView {

    private TriangleProperty property;
    private Paint paint;

    public Triangle(Context context, Property property) {
        super(context);
        this.property = (TriangleProperty) property;
        this.paint = new Paint();
    }

    public void setProperty(TriangleProperty property) {
        this.property = property;
    }

    @Override
    public ShapeType getShapeType() {
        return ShapeType.TRIANGLE;
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
    public void onDraw(Canvas canvas) {
        // set the fill color
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(property.getBackgroundColor());

        // draw the fill
        Random rand = new Random();
        int left = rand.nextInt(300);
        int top = rand.nextInt(500);
//        int right = left + property.getWidth();
//        int bottom = top + property.getHeight();
//        canvas.drawRect(left, top, right, bottom, paint);
        drawTriangle(canvas, paint, left, top, property.getWidth());
        Log.d("onDraw>>triangleShape", "left: " + left + " top: " + top);

        // set border color
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(property.getBorderWidth());
        paint.setColor(property.getBorderColor());
        drawTriangle(canvas, paint, left, top, property.getWidth());
        Log.d("onDraw>>triangleBorder", "left: " + left + " top: " + top);
    }

    public void drawTriangle(Canvas canvas, Paint paint, int x, int y, int width) {
        int halfWidth = width / 2;

        Path path = new Path();
        path.moveTo(x, y - halfWidth); // Top
        path.lineTo(x - halfWidth, y + halfWidth); // Bottom left
        path.lineTo(x + halfWidth, y + halfWidth); // Bottom right
        path.lineTo(x, y - halfWidth); // Back to Top
        path.close();

        canvas.drawPath(path, paint);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    public void refreshShape() {

    }

    @Override
    public void updateSubscriber(Property item) {

    }
}