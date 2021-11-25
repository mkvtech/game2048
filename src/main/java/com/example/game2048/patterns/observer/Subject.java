package com.example.game2048.patterns.observer;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private final List<Observer> observers = new ArrayList<>();

    public Subject() {}

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void update() {
        observers.forEach(Observer::update);
    }
}
