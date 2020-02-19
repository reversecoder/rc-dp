package com.rc.designpattern.undoredo.memento;

public interface Memento<T> {
	T getState();
}