package com.example.lab4_atis.models;

import com.example.lab4_atis.Departments;

import java.io.Serializable;

/**
 * Класс Карточка
 * Хранит информацию о расположении книги и о количестве её экземпляров
 * Реализует гетеры полей класса
 */
public class BookCard implements Serializable {
    private String name; // Выходные данные
    private Departments libraryDepartment; // Отдел библиотеки
    private String bookShelf; // Полка на которой находится книга
    private int count;  // Количество экземпляров книги
    public void inc() {
        count++;
    }

    public void dec() {
        count--;
    }

    public BookCard(BookCardBuilder builder) {
        name = builder.name;
        libraryDepartment = builder.libraryDepartment;
        bookShelf = builder.bookShelf;
        count = builder.count;
    }

    public String getName() {
        return name;
    }

    public Departments getLibraryDepartment() {
        return libraryDepartment;
    }

    public String getBookShelf() {
        return bookShelf;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "{" + name + " " + libraryDepartment + " " + bookShelf + "}";
    }

    public static class BookCardBuilder implements Serializable{
        private String name; // Выходные данные
        private Departments libraryDepartment; // Отдел библиотеки
        private String bookShelf; // Полка на которой находится книга
        private int count;  // Количество экземпляров книги

        public BookCardBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public BookCardBuilder setLibraryDepartment(Departments libraryDepartment) {
            this.libraryDepartment = libraryDepartment;
            return this;
        }

        public BookCardBuilder setBookShelf(String bookShelf) {
            this.bookShelf = bookShelf;
            return this;
        }

        public BookCardBuilder setCount(int count) {
            this.count = count;
            return this;
        }

        public BookCard build() {
            return new BookCard(this);
        }
    }
}
