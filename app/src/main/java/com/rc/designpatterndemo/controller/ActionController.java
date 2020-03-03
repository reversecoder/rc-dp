package com.rc.designpatterndemo.controller;

import android.app.Activity;
import android.view.ViewGroup;

import com.rc.designpattern.pattern.behavioural.state.MenuType;

/**
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public class ActionController {

    private Dispatcher dispatcher;

    public ActionController() {
        dispatcher = new Dispatcher();
    }

    public void dispatchRequest(Activity activity, MenuType menuType, ViewGroup parentView) {
        dispatcher.dispatch(activity, menuType, parentView);
    }
}