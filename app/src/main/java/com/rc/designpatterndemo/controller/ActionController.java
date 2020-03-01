package com.rc.designpatterndemo.controller;

import android.app.Activity;
import android.view.ViewGroup;

import com.rc.designpattern.pattern.behavioural.state.ActionType;

public class ActionController {

    private Dispatcher dispatcher;

    public ActionController() {
        dispatcher = new Dispatcher();
    }

    public void dispatchRequest(Activity activity, ActionType actionType, ViewGroup parentView) {
        dispatcher.dispatch(activity, actionType, parentView);
    }
}