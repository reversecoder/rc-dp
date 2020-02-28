package com.rc.designpattern.pattern.structural.decorator.enumeration;

/**
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public enum Color {

    RED(android.graphics.Color.RED),
    GREEN(android.graphics.Color.GREEN),
    BLUE(android.graphics.Color.BLUE),
    YELLOW(android.graphics.Color.YELLOW),
    WHITE(android.graphics.Color.WHITE),
    BLACK(android.graphics.Color.BLACK),
    MAGENTA(android.graphics.Color.MAGENTA),
    CYAN(android.graphics.Color.CYAN);

    private int value;

    Color(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}