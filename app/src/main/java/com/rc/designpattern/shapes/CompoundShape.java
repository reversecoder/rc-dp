package com.rc.designpattern.shapes;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.rc.designpattern.R;
import com.rc.designpattern.gesture.TouchGestureDetector;
import com.rc.designpattern.state.ShapeState;
import com.rc.designpattern.undoredo.command.CommandExecutor;
import com.rc.designpattern.undoredo.command.UpdateShapeCommand;
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
    private List<Shape> children = new ArrayList<>();
    private View editableView;

    public CompoundShape(Context context, Shape... components) {
        super(context);
        setWillNotDraw(false);
        // Add component views
        add(components);
        // Fixed screen size
        screenHeight = getResources().getDisplayMetrics().heightPixels;
        screenWidth = getResources().getDisplayMetrics().widthPixels;

        // Draw views
        drawViews();
    }

    public void add(Shape component) {
        children.add(component);
//        addView(((View) component));
//        ((View) component).setOnTouchListener(null);
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
    public void refreshView() {
        for (Shape child : children) {
            child.refreshView();
        }

        if (editableView != null) {
            editableView.setVisibility((isShapeSelected()) ? VISIBLE : GONE);
        }

        invalidate();
    }

    //    @Override
//    public void unselectShape() {
//        for (Shape child : children) {
//            child.unselectShape();
//        }
//
//        refreshView();
//    }
//
//    @Override
//    public void selectShape() {
//        for (Shape child : children) {
//            child.selectShape();
//        }
//
//        refreshView();
//    }
//
    @Override
    public boolean isShapeSelected() {
        for (Shape child : children) {
            if (!child.isShapeSelected()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void setShapeState(ShapeState shapeState) {
        for (Shape child : children) {
            child.setShapeState(shapeState);
        }

//        UpdateShapeCommand previousState = new UpdateShapeCommand(this);
//        CommandExecutor.getInstance().executeCommand(previousState);

        refreshView();

//        UpdateShapeCommand updateState = new UpdateShapeCommand(this);
//        CommandExecutor.getInstance().executeCommand(updateState);
    }

    @Override
    public ShapeState getShapeState() {
        if (children.size() > 0) {
            return children.get(0).getShapeState();
        }
        return null;
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
//            child.selectShape();
//            return true;
//        }
//        return false;
//    }
//
//    public List<Shape> getSelected() {
//        List<Shape> selected = new ArrayList<>();
//        for (Shape child : children) {
//            if (child.isShapeSelected()) {
//                selected.add(child);
//            }
//        }
//        return selected;
//    }

    @Override
    public void drawShape(Canvas canvas) {
//        removeAllViews();
//        for (Shape child : children) {
////            ((View) child).draw(canvas);
//            addView(((View) child));
//        }

//        removeAllViews();
//        drawViews();
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
//            ((View) child).measure(size, size);
            ((View) child).measure(child.getShapeWidth(), child.getShapeHeight());
        }
        if (editableView != null) {
            editableView.measure(size, size);
        }
        // measure parent size
//        setMeasuredDimension(getShapeWidth() + 50, getShapeHeight() + 50);
        setMeasuredDimension(size, size);
    }

    private void drawViews() {
        for (Shape child : children) {
            addView(((View) child));
//            ((View) child).setOnTouchListener(null);
        }

        editableView = CustomViewManager.getChildView(getContext(), this, R.layout.layout_editable_border_controller);
//        editableView.setOnTouchListener(null);
        editableView.setVisibility((isShapeSelected()) ? VISIBLE : GONE);
    }

    @Override
    public void onLayout(boolean changed, int l, int t, int r, int b) {
//        for(int i = 0 ; i < getChildCount() ; i++){
//            getChildAt(i).layout(l, t, r, b);
//            View view = getChildAt(i);
//            Log.d(TAG, "onLayout()>>view>>" + view.getClass().getSimpleName() + " width: " + view.getLayoutParams().width + " height: " + view.getLayoutParams().height);
//
//            if(view.getVisibility() != GONE && view instanceof FrameLayout){
//                view.layout(l, t, r, b);
//            }
//        }

        for (Shape child : children) {
            int left = centerX - child.getShapeWidth() / 2;
            int top = centerY - child.getShapeHeight() / 2;
            int right = left + child.getShapeWidth();
            int bottom = top + child.getShapeHeight();

            View childView = (View) child;
            if (childView.getVisibility() != GONE) {
                Log.d(TAG, "onLayout()>>child>>" + child.getClass().getSimpleName() + " left: " + left + " top: " + top + " right: " + right + " bottom: " + bottom);
                childView.layout(left, top, right, bottom);
            }
        }

        if (editableView != null && editableView.getVisibility() != GONE) {
            int left = centerX - getMeasuredWidth() / 2;
            int top = centerY - getMeasuredHeight() / 2;
            int right = left + getMeasuredWidth();
            int bottom = top + getMeasuredHeight();

//            editableView.layout(
//                    14
//                    , 14
//                    , 186
//                    , 186
//            );
            editableView.layout(
                    left
                    , top
                    , right
                    , bottom
            );
        }

        Log.d(TAG, "onLayout()>>Inside onLayout()");
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        drawShape(canvas);
    }

    /***********************
     * Touch events
     *************************/
    public enum DIRECTION {TOP, LEFT, BOTTOM, RIGHT, LEFT_TOP, RIGHT_TOP, LEFT_BOTTOM, RIGHT_BOTTOM, CENTER}

    private DIRECTION dragDirection;
    private int lastX;
    private int lastY;
    private int screenWidth;
    private int screenHeight;
    private int oriLeft;
    private int oriRight;
    private int oriTop;
    private int oriBottom;
    private int touchAreaLength = 60;
    private int minHeight = 150;
    private int minWidth = 150;
    private boolean mFixedSize = false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        touchGestureDetector.onTouchEvent(event);
        if (isShapeSelected()) {
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
                    Log.d(TAG, "onTouchEvent(MotionEvent.ACTION_DOWN): oriLeft: " + oriLeft + " oriRight: " + oriRight + " oriTop: " + oriTop + " oriBottom: " + oriBottom);
                    Log.d(TAG, "onTouchEvent(MotionEvent.ACTION_DOWN): dragDirection: " + dragDirection);
                    Toast.makeText(getContext(), "" + dragDirection, Toast.LENGTH_SHORT).show();
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
                    Log.d(TAG, "onTouchEvent(MotionEvent.ACTION_MOVE): dx= " + dx + " dy= " + dy);

                    if (dragDirection != null) {
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

                        int finalWidth = oriRight - oriLeft;
                        int finalHeight = oriBottom - oriTop;

                        //new pos l t r b is set into oriLeft, oriTop, oriRight, oriBottom
                        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(oriRight - oriLeft, oriBottom - oriTop);
                        lp.setMargins(oriLeft, oriTop, 0, 0);

                        for (Shape child : children) {
                            if (child instanceof Circle) {
                                int circleRadius = Math.min(finalWidth / 2, finalHeight / 2);
                                Log.d(TAG, "onTouchEvent(MotionEvent.ACTION_MOVE): circleRadius= " + circleRadius);
                                ((Circle) child).setRadius(circleRadius);
//                        ((View) child).setLayoutParams(lp);
                            }
                        }

                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(lp);
                        if (editableView != null) {
                            editableView.setLayoutParams(layoutParams);
                        }
                        setLayoutParams(lp);
                        Log.d(TAG, "onTouchEvent(MotionEvent.ACTION_MOVE): finalWidth= " + finalWidth + " finalHeight= " + finalHeight);
                        Log.d(TAG, "onTouchEvent(MotionEvent.ACTION_MOVE): marginLeft= " + oriLeft + " marginTop= " + oriTop);
                    }
                    break;
            }

            return true;
        }
        return true;
    }

    TouchGestureDetector touchGestureDetector = new TouchGestureDetector(new TouchGestureDetector.TouchGestureListener() {
        @Override
        public void onPress(MotionEvent motionEvent) {

        }

        @Override
        public void onTap(MotionEvent motionEvent) {

        }

        @Override
        public void onDrag(MotionEvent motionEvent) {

        }

        @Override
        public void onMove(MotionEvent motionEvent) {

        }

        @Override
        public void onRelease(MotionEvent motionEvent) {

        }

        @Override
        public void onLongPress(MotionEvent motionEvent) {
            if (!isShapeSelected()) {
                Log.d(TAG, "touchGestureDetector>>onLongPress: ");
                CustomViewManager.doVibrate(getContext(), 100);
                setShapeState(ShapeState.SELECTED);
            }
        }

        @Override
        public void onMultiTap(MotionEvent motionEvent, int clicks) {

        }
    });

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