package com.example.lab4_atis.models;

import java.io.Serializable;
import java.util.List;

public class Request implements Serializable {
    private final String ticketNumber;
    private final boolean isReturn;
    private final Book book;

    public Request(String ticketNumber, boolean isReturn, Book book) {
        this.ticketNumber = ticketNumber;
        this.isReturn = isReturn;
        this.book = book;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public boolean isReturn() {
        return isReturn;
    }

    public Book getBook() {
        return book;
    }
}
