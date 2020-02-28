package com.rc.shapemanager.pattern.structural.composite;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.rc.shapemanager.R;
import com.rc.shapemanager.gesture.DragGestureDetector;
import com.rc.shapemanager.pattern.creational.abstractfactory.ShapeView;
import com.rc.shapemanager.pattern.creational.builder.CircleViewGroupPropertyBuilder;
import com.rc.shapemanager.pattern.structural.bridge.CircleViewGroupProperty;
import com.rc.shapemanager.pattern.structural.bridge.Property;
import com.rc.shapemanager.pattern.structural.decorator.enumeration.ShapeType;
import com.rc.shapemanager.util.CustomViewManager;
import com.rc.shapemanager.views.CircleImageView;

import static com.rc.shapemanager.util.CustomViewManager.reconcileSize;

/**
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public class CircleViewGroup extends ShapeView {

    private static final float RADIUS_SCALE_FACTOR = .10f;
    private static final float BOARD_ITEM_SCALE = .10f;
    private int centerX = 0;
    private int centerY = 0;
    private int mainViewRadius;
    private int defaultRadius;
    private Paint defaultBackgroundPaint;
    private CircleViewGroupProperty property;
    private Bitmap closeIconBitmap, mainIconBitmap;
    private DragGestureDetector dragGestureDetector;
    // Custom views
    private CircleImageView closeView;

    public CircleViewGroup(Context context) {
        super(context, null);
        init(null);
    }

    public CircleViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CircleViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @Override
    public ShapeType getShapeType() {
        return ShapeType.CIRCLE;
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

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = reconcileSize(MeasureSpec.getSize(widthMeasureSpec), widthMeasureSpec);
        int height = reconcileSize(MeasureSpec.getSize(heightMeasureSpec), heightMeasureSpec);
        Log.d("CircleTest", "onMeasure>> width: " + width + " height: " + height);
        int size = Math.min(width, height);
        Log.d("CircleTest", "onMeasure>> size: " + size);        // measure children size
        measureChildren(size);
        // measure parent size
        setMeasuredDimension(size, size);
    }

    @Override
    public void onLayout(boolean changed, int l, int t, int r, int b) {
        layoutCloseChildView();
    }

    @Override
    public void onDraw(Canvas canvas) {
        drawMainCircle(canvas);
        drawMainIcon(canvas);
    }

    private void measureChildren(int size) {
        // calculate center of Y axis
        centerX = centerY = size / 2;
        Log.d("CircleTest", "measureChildren>> centerX/centerY: " + centerX);
        // calculate radius for main button
        mainViewRadius = (int) (centerX * RADIUS_SCALE_FACTOR);
        Log.d("CircleTest", "measureChildren>> mainViewRadius: " + mainViewRadius);
        // default radius for main circle
        defaultRadius = centerX - mainViewRadius - property.getBorderWidth() * 2;
        property.setRadius(defaultRadius);
        Log.d("CircleTest", "measureChildren>> defaultRadius: " + defaultRadius);
        // calculate size for board view
        int viewSize = mainViewRadius * 2;
        Log.d("CircleTest", "measureChildren>> viewSize: " + viewSize);
        int scaleViewSize = (int) (viewSize * BOARD_ITEM_SCALE);
        Log.d("CircleTest", "measureChildren>> scaleViewSize: " + scaleViewSize);
        // measure board view
        closeView.measure(50, 50);
//        measureChild(closeView, scaleViewSize, scaleViewSize);
    }

    private void layoutCloseChildView() {
        int width = closeView.getMeasuredWidth();
        int height = closeView.getMeasuredHeight();
        Log.d("CircleTest", "layoutCloseChildView>> width: " + width + " height:" + height);
//        int left = centerX + defaultRadius - (width / 2);
//        int top = centerX - defaultRadius - (width / 2);
        int left = centerX + defaultRadius - (width / 2);
        int top = centerX - defaultRadius - (width / 2);
        int right = left + width;
        int bottom = top + height;
        Log.d("CircleTest", "layoutCloseChildView>> left: " + left + " top:" + top + " right: " + right + " bottom: " + bottom);
        closeView.layout(250, 20, 300, 70);
    }

    private void drawMainCircle(final Canvas canvas) {
        Log.d("CircleTest", "drawMainCircle>> centerX: " + centerX + " centerY:" + centerY + " defaultRadius: " + defaultRadius);
        canvas.drawCircle(centerX, centerY, defaultRadius, getDefaultBackgroundPaint());
    }

    private void drawMainIcon(final Canvas canvas) {
        if (getMainIconDrawable() != null) {
            Bitmap bitmap = CustomViewManager.convertToBitmap(getMainIconDrawable(), (int) (defaultRadius * 1.2f), (int) (defaultRadius * 1.2f));

            if (bitmap != null) {
                int left = centerX - bitmap.getWidth() / 2;
                int top = centerY - bitmap.getHeight() / 2;
                canvas.drawBitmap(bitmap, left, top, getDefaultBackgroundPaint());

                Log.d("CircleTest", "drawMainIcon>> left: " + left + " top:" + top);
            }
        }
    }

    private void init(AttributeSet attributes) {
        initProperty(attributes);
        initDefaultBackgroundPaint();
        initCloseViews();
//        initActions();
    }

    private void initProperty(AttributeSet attributes) {
        setWillNotDraw(false);

        if (property == null) {
            property = new CircleViewGroupPropertyBuilder().createCircleViewGroupProperty();
        }

        if (attributes != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attributes, R.styleable.CircleViewGroup);

            setDefaultBackgroundColor(typedArray.getColor(R.styleable.CircleViewGroup_cvg_background_color, ContextCompat.getColor(getContext(), R.color.circle_default_background_color)));
            setHighLightBackgroundColor(typedArray.getColor(R.styleable.CircleViewGroup_cvg_highlight_background_color, ContextCompat.getColor(getContext(), R.color.default_highlight_background_color)));
            setBorderColor(typedArray.getColor(R.styleable.CircleViewGroup_cvg_border_color, ContextCompat.getColor(getContext(), R.color.circle_default_border_color)));
            setBorderWidth(typedArray.getDimensionPixelSize(R.styleable.CircleViewGroup_cvg_border_width, getResources().getDimensionPixelSize(R.dimen.default_stroke_size)));
            setMainIconDrawable(typedArray.getDrawable(R.styleable.CircleViewGroup_cvg_main_icon));
            setCloseIconDrawable(typedArray.getDrawable(R.styleable.CircleViewGroup_cvg_close_icon));

            typedArray.recycle();
        } else {
            setDefaultBackgroundColor(ContextCompat.getColor(getContext(), R.color.circle_default_background_color));
            setHighLightBackgroundColor(ContextCompat.getColor(getContext(), R.color.default_highlight_background_color));
            setBorderColor(ContextCompat.getColor(getContext(), R.color.circle_default_border_color));
            setBorderWidth(getResources().getDimensionPixelSize(R.dimen.default_stroke_size));
            setMainIconDrawable(ContextCompat.getDrawable(getContext(), R.drawable.user));
            setCloseIconDrawable(ContextCompat.getDrawable(getContext(), R.drawable.delete_checked));
        }
    }

    private void initDefaultBackgroundPaint() {
        defaultBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        defaultBackgroundPaint.setStyle(Paint.Style.STROKE);
        defaultBackgroundPaint.setColor(getDefaultBackgroundColor());
        defaultBackgroundPaint.setStrokeWidth(property.getBorderWidth());
    }

    private void initCloseViews() {
        closeView = new CircleImageView(getContext());
        closeView.setImageBitmap(getCloseIconBitmap());
        closeView.setOnClickListener(onCloseIconClick);
        addView(closeView);

        updateSelectedView();
    }

    /****************
     * View actions *
     ****************/
