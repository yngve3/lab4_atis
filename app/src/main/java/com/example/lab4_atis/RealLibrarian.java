package com.example.lab4_atis;


import com.example.lab4_atis.models.Book;
import com.example.lab4_atis.models.BookInsert;

import java.util.List;

public class RealLibrarian implements Librarian {
    private String name;
    private Library library;

    public RealLibrarian(String name, Library library) {
        this.name = name;
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
    public Book getBookForCustomer(String author, String name) {
        return library.findBook(author, name);
    }

    @Override
    public void writeInCard(Book book, BookInsert bookInsert) {
        book.setBookInsert(bookInsert);
    }

    @Override
    public void giveBook(Book book) {
        library.addBook(book);
    }

    @Override
    public void writeOff(Book book) {
        System.out.println("Списать книгу");
    }

    @Override
    public int issueFine(boolean isLost) {
        if (isLost) return 1000;
        else return 200;
    }
}
