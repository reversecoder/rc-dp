package com.rc.designpattern.view;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.rc.designpattern.R;
import com.rc.designpattern.shapes.Shape;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public class DragLayout extends RelativeLayout {

    private static final String TAG = "DragView";
    private Context mContext;
    private List<Shape> mMoveLayoutList;
    private int mMinHeight = 120;
    private int mMinWidth = 180;
    private int mSelfViewWidth = 0;
    private int mSelfViewHeight = 0;
//    private int touchAreaLength = 60;
//    private boolean mFixedSize = false;
//    private DirectionType dragDirection = DirectionType.NONE;
//
//    private int lastX;
//    private int lastY;
//    private int oriLeft;
//    private int oriRight;
//    private int oriTop;
//    private int oriBottom;

    public DragLayout(Context context) {
        super(context);
        init(context);
    }

    public DragLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DragLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context c) {
        mContext = c;
        mMoveLayoutList = new ArrayList<>();
    }

    public List<Shape> getShapes() {
        return mMoveLayoutList;
    }

    //    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        //  Log.e(TAG, "onDraw: height=" + getHeight());
//        mSelfViewWidth = getWidth();
//        mSelfViewHeight = getHeight();
//
//        if (mMoveLayoutList != null) {
//            int count = mMoveLayoutList.size();
//            for (int a = 0; a < count; a++) {
//                mMoveLayoutList.get(a).setViewWidthHeight(mSelfViewWidth, mSelfViewHeight);
//            }
//        }
//
//    }

    public void addShapeView(final Shape shape, View shapeView) {
        // Create move layout
//        MoveLayout moveLayout = new MoveLayout(mContext);
//        moveLayout.setClickable(true);
//        moveLayout.setMinWidth(mMinWidth);
//        moveLayout.setMinHeight(mMinHeight);
//        int tempWidth = shapeView.getRight() - shapeView.getLeft();
//        int tempHeight = shapeView.getBottom() - shapeView.getTop();
//        if (tempWidth < mMinWidth) tempWidth = mMinWidth;
//        if (tempHeight < mMinHeight) tempHeight = mMinHeight;
//
//        //set position
//        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(tempWidth, tempHeight);
//        lp.setMargins(shapeView.getLeft(), shapeView.getTop(), 0, 0);
//        moveLayout.setLayoutParams(lp);
//
//        LayoutInflater inflater = LayoutInflater.from(mContext);
//        View dragSubView = inflater.inflate(R.layout.drag_sub_view, null);
//        LinearLayout addYourViewHere = (LinearLayout) dragSubView.findViewById(R.id.add_your_view_here);
//        LinearLayout.LayoutParams lv = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        addYourViewHere.addView(shapeView, lv);
//
//        // Add view
//        moveLayout.addView(dragSubView);
//        moveLayout.setDraggableView(dragSubView);
//        moveLayout.setViewSelected(true);
//        moveLayout.setFixedSize(true);
//        addView(moveLayout);
//        mMoveLayoutList.add(moveLayout);

        addView(shapeView);
        mMoveLayoutList.add(shape);

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                int x = shape.getShapeX()+50;
//                int y = shape.getShapeY()+50;
//                Log.d(TAG, "moved x: " +x+" y: "+y);
//                shape.moveBy(x, y);
//                invalidate();
//            }
//        }, 2000);
    }

    public void removeShapeView(View shapeView) {
        removeView(shapeView);
    }

