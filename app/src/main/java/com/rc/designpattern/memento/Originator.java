package com.rc.designpattern.memento;

import android.view.View;

/**
 * Created by enrique on 08/08/14.
 */
public class Originator  {

    View mViewState; //state

    public void setState(View mViewState){
        this.mViewState = mViewState;
    }
    public View getState(){
        return mViewState;
    }
    public Memento save2Memento(){
        return  new Memento(mViewState);
    }
    public void getStateFromMemento(Memento Memento){
        mViewState = Memento.getState();
    }
}
