package com.rc.designpattern.shapes;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.rc.designpattern.util.CustomViewManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by enrique on 04/08/14.
 */
public class CompoundShape extends ViewGroup implements Shape {

    private String TAG = CompoundShape.class.getSimpleName();
    private int centerX = 0;
    private int centerY = 0;

    @Override
    public int getShapeX() {
        if (children.size() == 0) {
            return 0;
        }
        int x = children.get(0).getShapeX();
        for (Shape child : children) {
            if (child.getShapeX() < x) {
                x = child.getShapeX();
            }
        }
        return x;
    }

    @Override
    public int getShapeY() {
        if (children.size() == 0) {
            return 0;
        }
        int y = children.get(0).getShapeY();
        for (Shape child : children) {
            if (child.getShapeY() < y) {
                y = child.getShapeY();
            }
        }
        return y;
    }

    @Override
    public void setShapeX(int shapeX) {
        if (children.size() > 0) {
            for (Shape child : children) {
                child.setShapeX(shapeX);
            }
        }
    }

    @Override
    public void setShapeY(int shapeY) {
        if (children.size() > 0) {
            for (Shape child : children) {
                child.setShapeX(shapeY);
            }
        }
    }

    @Override
    public int getShapeWidth() {
        int maxWidth = 0;
        int x = getShapeX();
        for (Shape child : children) {
            int childsRelativeX = child.getShapeX() - x;
            int childWidth = childsRelativeX + child.getShapeWidth();
            if (childWidth > maxWidth) {
                maxWidth = childWidth;
            }
        }
        return maxWidth;
    }

    @Override
    public int getShapeHeight() {
        int maxHeight = 0;
        int y = getShapeY();
        for (Shape child : children) {
            int childsRelativeY = child.getShapeY() - y;
            int childHeight = childsRelativeY + child.getShapeHeight();
            if (childHeight > maxHeight) {
                maxHeight = childHeight;
            }
        }
        return maxHeight;
    }

    //
//    @Override
//    public void drag() {
//        for (Shape child : children) {
//            child.drag();
//        }
//    }
//
//    @Override
//    public void drop() {
//        for (Shape child : children) {
//            child.drop();
//        }
//    }
//
//    @Override
//    public void moveTo(int x, int y) {
//        for (Shape child : children) {
//            child.moveTo(x, y);
//        }
//    }
//
//    @Override
//    public void moveBy(int x, int y) {
//        for (Shape child : children) {
//            child.moveBy(x, y);
//        }
//    }
//
//    @Override
//    public boolean isInsideBounds(int x, int y) {
//        for (Shape child : children) {
//            if (child.isInsideBounds(x, y)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
    @Override
    public void setShapeColor(int color) {
        for (Shape child : children) {
            child.setShapeColor(color);
        }
    }

    @Override
    public void unSelect() {
        for (Shape child : children) {
            child.unSelect();
        }
    }

