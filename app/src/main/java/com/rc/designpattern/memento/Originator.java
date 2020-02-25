package com.rc.designpattern.memento;

public interface Originator<T> {

    Memento<T> saveToMemento();

    void restoreFromMemento(Memento<T> memento);

    T getState();
}