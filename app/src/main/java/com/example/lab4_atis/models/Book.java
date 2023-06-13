package com.example.lab4_atis.models;

import java.io.Serializable;

public class Book implements Serializable {
    private BookInsert bookInsert;
    private BookCard bookCard;

    public Book(BookInsert bookInsert, BookCard bookCard) {
        this.bookInsert = bookInsert;
        this.bookCard = bookCard;
    }

    public Book(BookCard bookCard) {
        this.bookCard = bookCard;
        this.bookInsert = null;
    }

    public BookInsert getBookInsert() {
        return bookInsert;
    }

    public BookCard getBookCard() {
        return bookCard;
    }

    public void setBookInsert(BookInsert bookInsert) {
        this.bookInsert = bookInsert;
    }

    public void setBookCard(BookCard bookCard) {
        this.bookCard = bookCard;
    }

    @Override
    public String toString() {
        return "[" + bookInsert + " " + bookCard + ']';
    }
}
