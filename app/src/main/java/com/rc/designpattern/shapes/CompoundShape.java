package com.rc.designpattern.shapes;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

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

        screenHeight = getResources().getDisplayMetrics().heightPixels;
        screenWidth = getResources().getDisplayMetrics().widthPixels;
    }

    public void add(final Shape component) {
        children.add(component);
        addView(((View) component));

//        ((View) component).setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getContext(), "Yes", Toast.LENGTH_SHORT).show();
//            }
//        });

//        ((View) component).setOnTouchListener(new OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                Log.d(TAG, "onTouchListener>> X: " + (int)motionEvent.getX() + " Y: " + (int)motionEvent.getY());
//
//                // Set drawBallView currX and currY value to user finger x y ordinate value..
//                component.setShapeX((int)motionEvent.getX());
//                component.setShapeY((int)motionEvent.getY());
//
//                // Set ball color to blue.
//                component.setShapeColor(Color.BLUE);
//
//                // Notify drawBallView to redraw. This will invoke DrawBallView's onDraw() method.
//                ((View) component).invalidate();
//
//                // Return true means this listener has complete process this event successfully.
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
//        setMeasuredDimension(size, size);
        setMeasuredDimension(getShapeWidth() + 30, getShapeHeight() + 30);
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

    /***********************
     * Touch events
     *************************/
    private DIRECTION dragDirection;
//    private static final int TOP = 0x15;
//    private static final int LEFT = 0x16;
//    private static final int BOTTOM = 0x17;
//    private static final int RIGHT = 0x18;
//    private static final int LEFT_TOP = 0x11;
//    private static final int RIGHT_TOP = 0x12;
//    private static final int LEFT_BOTTOM = 0x13;
//    private static final int RIGHT_BOTTOM = 0x14;
//    private static final int CENTER = 0x19;
    public enum DIRECTION {TOP,LEFT, BOTTOM, RIGHT, LEFT_TOP, RIGHT_TOP,LEFT_BOTTOM,RIGHT_BOTTOM,CENTER}
    private int lastX;
    private int lastY;
    private int screenWidth;
    private int screenHeight;
    private int oriLeft;
    private int oriRight;
    private int oriTop;
    private int oriBottom;
    private int touchAreaLength = 60;
    private int minHeight = 120;
    private int minWidth = 180;
    private boolean mFixedSize = false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                oriLeft = getLeft();
                oriRight = getRight();
                oriTop = getTop();
                oriBottom = getBottom();

                lastY = (int) event.getRawY();
                lastX = (int) event.getRawX();
                dragDirection = getDirection((int) event.getX(), (int) event.getY());
                Log.d(TAG, "onTouchEvent(MotionEvent.ACTION_DOWN): lastX: " + lastX + " lastY: " + lastY);
                Log.d(TAG, "onTouchEvent(MotionEvent.ACTION_DOWN): dragDirection: " + dragDirection);
                Log.d(TAG, "onTouchEvent(MotionEvent.ACTION_DOWN): oriLeft: " + oriLeft + " oriRight: " + oriRight + " oriTop: " + oriTop + " oriBottom: " + oriBottom);
                Toast.makeText(getContext(), dragDirection.name(), Toast.LENGTH_SHORT).show();
                break;
            case MotionEvent.ACTION_UP:
                //      Log.d(TAG, "onTouchEvent: up");
//                spotLT = false;
//                spotT = false;
//                spotRT = false;
//                spotR = false;
//                spotRB = false;
//                spotB = false;
//                spotLB = false;
//                spotL = false;
//                requestLayout();
                // invalidate();
                break;
//            case MotionEvent.ACTION_CANCEL:
//                Log.d(TAG, "onTouchEvent: cancel");
//                spotL = false;
//                spotT = false;
//                spotR = false;
//                spotB = false;
//                invalidate();
//                break;
            case MotionEvent.ACTION_MOVE:
                // Log.d(TAG, "onTouchEvent: move");
                int tempRawX = (int) event.getRawX();
                int tempRawY = (int) event.getRawY();

                int dx = tempRawX - lastX;
                int dy = tempRawY - lastY;
                lastX = tempRawX;
                lastY = tempRawY;

                switch (dragDirection) {
                    case LEFT:
                        left(dx);
                        break;
                    case RIGHT:
                        right(dx);
                        break;
                    case BOTTOM:
                        bottom(dy);
                        break;
                    case TOP:
                        top(dy);
                        break;
                    case CENTER:
                        center(dx, dy);
                        break;
                    case LEFT_BOTTOM:
                        left(dx);
                        bottom(dy);
                        break;
                    case LEFT_TOP:
                        left(dx);
                        top(dy);
                        break;
                    case RIGHT_BOTTOM:
                        right(dx);
                        bottom(dy);
                        break;
                    case RIGHT_TOP:
                        right(dx);
                        top(dy);
                        break;
                }

                //new pos l t r b is set into oriLeft, oriTop, oriRight, oriBottom
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(oriRight - oriLeft, oriBottom - oriTop);
                lp.setMargins(oriLeft, oriTop, 0, 0);
                setLayoutParams(lp);
                //   Log.d(TAG, "onTouchEvent: set layout width="+(oriRight - oriLeft)+" height="+(oriBottom - oriTop));
                //   Log.d(TAG, "onTouchEvent: marginLeft="+oriLeft+"  marginTop"+oriTop);
                break;
        }
        return true;
    }

    private void center(int dx, int dy) {
        int left = getLeft() + dx;
        int top = getTop() + dy;
        int right = getRight() + dx;
        int bottom = getBottom() + dy;

        if (left < 0) {
            left = 0;
            right = left + getWidth();
        }
        if (right > screenWidth) {
            right = screenWidth;
            left = right - getWidth();
        }
        if (top < 0) {
            top = 0;
            bottom = top + getHeight();
        }
        if (bottom > screenHeight) {
            bottom = screenHeight;
            top = bottom - getHeight();
        }

        oriLeft = left;
        oriTop = top;
        oriRight = right;
        oriBottom = bottom;
    }

    private void top(int dy) {
        oriTop += dy;
        if (oriTop < 0) {
            oriTop = 0;
        }
        if (oriBottom - oriTop < minHeight) {
            oriTop = oriBottom - minHeight;
        }
    }

    private void bottom(int dy) {

        oriBottom += dy;
        if (oriBottom > screenHeight) {
            oriBottom = screenHeight;
        }
        if (oriBottom - oriTop < minHeight) {
            oriBottom = minHeight + oriTop;
        }
    }

    private void right(int dx) {
        oriRight += dx;
        if (oriRight > screenWidth) {
            oriRight = screenWidth;
        }
        if (oriRight - oriLeft < minWidth) {
            oriRight = oriLeft + minWidth;
        }
    }

    private void left(int dx) {
        oriLeft += dx;
        if (oriLeft < 0) {
            oriLeft = 0;
        }
        if (oriRight - oriLeft < minWidth) {
            oriLeft = oriRight - minWidth;
        }
    }

    private DIRECTION getDirection(int x, int y) {
        int left = getLeft();
        int right = getRight();
        int bottom = getBottom();
        int top = getTop();

        if (x < touchAreaLength && y < touchAreaLength) {
//            spotLT = true;
            return DIRECTION.LEFT_TOP;
        }
        if (y < touchAreaLength && right - left - x < touchAreaLength) {
//            spotRT = true;
            return DIRECTION.RIGHT_TOP;
        }
        if (x < touchAreaLength && bottom - top - y < touchAreaLength) {
//            spotLB = true;
            return DIRECTION.LEFT_BOTTOM;
        }
        if (right - left - x < touchAreaLength && bottom - top - y < touchAreaLength) {
//            spotRB = true;
            return DIRECTION.RIGHT_BOTTOM;
        }
        if (mFixedSize) {
            return DIRECTION.CENTER;
        }

        if (x < touchAreaLength) {
//            spotL = true;
            requestLayout();
            return DIRECTION.LEFT;
        }
        if (y < touchAreaLength) {
//            spotT = true;
            requestLayout();
            return DIRECTION.TOP;
        }
        if (right - left - x < touchAreaLength) {
//            spotR = true;
            requestLayout();
            return DIRECTION.RIGHT;
        }
        if (bottom - top - y < touchAreaLength) {
//            spotB = true;
            requestLayout();
            return DIRECTION.BOTTOM;
        }
        return DIRECTION.CENTER;
    }
}