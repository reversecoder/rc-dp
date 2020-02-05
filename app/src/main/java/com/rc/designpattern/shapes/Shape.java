package com.rc.designpattern.shapes;

import android.content.Context;
import android.graphics.Paint;
import android.widget.RelativeLayout;

import java.io.Serializable;

public interface Shape extends Serializable {

    int getX();

    int getY();

    int getWidth();

    int getHeight();

    void drag();

    void drop();

    void moveTo(int x, int y);

    void moveBy(int x, int y);

    boolean isInsideBounds(int x, int y);

    int getColor();

    void select();

    void unSelect();

    boolean isSelected();

    void setColor(int color);

    void draw(RelativeLayout mFrame, Context aContext);
}