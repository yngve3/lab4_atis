package com.example.lab4_atis;

import com.example.lab4_atis.models.Book;
import com.example.lab4_atis.models.BookCard;

public class FillData {
    public static void fill(Library library) {
        fillClassic(library);
        fillFantastic(library);
        fillDetectives(library);
    }

    private static void fillClassic(Library library) {
        BookCard card = new BookCard.BookCardBuilder()
                .setName("Детство")
                .setBookShelf("Толстой Л.Н.")
                .setCount(32)
                .setLibraryDepartment(Departments.CLASSIC)
                .build();

        library.addBook(new Book(card));

        card = new BookCard.BookCardBuilder()
                .setName("Отрочество")
                .setBookShelf("Толстой Л.Н.")
                .setCount(34)
                .setLibraryDepartment(Departments.CLASSIC)
                .build();

        library.addBook(new Book(card));

        card = new BookCard.BookCardBuilder()
                .setName("Юность")
                .setBookShelf("Толстой Л.Н.")
                .setCount(25)
                .setLibraryDepartment(Departments.CLASSIC)
                .build();

        library.addBook(new Book(card));

        card = new BookCard.BookCardBuilder()
                .setName("Война и мир")
                .setBookShelf("Толстой Л.Н.")
                .setCount(1)
                .setLibraryDepartment(Departments.CLASSIC)
                .build();

        library.addBook(new Book(card));
    }

    private static void fillFantastic(Library library) {
        BookCard card = new BookCard.BookCardBuilder()
                .setName("Я, Робот")
                .setBookShelf("Айзек Азимов")
                .setCount(23)
                .setLibraryDepartment(Departments.FANTASTIC)
                .build();

        library.addBook(new Book(card));

        card = new BookCard.BookCardBuilder()
                .setName("Конец Вечности")
                .setBookShelf("Айзек Азимов")
                .setCount(25)
                .setLibraryDepartment(Departments.FANTASTIC)
                .build();

        library.addBook(new Book(card));

        card = new BookCard.BookCardBuilder()
                .setName("Трудно быть Богом")
                .setBookShelf("Аркадий и Борис Стругацкие")
                .setCount(48)
                .setLibraryDepartment(Departments.FANTASTIC)
                .build();

        library.addBook(new Book(card));

        card = new BookCard.BookCardBuilder()
                .setName("Отель «У Погибшего Альпиниста»")
                .setBookShelf("Аркадий и Борис Стругацкие")
                .setCount(56)
                .setLibraryDepartment(Departments.FANTASTIC)
                .build();

        library.addBook(new Book(card));
    }

    private static void fillDetectives(Library library) {
        BookCard card = new BookCard.BookCardBuilder()
                .setName("Этюд в багровых тонах")
                .setBookShelf("Артур Конан Дойл")
                .setCount(47)
                .setLibraryDepartment(Departments.DETECTIVES)
                .build();

        library.addBook(new Book(card));

        card = new BookCard.BookCardBuilder()
                .setName("Собака Баскервилей")
                .setBookShelf("Артур Конан Дойл")
                .setCount(34)
                .setLibraryDepartment(Departments.DETECTIVES)
                .build();

        library.addBook(new Book(card));

        card = new BookCard.BookCardBuilder()
                .setName("Десять негритят")
                .setBookShelf("Агата Кристи")
                .setCount(51)
                .setLibraryDepartment(Departments.DETECTIVES)
                .build();

        library.addBook(new Book(card));

        card = new BookCard.BookCardBuilder()
                .setName("В 4:50 с вокзала Паддингтон")
                .setBookShelf("Агата Кристи")
                .setCount(39)
                .setLibraryDepartment(Departments.DETECTIVES)
                .build();

        library.addBook(new Book(card));
    }
}
