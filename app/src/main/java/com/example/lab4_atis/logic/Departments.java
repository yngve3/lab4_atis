package com.example.lab4_atis.logic;

import java.io.Serializable;

public enum Departments implements Serializable {
    CLASSIC("Классика"),
    FANTASTIC("Фантастика"),
    DETECTIVES("Дeтективы");

    private final String name;
    Departments(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
