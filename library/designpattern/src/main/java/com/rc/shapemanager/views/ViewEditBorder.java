//package com.rc.shapemanager.views;
//
//import android.content.Context;
//import android.util.AttributeSet;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.RelativeLayout;
//
//import com.rc.shapemanager.R;
//
///**
// * Created by void on 2017/2/20.
// */
//
//public class ViewEditBorder extends RelativeLayout {
//    /********************
//     * Move layout start *
//     *********************/
//    private static final String TAG = "ViewEditBorder";
//    private int dragDirection = 0;
//    private static final int TOP = 0x15;
//    private static final int LEFT = 0x16;
//    private static final int BOTTOM = 0x17;
//    private static final int RIGHT = 0x18;
//    private static final int LEFT_TOP = 0x11;
//    private static final int RIGHT_TOP = 0x12;
//    private static final int LEFT_BOTTOM = 0x13;
//    private static final int RIGHT_BOTTOM = 0x14;
//    private static final int CENTER = 0x19;
//
//    private int lastX;
//    private int lastY;
//
//    private int screenWidth;
//    private int screenHeight;
//
//    private int oriLeft;
//    private int oriRight;
//    private int oriTop;
//    private int oriBottom;
//
//    private int touchAreaLength = 60;
//
//    private int minHeight = 120;
//    private int minWidth = 120;
//
//    private boolean mFixedSize = false;
//
//    private boolean spotL = false;
//    private boolean spotT = false;
//    private boolean spotR = false;
//    private boolean spotB = false;
//
//    private int getDirection(int x, int y) {
//        int left = getLeft();
//        int right = getRight();
//        int bottom = getBottom();
//        int top = getTop();
//
//        if (x < touchAreaLength && y < touchAreaLength) {
//            return LEFT_TOP;
//        }
//        if (y < touchAreaLength && right - left - x < touchAreaLength) {
//            return RIGHT_TOP;
//        }
//        if (x < touchAreaLength && bottom - top - y < touchAreaLength) {
//            return LEFT_BOTTOM;
//        }
//        if (right - left - x < touchAreaLength && bottom - top - y < touchAreaLength) {
//            return RIGHT_BOTTOM;
//        }
//        if (mFixedSize) {
//            return CENTER;
//        }
//
//        if (x < touchAreaLength) {
//            spotL = true;
//            requestLayout();
//            return LEFT;
//        }
//        if (y < touchAreaLength) {
//            spotT = true;
//            requestLayout();
//            return TOP;
//        }
//        if (right - left - x < touchAreaLength) {
//            spotR = true;
//            requestLayout();
//            return RIGHT;
//        }
//        if (bottom - top - y < touchAreaLength) {
//            spotB = true;
//            requestLayout();
//            return BOTTOM;
//        }
//        return CENTER;
//    }
//
//    /**
//     * 触摸点为中心->>移动
//     */
//    private void center(int dx, int dy) {
//        int left = getLeft() + dx;
//        int top = getTop() + dy;
//        int right = getRight() + dx;
//        int bottom = getBottom() + dy;
//
//        if (left < 0) {
//            left = 0;
//            right = left + getWidth();
//        }
//        if (right > screenWidth) {
//            right = screenWidth;
//            left = right - getWidth();
//        }
//        if (top < 0) {
//            top = 0;
//            bottom = top + getHeight();
//        }
//        if (bottom > screenHeight) {
//            bottom = screenHeight;
//            top = bottom - getHeight();
//        }
//
//        oriLeft = left;
//        oriTop = top;
//        oriRight = right;
//        oriBottom = bottom;
//    }
//
//    /**
//     * 触摸点为上边缘
//     */
//    private void top(int dy) {
//        oriTop += dy;
//        if (oriTop < 0) {
//            oriTop = 0;
//        }
//        if (oriBottom - oriTop < minHeight) {
//            oriTop = oriBottom - minHeight;
//        }
//    }
//
//    /**
//     * 触摸点为下边缘
//     */
//    private void bottom(int dy) {
//
//        oriBottom += dy;
//        if (oriBottom > screenHeight) {
//            oriBottom = screenHeight;
//        }
//        if (oriBottom - oriTop < minHeight) {
//            oriBottom = minHeight + oriTop;
//        }
//    }
//
//    /**
//     * 触摸点为右边缘
//     */
//    private void right(int dx) {
//        oriRight += dx;
//        if (oriRight > screenWidth) {
//            oriRight = screenWidth;
//        }
//        if (oriRight - oriLeft < minWidth) {
//            oriRight = oriLeft + minWidth;
//        }
//    }
//
//    /**
//     * 触摸点为左边缘
//     */
//    private void left(int dx) {
//        oriLeft += dx;
//        if (oriLeft < 0) {
//            oriLeft = 0;
//        }
//        if (oriRight - oriLeft < minWidth) {
//            oriLeft = oriRight - minWidth;
//        }
//    }
//
//    /*******************
//     * Move layout end *
//     *******************/
//
//    private View mOverlayView;
//    private View mAnchor, mParent;
//    private int mExtraWidth;
//    private int mExtraHeight;
//
//    public ViewEditBorder(Context context) {
//        this(context, null);
//    }
//
//    public ViewEditBorder(Context context, AttributeSet attrs) {
//        this(context, attrs, 0);
//    }
//
//    public ViewEditBorder(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        mOverlayView = LayoutInflater.from(context).inflate(R.layout.layout_editable_border_controller, this, true);
//    }
//
//    public void show(View parent, View anchor) {
//        mAnchor = anchor;
//        mParent = parent;
//        measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
//        mExtraWidth = findViewById(R.id.iv1).getMeasuredWidth();
//        mExtraHeight = findViewById(R.id.iv1).getMeasuredWidth();
//
//        findViewById(R.id.iv1).setOnTouchListener(new OnLeftTopTouchListener());
////        findViewById(R.id.iv2).setOnTouchListener(new OnRightTopTouchListener());
////        findViewById(R.id.iv3).setOnTouchListener(new OnLeftBottomTouchListener());
////        findViewById(R.id.iv4).setOnTouchListener(new OnRightBottomTouchListener());
//        setOnTouchListener(new OnRootTouchListener());
//
//        // Set param
//        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) anchor.getLayoutParams();
//        layoutParams.width = layoutParams.width + mExtraWidth;
//        layoutParams.height = layoutParams.height + mExtraHeight;
//        layoutParams.setMargins(layoutParams.leftMargin - mExtraWidth / 2, layoutParams.topMargin - mExtraWidth / 2, 0, 0);
//        setLayoutParams(layoutParams);
//
//        // Attach view with parent
//        ((ViewGroup) parent).addView(this);
//
////        if (getParent() == null) {
////             Log.d(TAG, "show()>>: parent null");
////            ((ViewGroup) anchor.getParent()).addView(this);
////        }
////        if (getParent() != anchor.getParent()) {
////            Log.d(TAG, "show()>>: parent and anchor parent are not same");
////           ((ViewGroup) getParent()).removeView(this);
////           ((ViewGroup) anchor.getParent()).addView(this);
////        }
//
//        // Move layout
//        screenHeight = ((ViewGroup) parent).getHeight();//getResources().getDisplayMetrics().heightPixels - 40;
//        screenWidth = ((ViewGroup) parent).getWidth();// getResources().getDisplayMetrics().widthPixels;
//    }
//
//    class OnRootTouchListener implements OnTouchListener {
//        float originX;
//        float originY;
//
//        int originLeftMargin;
//        int originTopMargin;
//
//        @Override
//        public boolean onTouch(View view, MotionEvent event) {
//            switch (event.getAction()) {
//                case MotionEvent.ACTION_DOWN: {
//                    originX = event.getRawX();
//                    originY = event.getRawY();
//                    MarginLayoutParams layoutParams = (MarginLayoutParams) view.getLayoutParams();
//                    originLeftMargin = layoutParams.leftMargin;
//                    originTopMargin = layoutParams.topMargin;
//                    return true;
//                }
//                case MotionEvent.ACTION_MOVE: {
//                    float deltaX = event.getRawX() - originX;
//                    float deltaY = event.getRawY() - originY;
//
//                    // 忽略小范围移动
//                    if (Math.abs(deltaX) < 5 && Math.abs(deltaY) < 5) {
//                        return false;
//                    }
//
//                    //refresh view layout
//                    MarginLayoutParams layoutParams = (MarginLayoutParams) view.getLayoutParams();
//                    layoutParams.leftMargin = (int) (originLeftMargin + deltaX);
//                    layoutParams.topMargin = (int) (originTopMargin + deltaY);
//                    view.setLayoutParams(layoutParams);
//
//                    //refresh anchor layout
//                    MarginLayoutParams anchorLayoutParams = (MarginLayoutParams) mAnchor.getLayoutParams();
//                    anchorLayoutParams.leftMargin = layoutParams.leftMargin + mExtraWidth / 2;
//                    anchorLayoutParams.topMargin = layoutParams.topMargin + mExtraHeight / 2;
//                    mAnchor.setLayoutParams(anchorLayoutParams);
//                    return true;
//                }
//                case MotionEvent.ACTION_UP: {
//                    return true;
//                }
//            }
//            return false;
//        }
//    }
//
//    /**
//     * 左上角点击监听
//     */
//    class OnLeftTopTouchListener implements OnTouchListener {
//
//        int originWidth = 0, originHeight = 0;
//
//        @Override
//        public boolean onTouch(View view, MotionEvent event) {
//            int action = event.getAction();
//            switch (action) {
//                case MotionEvent.ACTION_DOWN:
//                    //   Log.d(TAG, "onTouchEvent: down height="+ getHeight());
//                    oriLeft = getLeft();
//                    oriRight = getRight();
//                    oriTop = getTop();
//                    oriBottom = getBottom();
//
//                    lastY = (int) event.getRawY();
//                    lastX = (int) event.getRawX();
//
//                    originWidth = mAnchor.getMeasuredWidth();
//                    originHeight = mAnchor.getMeasuredHeight();
//
////                    dragDirection = getDirection((int) event.getX(), (int) event.getY());
//
//                    return true;
//                case MotionEvent.ACTION_UP:
//                    //      Log.d(TAG, "onTouchEvent: up");
//                    spotL = false;
//                    spotT = false;
//                    spotR = false;
//                    spotB = false;
//                    requestLayout();
////                    mDeleteView.setVisibility(View.INVISIBLE);
//                    // invalidate();
//
//                    return true;
////            case MotionEvent.ACTION_CANCEL:
////                Log.d(TAG, "onTouchEvent: cancel");
////                spotL = false;
////                spotT = false;
////                spotR = false;
////                spotB = false;
////                invalidate();
////                break;
//                case MotionEvent.ACTION_MOVE:
//                    // Log.d(TAG, "onTouchEvent: move");
//                    int tempRawX = (int) event.getRawX();
//                    int tempRawY = (int) event.getRawY();
//
//                    int dx = tempRawX - lastX;
//                    int dy = tempRawY - lastY;
//                    lastX = tempRawX;
//                    lastY = tempRawY;
//
//                    left(dx);
//                    top(dy);
//
////                    switch (dragDirection) {
////                        case LEFT:
////                            left(dx);
////                            break;
////                        case RIGHT:
////                            right(dx);
////                            break;
////                        case BOTTOM:
////                            bottom(dy);
////                            break;
////                        case TOP:
////                            top(dy);
////                            break;
////                        case CENTER:
////                            center(dx, dy);
////                            break;
////                        case LEFT_BOTTOM:
////                            left(dx);
////                            bottom(dy);
////                            break;
////                        case LEFT_TOP:
////                            left(dx);
////                            top(dy);
////                            break;
////                        case RIGHT_BOTTOM:
////                            right(dx);
////                            bottom(dy);
////                            break;
////                        case RIGHT_TOP:
////                            right(dx);
////                            top(dy);
////                            break;
////                    }
//
//                    if (Math.abs(dx) < 5 && Math.abs(dy) < 5) {
//                        return false;
//                    }
//
//                    //new pos l t r b is set into oriLeft, oriTop, oriRight, oriBottom
//                    RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(oriRight - oriLeft, oriBottom - oriTop);
//                    lp.setMargins(oriLeft, oriTop, 0, 0);
//                    mAnchor.setLayoutParams(lp);
//                    setLayoutParams(lp);
//
////                    setLayoutParams(lp);
//                    //   Log.d(TAG, "onTouchEvent: set layout width="+(oriRight - oriLeft)+" height="+(oriBottom - oriTop));
//                    //   Log.d(TAG, "onTouchEvent: marginLeft="+oriLeft+"  marginTop"+oriTop);
//
//
//                    //refresh anchor layout
////                    ViewGroup.LayoutParams anchorLayoutParams = mAnchor.getLayoutParams();
////                    anchorLayoutParams.width = (int) (oriRight - oriLeft + dx);
////                    anchorLayoutParams.height = (int) (oriBottom - oriTop + dy);
////                    mAnchor.setLayoutParams(lp);
//
//                    //refresh controller layout
////                    ViewGroup.LayoutParams layoutParams =lp;
////                    layoutParams.width = layoutParams.width + mExtraWidth;
////                    layoutParams.height = layoutParams.height + mExtraHeight;
////                    setLayoutParams(layoutParams);
//
//
//                    return true;
//            }
//            return false;
//        }
//    }
//
//    /**
//     * 右上角点击监听
//     */
//    class OnRightTopTouchListener implements OnTouchListener {
//
//        @Override
//        public boolean onTouch(View view, MotionEvent event) {
//            return false;
//
//        }
//    }
//
//    /**
//     * 左下角点击监听
//     */
//    class OnLeftBottomTouchListener implements OnTouchListener {
//
//        @Override
//        public boolean onTouch(View view, MotionEvent event) {
//            return false;
//
//        }
//    }
//
//    /**
//     * 右下角点击监听
//     */
//    class OnRightBottomTouchListener implements OnTouchListener {
//
//        float originX;
//        float originY;
//
//        int originWidth;
//        int originHeight;
//
//        @Override
//        public boolean onTouch(View view, MotionEvent event) {
//            switch (event.getAction()) {
//                case MotionEvent.ACTION_DOWN:
//                    originX = event.getRawX();
//                    originY = event.getRawY();
//                    originWidth = mAnchor.getMeasuredWidth();
//                    originHeight = mAnchor.getMeasuredHeight();
//                    return true;
//                case MotionEvent.ACTION_MOVE:
//                    float deltaX = event.getRawX() - originX;
//                    float deltaY = event.getRawY() - originY;
//
//                    // 忽略小范围移动
//                    if (Math.abs(deltaX) < 5 && Math.abs(deltaY) < 5) {
//                        return false;
//                    }
//
//                    //refresh anchor layout
//                    ViewGroup.LayoutParams anchorLayoutParams = mAnchor.getLayoutParams();
//                    anchorLayoutParams.width = (int) (originWidth + deltaX);
//                    anchorLayoutParams.height = (int) (originHeight + deltaY);
//                    mAnchor.setLayoutParams(anchorLayoutParams);
//
//                    //refresh controller layout
//                    ViewGroup.LayoutParams layoutParams = getLayoutParams();
//                    layoutParams.width = anchorLayoutParams.width + mExtraWidth;
//                    layoutParams.height = anchorLayoutParams.height + mExtraHeight;
//                    setLayoutParams(layoutParams);
//
//                    return true;
//                case MotionEvent.ACTION_UP:
//                    return true;
//            }
//            return false;
//        }
//    }
//
//}