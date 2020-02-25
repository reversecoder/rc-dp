package com.rc.designpattern.command;

public interface Command {

    void doIt();

    void undoIt();

    String whoAmI();
}