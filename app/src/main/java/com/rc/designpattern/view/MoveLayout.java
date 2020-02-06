package com.rc.designpattern.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.rc.designpattern.gesture.TouchGestureDetector;
import com.rc.designpattern.util.Util;

/**
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public class MoveLayout extends RelativeLayout {

    private int dragDirection = 0;
    private static final int TOP = 0x15;
    private static final int LEFT = 0x16;
    private static final int BOTTOM = 0x17;
    private static final int RIGHT = 0x18;
    private static final int LEFT_TOP = 0x11;
    private static final int RIGHT_TOP = 0x12;
    private static final int LEFT_BOTTOM = 0x13;
    private static final int RIGHT_BOTTOM = 0x14;
    private static final int CENTER = 0x19;
    private int lastX;
    private int lastY;
    private int screenWidth;
    private int screenHeight;
    private int oriLeft;
    private int oriRight;
    private int oriTop;
    private int oriBottom;

//    private boolean spotLT = false;
//    private boolean spotT = false;
//    private boolean spotRT = false;
//    private boolean spotR = false;
//    private boolean spotRB = false;
//    private boolean spotB = false;
//    private boolean spotLB = false;
//    private boolean spotL = false;

    private int identity = 0;
    private int touchAreaLength = 60;
    private int minHeight = 120;
    private int minWidth = 180;
    private static final String TAG = "MoveLinearLayout";
    private boolean isMoveToDelete = false;
    private boolean isViewSelected = false;
    private View draggableView;

    public MoveLayout(Context context) {
        super(context);
        init();
    }

    public MoveLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MoveLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        screenHeight = getResources().getDisplayMetrics().heightPixels;
        screenWidth = getResources().getDisplayMetrics().widthPixels;
    }

    public void setViewWidthHeight(int width, int height) {
        screenWidth = width;
        screenHeight = height;
    }

    public void setMinHeight(int height) {
        minHeight = height;
        if (minHeight < touchAreaLength * 2) {
            minHeight = touchAreaLength * 2;
        }
    }

    public void setMinWidth(int width) {
        minWidth = width;
        if (minWidth < touchAreaLength * 3) {
            minWidth = touchAreaLength * 3;
        }
    }

    private boolean mFixedSize = false;

    public void setFixedSize(boolean b) {
        mFixedSize = b;
    }

    private int mDeleteHeight = 0;
    private int mDeleteWidth = 0;
    private boolean isInDeleteArea = false;

    public void setDeleteWidthHeight(int width, int height) {
        mDeleteWidth = screenWidth - width;
        mDeleteHeight = height;
    }

    public void setViewSelected(boolean viewSelected) {
        this.isViewSelected = viewSelected;
    }

    public void setDraggableView(View draggableView) {
        if (draggableView != null) {
            this.draggableView = draggableView;
        }
    }

    public boolean isViewSelected() {
        return isViewSelected;
    }

//    public void updateSelectorView() {
//        if (draggableView != null) {
//            if (isViewSelected()) {
//                draggableView.findViewById(R.id.content_view).setBackgroundResource(R.drawable.shape_editable_border_green);
//                draggableView.findViewById(R.id.change_bg).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color_selected));
//
//                draggableView.findViewById(R.id.spotLT).setVisibility(VISIBLE);
//                draggableView.findViewById(R.id.spotT).setVisibility(VISIBLE);
//                draggableView.findViewById(R.id.spotRT).setVisibility(VISIBLE);
//                draggableView.findViewById(R.id.spotR).setVisibility(VISIBLE);
//                draggableView.findViewById(R.id.spotRB).setVisibility(VISIBLE);
//                draggableView.findViewById(R.id.spotB).setVisibility(VISIBLE);
//                draggableView.findViewById(R.id.spotLB).setVisibility(VISIBLE);
//                draggableView.findViewById(R.id.spotL).setVisibility(VISIBLE);
//            } else {
//                draggableView.findViewById(R.id.content_view).setBackgroundResource(R.drawable.shape_editable_border_transparent);
//                draggableView.findViewById(R.id.change_bg).setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color_transparent));
//
//                draggableView.findViewById(R.id.spotLT).setVisibility(INVISIBLE);
//                draggableView.findViewById(R.id.spotT).setVisibility(INVISIBLE);
//                draggableView.findViewById(R.id.spotRT).setVisibility(INVISIBLE);
//                draggableView.findViewById(R.id.spotR).setVisibility(INVISIBLE);
//                draggableView.findViewById(R.id.spotRB).setVisibility(INVISIBLE);
//                draggableView.findViewById(R.id.spotB).setVisibility(INVISIBLE);
//                draggableView.findViewById(R.id.spotLB).setVisibility(INVISIBLE);
//                draggableView.findViewById(R.id.spotL).setVisibility(INVISIBLE);
//            }
//        }
//    }

    TouchGestureDetector gestureDetector = new TouchGestureDetector(new TouchGestureDetector.TouchGestureListener() {
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
            Util.doVibrate(getContext(), 100);
            setViewSelected(!isViewSelected());
            requestLayout();
        }

        @Override
        public void onMultiTap(MotionEvent motionEvent, int clicks) {

        }
    });

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        int childViewId = ((ShapeView) ((LinearLayout) draggableView.findViewById(R.id.add_your_view_here)).getChildAt(0)).getTopicValue().getIdentity();
//        Log.d("cloneid", "cloneid>>move layout touch: " + childViewId);
        if (isViewSelected) {
            int action = event.getAction();
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    //   Log.d(TAG, "onTouchEvent: down height="+ getHeight());
                    oriLeft = getLeft();
                    oriRight = getRight();
                    oriTop = getTop();
                    oriBottom = getBottom();

                    lastY = (int) event.getRawY();
                    lastX = (int) event.getRawX();
                    dragDirection = getDirection((int) event.getX(), (int) event.getY());
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
                    LayoutParams lp = new LayoutParams(oriRight - oriLeft, oriBottom - oriTop);
                    lp.setMargins(oriLeft, oriTop, 0, 0);
                    setLayoutParams(lp);
                    //   Log.d(TAG, "onTouchEvent: set layout width="+(oriRight - oriLeft)+" height="+(oriBottom - oriTop));
                    //   Log.d(TAG, "onTouchEvent: marginLeft="+oriLeft+"  marginTop"+oriTop);
                    break;
            }
        }

        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

//        RelativeLayout rlt = (RelativeLayout) getChildAt(0);
//        int count = rlt.getChildCount();
//        Log.d(TAG, "ViewSelection>>onLayout>>Child count: " + count);
//        for (int a = 0; a < count; a++) {
//            Log.d(TAG, "ViewSelection>>onLayout>>Child " + a + " is: " + rlt.getChildAt(a).getClass().toString());
//        }
//        updateSelectorView();















//        if (isLongPressUnderExecution) {
//            RelativeLayout rlt = (RelativeLayout) getChildAt(0);
//            int count = rlt.getChildCount();
//            Log.d(TAG, "ViewSelection>>onLayout>>Child count: " + count);
//            for (int a = 0; a < count; a++) {
//                Log.d(TAG, "ViewSelection>>onLayout>>Child " + a + " is: " + rlt.getChildAt(a).getClass().toString());
//                switch (a) {
//                    case 1:
//                    case 2:
//                    case 3:
//                    case 4:
//                    case 5:
//                    case 6:
//                    case 7:
//                    case 8:
//                        int childVisibility = isViewSelected() ? View.VISIBLE : View.INVISIBLE;
//                        Log.d(TAG, "ViewSelection>>onLayout>>Child visibility: " + childVisibility);
//                        rlt.getChildAt(a).setVisibility(childVisibility);
//                        break;
//                }
//            }
//            isLongPressUnderExecution = false;
//        }

//         Log.d(TAG, "onLayout: "+rlt.getChildAt(a).getClass().toString());
//        for (int a = 0; a < count; a++) {
//            if (a == 1) {        //l
//                if (spotLT)
//                    rlt.getChildAt(a).setVisibility(View.VISIBLE);
//                else
//                    rlt.getChildAt(a).setVisibility(View.INVISIBLE);
//            } else if (a == 2) { //t
//                if (spotT)
//                    rlt.getChildAt(a).setVisibility(View.VISIBLE);
//                else
//                    rlt.getChildAt(a).setVisibility(View.INVISIBLE);
//            } else if (a == 3) { //r
//                if (spotRT)
//                    rlt.getChildAt(a).setVisibility(View.VISIBLE);
//                else
//                    rlt.getChildAt(a).setVisibility(View.INVISIBLE);
//            } else if (a == 4) { //b
//                if (spotR)
//                    rlt.getChildAt(a).setVisibility(View.VISIBLE);
//                else
//                    rlt.getChildAt(a).setVisibility(View.INVISIBLE);
//            } else if (a == 5) { //b
//                if (spotRB)
//                    rlt.getChildAt(a).setVisibility(View.VISIBLE);
//                else
//                    rlt.getChildAt(a).setVisibility(View.INVISIBLE);
//            } else if (a == 6) { //b
//                if (spotB)
//                    rlt.getChildAt(a).setVisibility(View.VISIBLE);
//                else
//                    rlt.getChildAt(a).setVisibility(View.INVISIBLE);
//            } else if (a == 7) { //b
//                if (spotLB)
//                    rlt.getChildAt(a).setVisibility(View.VISIBLE);
//                else
//                    rlt.getChildAt(a).setVisibility(View.INVISIBLE);
//            } else if (a == 8) { //b
//                if (spotL)
//                    rlt.getChildAt(a).setVisibility(View.VISIBLE);
//                else
//                    rlt.getChildAt(a).setVisibility(View.INVISIBLE);
//            }
        // Log.d(TAG, "onLayout: "+rlt.getChildAt(a).getClass().toString());
//        }
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

    private int getDirection(int x, int y) {
        int left = getLeft();
        int right = getRight();
        int bottom = getBottom();
        int top = getTop();

        if (x < touchAreaLength && y < touchAreaLength) {
//            spotLT = true;
            return LEFT_TOP;
        }
        if (y < touchAreaLength && right - left - x < touchAreaLength) {
//            spotRT = true;
            return RIGHT_TOP;
        }
        if (x < touchAreaLength && bottom - top - y < touchAreaLength) {
//            spotLB = true;
            return LEFT_BOTTOM;
        }
        if (right - left - x < touchAreaLength && bottom - top - y < touchAreaLength) {
//            spotRB = true;
            return RIGHT_BOTTOM;
        }
        if (mFixedSize) {
            return CENTER;
        }

        if (x < touchAreaLength) {
//            spotL = true;
            requestLayout();
            return LEFT;
        }
        if (y < touchAreaLength) {
//            spotT = true;
            requestLayout();
            return TOP;
        }
        if (right - left - x < touchAreaLength) {
//            spotR = true;
            requestLayout();
            return RIGHT;
        }
        if (bottom - top - y < touchAreaLength) {
//            spotB = true;
            requestLayout();
            return BOTTOM;
        }
        return CENTER;
    }

    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }
}
