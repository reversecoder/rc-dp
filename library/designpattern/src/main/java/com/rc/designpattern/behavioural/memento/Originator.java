package com.rc.designpattern.behavioural.memento;

public interface Originator<T> {

    Memento<T> saveToMemento();

    void restoreFromMemento(Memento<T> memento);

    T getState();
}