package com.mhm.action.observer;

import java.util.Vector;

/**
 * 被观察者
 * @author MHm
 * @date 2020-4-19 20:56
 */
public abstract class PropertiesSubject {
    private Vector<Observer> observers = new Vector<Observer>();
    public void addObserver(Observer observer){
        this.observers.add(observer);
    }
    public void deleteObserver(Observer observer){
        this.observers.remove(observer);
    }

    /**
     * 通知所有观察者
     */
    public void notifyObservers(){
        for (Observer observer:observers){
            observer.update();
        }
    }
}
