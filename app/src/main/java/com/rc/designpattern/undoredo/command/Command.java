package com.rc.designpattern.undoredo.command;

public interface Command {

    void doIt();

    void undoIt();

    String whoAmI();
}