package com.rc.designpattern.pattern.behavioural.command;

public interface Command {

    void doIt();

    void undoIt();

    String whoAmI();
}