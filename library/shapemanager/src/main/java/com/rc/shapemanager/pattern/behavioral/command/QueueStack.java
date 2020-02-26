package com.rc.shapemanager.pattern.behavioral.command;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public class QueueStack<T> {

    private List<T> dataCollection;

    public QueueStack() {
        dataCollection = new LinkedList<>();
    }

    public void push(T item) {
        dataCollection.add(0, item);
    }

    public T pop() {
        if (dataCollection.size() > 0)
            return dataCollection.remove(dataCollection.size() - 1);
        else
            return null;
    }

    public void clear() {
        dataCollection.clear();
    }
}