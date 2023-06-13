package com.example.lab4_atis.logic;

import com.example.lab4_atis.models.Book;

import java.util.List;

public interface Customer {
    List<Book> viewBooks();

    void getBookFromLibrary(String author, String name);

    void getBookFromLibrary(Book book);

    Book returnBook(Book book);

    void payFine();

    void addFineToCount(int fineCount);

    int getFine();
}