    @Override
    public void select() {
        for (Shape child : children) {
            child.select();
        }
    }
//
//    public Shape getChildAt(int x, int y) {
//        for (Shape child : children) {
//            if (child.isInsideBounds(x, y)) {
//                return child;
//            }
//        }
//        return null;
//    }
//
//    public boolean selectChildAt(int x, int y) {
//        Shape child = getChildAt(x, y);
//        if (child != null) {
//            child.select();
//            return true;
//        }
//        return false;
//    }
//
//    public List<Shape> getSelected() {
//        List<Shape> selected = new ArrayList<>();
//        for (Shape child : children) {
//            if (child.isSelected()) {
//                selected.add(child);
//            }
//        }
//        return selected;
//    }
//
//    @Override
//    public void drawShape(final DragLayout frame, final Context context) {
//        super.drawShape(frame, context);
//
//        CompoundView compoundView = new CompoundView(context);
//        compoundView.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_orange_dark));
//        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(getShapeWidth(), getShapeHeight());
//        layoutParams.setMargins(getShapeX()-10,getShapeY()-10,0,0);
//        compoundView.setLayoutParams(layoutParams);
//        Log.d(TAG, "drawShape>>getShapeWidth: " + getShapeWidth() + " getShapeHeight: " + getShapeHeight());
//        frame.addShapeView(this, compoundView);
//        Log.d(TAG, "drawShape>>getWidth: " + layoutParams.width + " getHeight: " + layoutParams.height);
//        compoundView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//
//                int X = (int) event.getRawX();
//                int Y = (int) event.getRawY();
//                switch (event.getAction() & MotionEvent.ACTION_MASK) {
//                    case MotionEvent.ACTION_DOWN:
//                        RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
//                        if (lParams.getRule(RelativeLayout.ALIGN_PARENT_BOTTOM) == RelativeLayout.TRUE) {
//                            lParams.topMargin = v.getTop();
//                            lParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 0);
//                            Log.d("MainActivity", "added Rule bottom");
//                        }
//                        if (lParams.getRule(RelativeLayout.ALIGN_PARENT_TOP) == RelativeLayout.TRUE) {
//                            lParams.bottomMargin = v.getBottom();
//                            lParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
//                            Log.d("MainActivity", "added Rule top");
//                        }
//                        if (lParams.getRule(RelativeLayout.ALIGN_PARENT_LEFT) == RelativeLayout.TRUE) {
//                            lParams.rightMargin = v.getRight();
//                            lParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 0);
//                            Log.d("MainActivity", "added Rule left");
//                        }
//                        if (lParams.getRule(RelativeLayout.ALIGN_PARENT_RIGHT) == RelativeLayout.TRUE) {
//                            lParams.leftMargin = v.getLeft();//rootLayout.getMeasuredWidth()-v.getWidth();
//                            lParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0);
//                            Log.d("MainActivity", "added Rule right");
//                        }
//
//                        Log.d("MainActivity", "leftPos:" + v.getLeft() + "topPos:" + v.getTop());
//
//                        xDelta = X - lParams.leftMargin;
//                        yDelta = Y - lParams.topMargin;
//
//                        Log.d("MainActivity", "Action_Down:X=" + X + ",Y=" + Y + ",xD=" + xDelta + ",yD=" + yDelta + ",lm=" + lParams.leftMargin + ",tm=" + lParams.topMargin);
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        Log.d("MainActivity", "Action_up");
//                        break;
//                    case MotionEvent.ACTION_POINTER_DOWN:
//                        Log.d("MainActivity", "Action_Pointer_Down");
//                        break;
//                    case MotionEvent.ACTION_POINTER_UP:
//                        Log.d("MainActivity", "Action_Pointer_Up");
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
//                        layoutParams.leftMargin = X - xDelta;
//                        layoutParams.topMargin = Y - yDelta;
//                        layoutParams.rightMargin = 0;
//                        layoutParams.bottomMargin = 0;
//
//                        v.setLayoutParams(layoutParams);
//                        v.invalidate();
//                        //v.animate().x(X-xDelta).y(Y-yDelta).setDuration(0).start();
//                        Log.d("MainActivity", "Action_Move:X=" + X + ",Y=" + Y + ",xD=" + xDelta + ",yD=" + yDelta);
//                        break;
//                }
//                frame.invalidate();
//
//
//                touchGestureDetector.onTouchEvent(event);
//                return true;
//            }
//        });
//    }
//
//    private TouchGestureDetector touchGestureDetector = new TouchGestureDetector(new TouchGestureDetector.TouchGestureListener() {
//        @Override
//        public void onPress(MotionEvent motionEvent) {
//
//        }
//
//        @Override
//        public void onTap(MotionEvent motionEvent) {
//
//        }
//
//        @Override
//        public void onDrag(MotionEvent motionEvent) {
//
//        }
//
//        @Override
//        public void onMove(MotionEvent motionEvent) {
//
//        }
//
//        @Override
//        public void onRelease(MotionEvent motionEvent) {
//
//        }
//
//        @Override
//        public void onLongPress(MotionEvent motionEvent) {
//            Util.doVibrate(context, 100);
////            setViewSelected(!isViewSelected());
////            requestLayout();
//        }
//
//        @Override
//        public void onMultiTap(MotionEvent motionEvent, int clicks) {
//
//        }
//    });
//
//    private class CompoundView extends View implements ShapeView {
//
//        public CompoundView(Context context) {
//            super(context);
//        }
//
//        @Override
//        protected synchronized void onDraw(Canvas canvas) {
//            if (isSelected()) {
//                enableSelectionStyle();
//                canvas.drawRect(getShapeX() - 1, getShapeY() - 1, getShapeWidth() + 1, getShapeHeight() + 1, borderPaint);
//                disableSelectionStyle();
//            }
//
//            for (Shape child : children) {
//                child.drawShape(frame, getContext());
//            }
//        }
//    }
//
//    @Override
//    public String toString() {
//        return "CompoundShape{" +
//                "children=" + children +
//                ", shapeX=" + shapeX +
//                ", shapeY=" + shapeY +
//                '}';
//    }


    private List<Shape> children = new ArrayList<>();

    public CompoundShape(Context context, Shape... components) {
        super(context);
        setWillNotDraw(false);
        add(components);
    }


    private int _yDelta;
    private int _xDelta;

    public void add(final Shape component) {
        children.add(component);
        addView(((View) component));

//        ((View) component).setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent event) {
//                component.setShapeX((int) event.getX());
//                component.setShapeY((int) event.getY());
//                ((View) component).invalidate();
//                return true;
//            }
//        });
    }

    public void add(Shape... components) {
//        children.addAll(Arrays.asList(components));
        for (Shape child : components) {
            add(child);
        }
    }

    public void remove(Shape child) {
        children.remove(child);
    }

    public void remove(Shape... components) {
        children.removeAll(Arrays.asList(components));
    }

