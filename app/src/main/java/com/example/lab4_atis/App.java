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
        fillData();

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

    private void fillData() {
        BookInsert insert = new BookInsert.BookInsertBuilder()
                .setDate("12/06/2023")
                .setDeadline("15/06/2023")
                .setTicketNumber("123-GH")
                .build();

        BookCard card = new BookCard.BookCardBuilder()
                .setName("Детство")
                .setBookShelf("Толстой Л.Н.")
                .setCount(32)
                .setLibraryDepartment("Художественный")
                .build();

        library.addBook(new Book(insert, card));

        insert = new BookInsert.BookInsertBuilder()
                .setDate("12/06/2023")
                .setDeadline("15/06/2023")
                .setTicketNumber("123-GH")
                .build();

        card = new BookCard.BookCardBuilder()
                .setName("Отрочество")
                .setBookShelf("Толстой Л.Н.")
                .setCount(34)
                .setLibraryDepartment("Художественный")
                .build();

        library.addBook(new Book(insert, card));

        insert = new BookInsert.BookInsertBuilder()
                .setDate("12/06/2023")
                .setDeadline("15/06/2023")
                .setTicketNumber("123-GH")
                .build();

        card = new BookCard.BookCardBuilder()
                .setName("Юность")
                .setBookShelf("Толстой Л.Н.")
                .setCount(25)
                .setLibraryDepartment("Художественный")
                .build();

        library.addBook(new Book(insert, card));

        insert = new BookInsert.BookInsertBuilder()
                .setDate("12/06/2023")
                .setDeadline("15/06/2023")
                .setTicketNumber("123-GH")
                .build();

        card = new BookCard.BookCardBuilder()
                .setName("Война и мир")
                .setBookShelf("Толстой Л.Н.")
                .setCount(1)
                .setLibraryDepartment("Художественный")
                .build();

        library.addBook(new Book(insert, card));

    }
}
