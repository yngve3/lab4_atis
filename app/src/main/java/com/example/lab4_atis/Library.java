package com.example.lab4_atis;


import com.example.lab4_atis.models.Book;
import com.example.lab4_atis.models.BookCard;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library implements Subject {
    private ArrayList<Book> books = new ArrayList<>();
    private final ArrayList<Observer> observers = new ArrayList<>();

    public ArrayList<Book> getBooks() {
        return books;
    }
    public List<Book> getBooks(Departments department) {
        return books.stream().filter(book -> book.getBookCard().getLibraryDepartment() == department).collect(Collectors.toList());
    }

    public Book findBook(String author, String name){
        Book book = null;
        for (Book b : books) {
            BookCard bookCard = b.getBookCard();
            if(bookCard.getBookShelf().equals(author) && bookCard.getName().equals(name)) book = b;
        }
        return book;
    }

    public void addBook(Book book) {
        books.add(book);
        notifyObserver();
    }

    public void removeBook(Book book) {
        books.remove(book);
        notifyObserver();
    }

    @Override
    public void attachObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detachObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for (Observer observer : observers) {
            observer.update(books);
        }
    }
}
