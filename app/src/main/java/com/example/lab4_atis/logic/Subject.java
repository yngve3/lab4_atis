package com.example.lab4_atis.logic;

public interface Subject {
    void attachObserver(Observer observer);

    void detachObserver(Observer observer);

    void notifyObserver();
}
