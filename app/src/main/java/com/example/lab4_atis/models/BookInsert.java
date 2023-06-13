package com.example.lab4_atis.models;

import java.io.Serializable;

/**
 * Класс Вкладыш
 * Хранит инфомрацию о дате выдач книги, сроке и номер читательского
 * Реализует гетеры и сетеры для полей
 */
public class BookInsert implements Serializable {
    private String date; // Дата выдачи книги
    private String deadline; // Срок выдачи книги
    private String ticketNumber; // Номер читательского билета
    private boolean isLost;

    public BookInsert(BookInsertBuilder builder) {
        date = builder.date;
        deadline = builder.deadline;
        ticketNumber = builder.ticketNumber;
    }

    public String getDate() {
        return date;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public boolean isLost() {
        return isLost;
    }

    public void setLost(boolean lost) {
        isLost = lost;
    }

    @Override
    public String toString() {
        return "{" + date + " " + deadline + " " + ticketNumber + "}";
    }

    public static class BookInsertBuilder implements Serializable {
        private String date; // Дата выдачи книги
        private String deadline; // Срок выдачи книги
        private String ticketNumber; // Номер читательского билета

        private boolean isLost;

        public BookInsertBuilder setDate(String date) {
            this.date = date;
            return this;
        }

        public BookInsertBuilder setDeadline(String deadline) {
            this.deadline = deadline;
            return this;
        }

        public BookInsertBuilder setTicketNumber(String ticketNumber) {
            this.ticketNumber = ticketNumber;
            return this;
        }

        public BookInsertBuilder setLost(boolean isLost) {
            this.isLost = isLost;
            return this;
        }

        public BookInsert build() {
            return new BookInsert(this);
        }

    }
}
