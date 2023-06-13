package com.example.lab4_atis;

import com.example.lab4_atis.models.Book;

import java.util.ArrayList;
import java.util.List;

public class RealCustomer implements Customer {
    private String name;
    private String ticketNumber;
    private List<Book> books;
    private Library library;

    public RealCustomer(String name, String ticketNumber, Library library) {
        this.name = name;
        this.ticketNumber = ticketNumber;
        books = new ArrayList<>();
        this.library = library;
    }

    public String getName() {
        return name;
    }

    @Override
    public List<Book> viewBooks() {
        return library.getBooks();
    }

    @Override
    public void getBookFromLibrary(String author, String name) {}

    @Override
    public void getBookFromLibrary(Book book) {
        books.add(book);
    }

    public List<Book> getBooks() {
        if (books == null) books = new ArrayList<>();
        return books;
    }

    @Override
    public Book returnBook(Book book) {
        return books.remove(books.indexOf(book));
    }

    @Override
    public void payFine() {
        System.out.println("Оплатить штраф");
    }
}
