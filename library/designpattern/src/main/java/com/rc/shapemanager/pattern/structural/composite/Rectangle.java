package com.rc.shapemanager.pattern.structural.composite;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import com.rc.shapemanager.pattern.creational.abstractfactory.ShapeView;
import com.rc.shapemanager.pattern.structural.bridge.Property;
import com.rc.shapemanager.pattern.structural.bridge.RectangleProperty;
import com.rc.shapemanager.pattern.structural.decorator.enumeration.ShapeType;

import java.util.Random;

/**
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public class Rectangle extends ShapeView {

    private RectangleProperty property;
    private Paint paint;

    public Rectangle(Context context, Property property) {
        super(context);
        this.property = (RectangleProperty) property;
        this.paint = new Paint();
    }

//    private void init(AttributeSet attrs) {
//        retrieveAttributes(attrs);
//        initDefaultBackgroundPaint();
//        initIncreaseButtonPaint();
//        initBoardViews();
//    }
//
//    private void retrieveAttributes(AttributeSet attributes) {
//        TypedArray typedArray = getContext().obtainStyledAttributes(attributes, R.styleable.IncrementProductView);
//        setMainIconDrawable(typedArray.getDrawable(R.styleable.IncrementProductView_ipv_middle_icon));
//        setIncrementIcon(typedArray.getDrawable(R.styleable.IncrementProductView_ipv_increment_icon));
//        setDecrementIcon(typedArray.getDrawable(R.styleable.IncrementProductView_ipv_decrement_icon));
//        setCloseIconDrawable(typedArray.getDrawable(R.styleable.IncrementProductView_ipv_add_icon));
//        setConfirmIcon(typedArray.getDrawable(R.styleable.IncrementProductView_ipv_confirm_icon));
//        setDefaultBackgroundColor(typedArray.getColor(R.styleable.IncrementProductView_ipv_default_background_color,
//                ContextCompat.getColor(getContext(), R.color.default_background_color)));
//        setHighLightBackgroundColor(typedArray.getColor(R.styleable.IncrementProductView_ipv_highlight_background_color,
//                ContextCompat.getColor(getContext(), R.color.highlight_background_color)));
//        setBoardBackgroundColor(typedArray.getColor(R.styleable.IncrementProductView_ipv_counter_background_color,
//                ContextCompat.getColor(getContext(), R.color.counter_background_color)));
//        setBoardTextColor(typedArray.getColor(R.styleable.IncrementProductView_ipv_text_color,
//                ContextCompat.getColor(getContext(), android.R.color.white)));
//        setBoardTextSize(typedArray.getDimensionPixelSize(R.styleable.IncrementProductView_ipv_text_size,
//                getResources().getDimensionPixelSize(R.dimen.text_size)));
//        typedArray.recycle();
//    }

    public void setProperty(RectangleProperty property) {
        this.property = property;
    }

    @Override
    public ShapeType getShapeType() {
        return ShapeType.RECTANGLE;
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
        int left = rand.nextInt(275);
        int top = rand.nextInt(350);
        int right = left + property.getWidth();
        int bottom = top + property.getHeight();
        canvas.drawRect(left, top, right, bottom, paint);
        Log.d("onDraw>>rectangleShape", "left: " + left + " top: " + top + " right: " + right + " bottom: " + bottom);

        // set border color
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(property.getBorderWidth());
        paint.setColor(property.getBorderColor());
        canvas.drawRect(left, top, right, bottom, paint);
        Log.d("onDraw>>rectangleBorder", "left: " + left + " top: " + top + " right: " + right + " bottom: " + bottom);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    public void refreshShape() {
        invalidate();
    }

    @Override
    public void updateSubscriber(Property item) {

    }
}