    public void clear() {
        children.clear();
        removeAllViews();
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Optimized measurement
        int width = CustomViewManager.reconcileSize(MeasureSpec.getSize(widthMeasureSpec), widthMeasureSpec);
        int height = CustomViewManager.reconcileSize(MeasureSpec.getSize(heightMeasureSpec), heightMeasureSpec);
        Log.d(TAG, "onMeasure>> width: " + width + " height: " + height);
        int size = Math.min(width, height);
        Log.d(TAG, "onMeasure>> size: " + size);
        // measure children size
        centerX = centerY = size / 2;
        Log.d(TAG, "onMeasure>> centerX=centerY: " + centerX);
        for (Shape child : children) {
            ((View) child).measure(child.getShapeWidth(), child.getShapeHeight());
        }
        // measure parent size
        setMeasuredDimension(size, size);
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        Log.d(TAG, "onMeasure>> w: " + MeasureSpec.toString(widthMeasureSpec));
//        Log.d(TAG, "onMeasure>> h: " + MeasureSpec.toString(heightMeasureSpec));
//
//        int desiredWidth = getSuggestedMinimumWidth() + getPaddingLeft() + getPaddingRight();
//        int desiredHeight = getSuggestedMinimumHeight() + getPaddingTop() + getPaddingBottom();
//
//        int width = CustomViewManager.reconcileSize(MeasureSpec.getSize(widthMeasureSpec), widthMeasureSpec);
//        int height = CustomViewManager.reconcileSize(MeasureSpec.getSize(heightMeasureSpec), heightMeasureSpec);
//        Log.d(TAG, "onMeasure>> width: " + width + " height: " + height);
//
//        int size = Math.min(width, height);
//        Log.d(TAG, "onMeasure>> size: " + size);
//
//        // set child view measure
//        for (Shape child : children) {
//            ((View) child).measure(child.getShapeWidth(), child.getShapeHeight());
//        }
//
//        // set parent view measure
//        setMeasuredDimension(size, size);
//    }

//    @Override
//    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        Log.d(TAG,"onLayout()>>INSIDE ON MEASURE SUPER VIEWGROUP");
//
//        for (Shape child : children) {
//            View childView = (View)child;
//            if (childView.getVisibility() != View.GONE) {
//                //Make or work out measurements for children here (MeasureSpec.make...)
//                measureChild (childView, widthMeasureSpec, heightMeasureSpec);
//                childView.measure(widthMeasureSpec, heightMeasureSpec);
//            }
//        }
//    }

    @Override
    public void onLayout(boolean changed, int l, int t, int r, int b) {
//        for (Shape child : children) {
//            int left = child.getShapeX() - child.getShapeWidth() / 2;
//            int top = child.getShapeY() - child.getShapeHeight() / 2;
//            int right = left + child.getShapeWidth();
//            int bottom = top + child.getShapeHeight();
//            Log.d(TAG, "onLayout()>> left: " + left + " top: " + top + " right: " + right + " bottom: " + bottom);
//            ((View) child).layout(left, top, right, bottom);
//        }

        for (Shape child : children) {
            int left = centerX - child.getShapeWidth() / 2;
            int top = centerY - child.getShapeHeight() / 2;
            int right = left + child.getShapeWidth();
            int bottom = top + child.getShapeHeight();

            View childView = (View) child;
            if (childView.getVisibility() != GONE) {
                Log.d(TAG, "onLayout()>> left: " + left + " top: " + top + " right: " + right + " bottom: " + bottom);
                childView.layout(left, top, right, bottom);
            }
        }

        Log.d(TAG, "onLayout()>>Inside onLayout()");
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        drawShape(canvas);
    }

    @Override
    public void drawShape(Canvas canvas) {
//        removeAllViews();
//        for (Shape child : children) {
//            ((View) child).draw(canvas);

//            setViewTouchListener(((View) child));
//            addView(((View) child));
//            ((View) child).setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(getContext(), "Yes", Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
    }

//        private int _xDelta;
//    private int _yDelta;
//    private void setViewTouchListener(View v) {
//        v.setOnTouchListener(new OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent event) {
//                final int X = (int) event.getRawX();
//                final int Y = (int) event.getRawY();
//                switch (event.getAction() & MotionEvent.ACTION_MASK) {
//                    case MotionEvent.ACTION_DOWN:
//                        ViewGroup.LayoutParams lParams =  view.getLayoutParams();
//                        RelativeLayout.LayoutParams containerParams = new RelativeLayout.LayoutParams(lParams);
//                        Log.d(TAG, "setViewTouchListener()>>onTouch()>> containerParams: " + containerParams.toString());
//                        _xDelta = X - containerParams.leftMargin;
//                        _yDelta = Y - containerParams.topMargin;
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        break;
//                    case MotionEvent.ACTION_POINTER_DOWN:
//                        break;
//                    case MotionEvent.ACTION_POINTER_UP:
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        ViewGroup.LayoutParams parentParams =  view.getLayoutParams();
//                        RelativeLayout.LayoutParams updatedParams = new RelativeLayout.LayoutParams(parentParams);
//                        updatedParams.leftMargin = X - _xDelta;
//                        updatedParams.topMargin = Y - _yDelta;
//                        updatedParams.rightMargin = -250;
//                        updatedParams.bottomMargin = -250;
//                        Log.d(TAG, "setViewTouchListener()>>onTouch()>> updatedParams: " + updatedParams.toString());
//                        view.setLayoutParams(updatedParams);
//                        break;
//                }
//                view.requestLayout();
//                return true;
//            }
//        });
//    }
}