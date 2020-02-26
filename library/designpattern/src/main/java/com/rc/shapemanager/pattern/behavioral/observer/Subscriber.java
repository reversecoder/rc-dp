package com.rc.shapemanager.pattern.behavioral.observer;

/**
 * This is Observer
 *
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public interface Subscriber<T> {
    void updateSubscriber(T item);
}