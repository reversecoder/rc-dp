package com.rc.designpattern.shapes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.rc.designpattern.gesture.TouchGestureDetector;
import com.rc.designpattern.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by enrique on 04/08/14.
 */
public class CompoundShape extends BaseShape {

    private List<Shape> children = new ArrayList<>();

    public CompoundShape(Shape... components) {
        super(0, 0, Color.BLACK);
        add(components);
    }

    public void add(Shape component) {
        children.add(component);
    }

    public void add(Shape... components) {
        children.addAll(Arrays.asList(components));
    }

    public void remove(Shape child) {
        children.remove(child);
    }

    public void remove(Shape... components) {
        children.removeAll(Arrays.asList(components));
    }

    public void clear() {
        children.clear();
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
    public void drag() {
        for (Shape child : children) {
            child.drag();
        }
    }

    @Override
    public void drop() {
        for (Shape child : children) {
            child.drop();
        }
    }

    @Override
    public void moveTo(int x, int y) {
        for (Shape child : children) {
            child.moveTo(x, y);
        }
    }

    @Override
    public void moveBy(int x, int y) {
        for (Shape child : children) {
            child.moveBy(x, y);
        }
    }

    @Override
    public boolean isInsideBounds(int x, int y) {
        for (Shape child : children) {
            if (child.isInsideBounds(x, y)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setShapeColor(int color) {
        super.setShapeColor(color);
        for (Shape child : children) {
            child.setShapeColor(color);
        }
    }

    @Override
    public void unSelect() {
        super.unSelect();
        for (Shape child : children) {
            child.unSelect();
        }
    }

    public Shape getChildAt(int x, int y) {
        for (Shape child : children) {
            if (child.isInsideBounds(x, y)) {
                return child;
            }
        }
        return null;
    }

    public boolean selectChildAt(int x, int y) {
        Shape child = getChildAt(x, y);
        if (child != null) {
            child.select();
            return true;
        }
        return false;
    }

    public List<Shape> getSelected() {
        List<Shape> selected = new ArrayList<>();
        for (Shape child : children) {
            if (child.isSelected()) {
                selected.add(child);
            }
        }
        return selected;
    }

    @Override
    public void drawShape(RelativeLayout frame, Context context) {
        super.drawShape(frame, context);

        CompoundView compoundView = new CompoundView(context);
        frame.addView(compoundView);
    }

    private class CompoundView extends View implements ShapeView {

        public CompoundView(Context context) {
            super(context);

//            setOnTouchListener(new OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                    gestureDetector.onTouchEvent(event);
//                    return false;
//                }
//            });
        }

        @Override
        protected synchronized void onDraw(Canvas canvas) {
            if (isSelected()) {
                enableSelectionStyle();
                canvas.drawRect(getShapeX() - 1, getShapeY() - 1, getShapeWidth() + 1, getShapeHeight() + 1, borderPaint);
                disableSelectionStyle();
            }

            for (Shape child : children) {
                child.drawShape(frame, getContext());
            }
        }

//        TouchGestureDetector gestureDetector = new TouchGestureDetector(new TouchGestureDetector.TouchGestureListener() {
//            @Override
//            public void onPress(MotionEvent motionEvent) {
//
//            }
//
//            @Override
//            public void onTap(MotionEvent motionEvent) {
//
//            }
//
//            @Override
//            public void onDrag(MotionEvent motionEvent) {
//
//            }
//
//            @Override
//            public void onMove(MotionEvent motionEvent) {
//
//            }
//
//            @Override
//            public void onRelease(MotionEvent motionEvent) {
//
//            }
//
//            @Override
//            public void onLongPress(MotionEvent motionEvent) {
//                Util.doVibrate(getContext(), 100);
//            }
//
//            @Override
//            public void onMultiTap(MotionEvent motionEvent, int clicks) {
//
//            }
//        });
    }
}