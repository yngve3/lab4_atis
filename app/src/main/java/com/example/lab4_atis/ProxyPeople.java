package com.example.lab4_atis;

import com.example.lab4_atis.models.Book;

import java.util.ArrayList;
import java.util.List;

public class ProxyPeople implements Observer, Customer, Librarian {
    private RealCustomer customer = null;
    private RealLibrarian librarian = null;
    private boolean authorization = false;

    private String name;
    private String ticketNumber;
    private Book book;

    private Library library;

    public ProxyPeople(String name, String ticketNumber, Library library) {
        this.name = name;
        this.ticketNumber = ticketNumber;
        this.book = null;
        this.library = library;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void authorizeCustomer() {
        if (customer == null) {
            customer = new RealCustomer(name, ticketNumber, library);
            authorization = true;
            System.out.println("Авторизация пользователя прошла успешно.");
        } else {
            System.out.println("Пользователь уже авторизован.");
        }
    }

    public void authorizeLibrarian() {
        if (librarian == null) {
            librarian = new RealLibrarian(name, library);
            authorization = true;
            System.out.println("Авторизация библиотекаря прошла успешно.");
        } else {
            System.out.println("Библиотекарь уже авторизован.");
        }
    }

    public List<Book> getCustomerBookList() {
        if (customer != null && authorization) return customer.getBooks();
        else System.out.println("Авторизуйтесь как пользователь");
        return null;
    }

    @Override
    public void update(ArrayList<Book> books) {
        if (customer != null) System.out.println("Dear " + customer.getName() + ", we have some changes in library");
        else if (librarian != null)
            System.out.println("Dear " + librarian.getName() + ", we have some changes in library");
        else System.out.println("Пожалуйста авторизуйтесь для получения уведомлений");
    }

    @Override
    public List<Book> viewBooks() {
        return library.getBooks();
    }

    @Override
    public void getBookFromLibrary(String author, String name) {
        if (customer != null && authorization) customer.getBookFromLibrary(author, name);
        else System.out.println("Авторизуйтесь как пользователь");
    }

    @Override
    public void getBookFromLibrary(Book book) {
        if (customer != null && authorization) customer.getBookFromLibrary(book);
        else System.out.println("Авторизуйтесь как пользователь");
    }

    @Override
    public Book getBookForCustomer(String author, String name) {
        if (librarian != null && authorization) return librarian.getBookForCustomer(author, name);
        else System.out.println("Авторизуйтесь как библиотекарь");
        return null;
    }

    @Override
    public void writeInCard(Book book) {
        if (librarian != null && authorization) librarian.writeInCard(book);
        else System.out.println("Авторизуйтесь как библиотекарь");
    }

    @Override
    public void giveBook(Book book) {
        if (librarian != null && authorization) librarian.giveBook(book);
        else System.out.println("Авторизуйтесь как библиотекарь");
    }

    @Override
    public Book returnBook(Book book) {
        if (customer != null && authorization) customer.returnBook(book);
        else System.out.println("Авторизуйтесь как пользователь");
        return null;
    }

    @Override
    public void writeOff(Book book) {
        if (librarian != null && authorization) librarian.writeOff(book);
        else System.out.println("Авторизуйтесь как библиотекарь");
    }

    @Override
    public void issueFine() {
        if (librarian != null && authorization) librarian.issueFine();
        else System.out.println("Авторизуйтесь как библиотекарь");
    }

    @Override
    public void payFine() {
        if (customer != null && authorization) customer.payFine();
        else System.out.println("Авторизуйтесь как пользователь");
    }
}
