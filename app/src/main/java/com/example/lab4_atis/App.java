package com.example.lab4_atis;

import android.app.Application;

import com.example.lab4_atis.models.Book;
import com.example.lab4_atis.models.BookCard;
import com.example.lab4_atis.models.BookInsert;
import com.example.lab4_atis.models.Request;

import java.util.ArrayList;
import java.util.List;

public class App extends Application {

    private static App instance;
    private Library library;
    private ProxyPeople people;

    public static App getInstance() {
        return instance;
    }

    private boolean isWorker = false;

    public Library getLibrary() {
        return library;
    }

    public ProxyPeople getPeople() {
        return people;
    }

    private List<Request> requests;

    @Override
    public void onCreate() {
        super.onCreate();
        library = new Library();
        people = new ProxyPeople("Ivan", "123-GH", library);
        instance = this;

        library.attachObserver(people);
        FillData.fill(library);

        requests = new ArrayList<>();
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setWorker(boolean worker) {
        isWorker = worker;
    }

    public boolean isWorker() {
        return isWorker;
    }
}
