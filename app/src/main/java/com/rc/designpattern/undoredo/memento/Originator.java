package com.rc.designpattern.undoredo.memento;

public interface Originator<T> {
	Memento<T> saveToMemento();
	
	void restoreFromMemento(Memento<T> memento);
	
	T getState();
}