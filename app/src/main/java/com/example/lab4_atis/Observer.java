package com.example.lab4_atis;

import com.example.lab4_atis.models.Book;

import java.util.ArrayList;

public interface Observer {
    void update(ArrayList<Book> books);
}
