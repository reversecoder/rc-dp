package com.rc.designpattern.pattern.controller;

import android.content.Context;
import android.view.ViewGroup;

import com.rc.designpattern.pattern.behavioural.state.ActionType;

public class ActionController {

    private Dispatcher dispatcher;

    public ActionController() {
        dispatcher = new Dispatcher();
    }

    public void dispatchRequest(Context context, ActionType actionType, ViewGroup parentView) {
        dispatcher.dispatch(context, actionType, parentView);
    }
}