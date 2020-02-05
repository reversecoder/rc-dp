package com.rc.designpattern.shapes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by enrique on 04/08/14.
 */
public class CompoundShape implements Shape {

    private Paint myColor = new Paint();
    private float width;
    // Display dimensions
    private int mDisplayWidth, mDisplayHeight;
    RelativeLayout mFrame;

    private List<Shape> children = new ArrayList<>();

    public CompoundShape(Shape... components) {
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
    public void draw(RelativeLayout mFrame, final float x, final float y, Context aContext) {
        this.mFrame = mFrame;
        mDisplayHeight = mFrame.getHeight();
        mDisplayWidth = mFrame.getWidth();

        CompoundView compoundView = new CompoundView(aContext,x,y);
        mFrame.addView(compoundView);
    }

    @Override
    public void setColor(int aColor) {
        this.myColor.setColor(aColor);
    }

    @Override
    public void setWidth(int width) {
        this.width = (float) width;
    }


    private class CompoundView extends View implements ShapeView {

        private float mXPos, mYPos, mDx, mDy;
//        private long mRotate, mDRotate;
//        private ScheduledFuture<?> mMoverFuture;


        public CompoundView(Context context, float x, float y) {
            super(context);
            this.mXPos = x;
            this.mYPos = y;
//            Random r = new Random();
//
//            setSpeedAndDirection(r);

        }
//
//        public synchronized boolean intersects(float x, float y) {
//
//            // TODO - Return true if the BubbleView intersects position (x,y)
//
//            if(x>mXPos && x<mXPos+width*2 && y>mYPos && y<mYPos+width*2)
//                return true;
//
//            return false;
//        }

        @Override
        protected synchronized void onDraw(Canvas canvas) {
//            canvas.drawCircle(mXPos, mYPos, width / 2, myColor);
            //Log.i("xd","Creating Circle at: x:" + x + "xpos" + xPos + " y:"+y+"ypos" + yPos);

            for (Shape child : children) {
                child.draw(mFrame, mXPos, mYPos, getContext());
            }
        }
//        @Override
//        public void setSpeedAndDirection(Random r) {
//            // Used by test cases
//
//            // TODO - Set movement direction and speed
//            // Limit movement speed in the x and y
//            // direction to [-3..3].
//            int max = 3;
//            int min = -3;
//            mDx = (r.nextInt(max-min+1)+min);
//            mDy = (r.nextInt(max-min+1)+min);
//
//
//        }
//        // Start moving the BubbleView & updating the display
//        private void start() {
//            // Creates a WorkerThread
//            ScheduledExecutorService executor = Executors
//                    .newScheduledThreadPool(1);
//
//            // Execute the run() in Worker Thread every REFRESH_RATE
//            // milliseconds
//            // Save reference to this job in mMoverFuture
//            mMoverFuture = executor.scheduleWithFixedDelay(new Runnable() {
//                @Override
//                public void run() {
//                    // TODO - implement movement logic.
//                    // Each time this method is run the BubbleView should
//                    // move one step. If the BubbleView exits the display,
//                    // stop the BubbleView's Worker Thread.
//                    // Otherwise, request that the BubbleView be redrawn.
//
//                    //mFrame.removeView(BubbleView.this);
//                    if(moveWhileOnScreen())
//                        postInvalidate();
//                    else
//                        stop(false);
//
//
//
//                }
//            }, 0, REFRESH_RATE, TimeUnit.MILLISECONDS);
//
//        }
//
//        private void stop(boolean popped) {
//            if (null != mMoverFuture && mMoverFuture.cancel(true)) {
//
//                // This work will be performed on the UI Thread
//
//                mFrame.post(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        // TODO - Remove the BubbleView from mFrame
//
//                        mFrame.removeView(CircleView.this);
//
//                        //log("Bubble removed from com.rc.designpattern.view!");
//
//                    }
//                });
//            }
//
//        }
//
//        private boolean moveWhileOnScreen() {
//            mXPos += mDx;
//            mYPos += mDy;
//            if(isOutOfView()) return false;
//
//            return true;
//        }
//
//        private boolean isOutOfView() {
//
//            // TODO - Return true if the BubbleView has exited the screen
//
//            if(mXPos > mDisplayWidth || mXPos+width <0)
//                return true;
//            if(mYPos > mDisplayHeight || mYPos+width <0)
//                return true;
//            return false;
//
//        }
//        @Override
//        public void deflect(float velocityX, float velocityY) {
//            mDx = velocityX/REFRESH_RATE;
//            mDy = velocityY/REFRESH_RATE;
//        }
    }
}
