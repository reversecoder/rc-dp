package com.rc.designpattern.behavioural.command;

public interface Command {

    void doIt();

    void undoIt();

    String whoAmI();
}