package com.example.lab4_atis;


import com.example.lab4_atis.models.Book;
import com.example.lab4_atis.models.BookInsert;

import java.util.List;

public interface Librarian {
    List<Book> viewBooks();

    Book getBookForCustomer(String author, String name);

    void writeInCard(Book book, BookInsert bookInsert);

    void giveBook(Book book);

    void writeOff(Book book);

    int issueFine(boolean isLost);
}
