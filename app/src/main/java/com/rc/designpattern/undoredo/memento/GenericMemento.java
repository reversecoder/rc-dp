package com.rc.designpattern.undoredo.memento;

public class GenericMemento<T> implements Memento<T> {

	private final T state;
	public boolean undone;
	
	public GenericMemento(T state) {
		super();
		this.state = state;
		this.undone = false;
	}
	
	@Override
	public T getState() {
		return state;
	}

	public boolean isUndone() {
		return undone;
	}

	public void switchUndone(){
		undone = !undone;
	}
}