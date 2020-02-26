package com.rc.shapemanager.pattern.behavioral.observer;

/**
 * This is Observable
 *
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public interface Publisher <T>{
    public void setValue(T value);
    boolean registerSubscriber(Subscriber subscriber);
    boolean removeSubscriber(Subscriber subscriber);
    public void notifySubscribers();
    public void notifySubscriber(Subscriber subscriber);
}