//    public void setFixedSize(boolean fixedSize) {
//        this.mFixedSize = fixedSize;
//    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        touchGestureDetector.onTouchEvent(event);
//
//        int action = event.getAction();
//        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//                lastY = (int) event.getRawY();
//                lastX = (int) event.getRawX();
//
//                oriLeft = getLeft();
//                oriRight = getRight();
//                oriTop = getTop();
//                oriBottom = getBottom();
//
//                dragDirection = getDirection((int) event.getX(), (int) event.getY());
//                break;
//
//            case MotionEvent.ACTION_UP:
//                break;
//            case MotionEvent.ACTION_MOVE:
//                int tempRawX = (int) event.getRawX();
//                int tempRawY = (int) event.getRawY();
//
//                int dx = tempRawX - lastX;
//                int dy = tempRawY - lastY;
//                lastX = tempRawX;
//                lastY = tempRawY;
//
//                switch (dragDirection) {
//                    case LEFT:
//                        left(dx);
//                        break;
//                    case RIGHT:
//                        right(dx);
//                        break;
//                    case BOTTOM:
//                        bottom(dy);
//                        break;
//                    case TOP:
//                        top(dy);
//                        break;
//                    case CENTER:
//                        center(dx, dy);
//                        break;
//                    case LEFT_BOTTOM:
//                        left(dx);
//                        bottom(dy);
//                        break;
//                    case LEFT_TOP:
//                        left(dx);
//                        top(dy);
//                        break;
//                    case RIGHT_BOTTOM:
//                        right(dx);
//                        bottom(dy);
//                        break;
//                    case RIGHT_TOP:
//                        right(dx);
//                        top(dy);
//                        break;
//                }
//
//                //new pos l t r b is set into oriLeft, oriTop, oriRight, oriBottom
//                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(oriRight - oriLeft, oriBottom - oriTop);
//                lp.setMargins(oriLeft, oriTop, 0, 0);
//                setLayoutParams(lp);
//                //   Log.d(TAG, "onTouchEvent: set layout width="+(oriRight - oriLeft)+" height="+(oriBottom - oriTop));
//                //   Log.d(TAG, "onTouchEvent: marginLeft="+oriLeft+"  marginTop"+oriTop);
//                break;
//        }
//        return true;
//    }

//    private DirectionType getDirection(int x, int y) {
//        int left = getLeft();
//        int right = getRight();
//        int bottom = getBottom();
//        int top = getTop();
//
//        if (x < touchAreaLength && y < touchAreaLength) {
////            spotLT = true;
//            return DirectionType.LEFT_TOP;
//        }
//        if (y < touchAreaLength && right - left - x < touchAreaLength) {
////            spotRT = true;
//            return DirectionType.RIGHT_TOP;
//        }
//        if (x < touchAreaLength && bottom - top - y < touchAreaLength) {
////            spotLB = true;
//            return DirectionType.LEFT_BOTTOM;
//        }
//        if (right - left - x < touchAreaLength && bottom - top - y < touchAreaLength) {
////            spotRB = true;
//            return DirectionType.RIGHT_BOTTOM;
//        }
//        if (mFixedSize) {
//            return DirectionType.CENTER;
//        }
//
//        if (x < touchAreaLength) {
////            spotL = true;
//            requestLayout();
//            return DirectionType.LEFT;
//        }
//        if (y < touchAreaLength) {
////            spotT = true;
//            requestLayout();
//            return DirectionType.TOP;
//        }
//        if (right - left - x < touchAreaLength) {
////            spotR = true;
//            requestLayout();
//            return DirectionType.RIGHT;
//        }
//        if (bottom - top - y < touchAreaLength) {
////            spotB = true;
//            requestLayout();
//            return DirectionType.BOTTOM;
//        }
//        return DirectionType.CENTER;
//    }

//    TouchGestureDetector touchGestureDetector = new TouchGestureDetector(new TouchGestureDetector.TouchGestureListener() {
//        @Override
//        public void onPress(MotionEvent motionEvent) {
//            if(mMoveLayoutList.size()>0){
//                Shape shape = ( (CompoundShape)mMoveLayoutList.get(0)).getChildAt((int)motionEvent.getX(), (int)motionEvent.getY());
//                if(shape !=null){
//                    Log.d(TAG, "selected shape: " + shape.toString());
//                }
//            }
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
//
//        }
//
//        @Override
//        public void onMultiTap(MotionEvent motionEvent, int clicks) {
//
//        }
//    });
}