//    private void initActions() {
//        setOnLongClickListener(new OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                if (!isSelected()) {
//                    setSelector(true);
//                    CustomViewManager.doVibrate(getContext(), 150);
//                    updateSelectedView();
//                }
//                return false;
//            }
//        });
//
//        initTouchListener();
//    }
//
//    private void initTouchListener() {
//        if (dragGestureDetector == null) {
//            dragGestureDetector = new DragGestureDetector(getContext());
//        }
//        dragGestureDetector.setOnDragListener(new DragGestureDetector.OnDragListener() {
//            @Override
//            public void onDrag(float dx, float dy) {
//                DragGestureDetector.translateViewInParent(getShapeView(), dx, dy, false);
//            }
//
//            @Override
//            public void motionEvent(MotionEvent event) {
//                if (isSelected()) {
//                    if ((event.getActionMasked() == MotionEvent.ACTION_UP)) {
//                        dragGestureDetector.setDragEnabled(true);
//                    }
//                }
//            }
//        });
//        setOnTouchListener(new OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return dragGestureDetector.onTouch(event);
//            }
//        });
//    }

    private OnClickListener onCloseIconClick = new OnClickListener() {
        @Override
        public void onClick(View view) {
            if (isSelected()) {
                setSelector(false);
                dragGestureDetector.setDragEnabled(false);
                updateSelectedView();
            }
        }
    };

    private void updateSelectedView() {
        if (isSelected()) {
            closeView.setVisibility(VISIBLE);
        } else {
            closeView.setVisibility(INVISIBLE);
        }
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

    public int getHighLightBackgroundColor() {
        return property.getHighLightColor();
    }

    public void setHighLightBackgroundColor(int highLightBackgroundColor) {
        property.setHighLightColor(highLightBackgroundColor);
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

    public void setMainIconDrawable(Drawable drawable) {
        property.setMainIconDrawable(drawable);
    }

    public Drawable getMainIconDrawable() {
        return property.getMainIconDrawable();
    }

    public Bitmap getMainIconBitmap() {
        if (mainIconBitmap == null) {
            if (property.getMainIconDrawable() != null) {
                mainIconBitmap = CustomViewManager.convertToBitmap(property.getMainIconDrawable(), property.getMainIconDrawable().getIntrinsicWidth(), property.getMainIconDrawable().getIntrinsicHeight());
            } else {
                mainIconBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.user);
            }
        }
        return mainIconBitmap;
    }

    public void setCloseIconDrawable(Drawable drawable) {
        property.setCloseIconDrawable(drawable);
    }

    public Drawable getCloseIconDrawable() {
        return property.getCloseIconDrawable();
    }

    public Bitmap getCloseIconBitmap() {
        if (closeIconBitmap == null) {
            if (property.getCloseIconDrawable() != null) {
                closeIconBitmap = CustomViewManager.convertToBitmap(property.getCloseIconDrawable(), property.getCloseIconDrawable().getIntrinsicWidth(), property.getCloseIconDrawable().getIntrinsicHeight());
            } else {
                closeIconBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.delete_checked);
            }
        }
        return closeIconBitmap;
    }

    private Paint getDefaultBackgroundPaint() {
        defaultBackgroundPaint.setColor(property.isSelector() ? getHighLightBackgroundColor() : getDefaultBackgroundColor());
        // closeView.setBackgroundColor(color);
        // closeView.setImageBitmap(getCloseIconBitmap());
        return defaultBackgroundPaint;
    }

    public void setRadius(int radius) {
        property.setRadius(radius);
    }

    public void setBorderWidth(int borderWidth) {
        property.setBorderWidth(borderWidth);
    }

    public void setBorderColor(int borderColor) {
        property.setBorderColor(borderColor);
    }

    public void setProperty(Property property) {
        if (property != null) {
            this.property = (CircleViewGroupProperty) property;
            refreshShape();
        }
    }

    @Override
    public void updateSubscriber(Property item) {
        if (item != null) {
            setProperty(item);
        }
    }
}