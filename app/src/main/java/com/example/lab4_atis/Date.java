package com.example.lab4_atis;

public class Date {
    public static final String NOW_DATE = "14/06/2023";
    public static final String DEAD_LINE_DATE = "28/05/2023";

    public static int conversionDate(String date) {
        String[] dateMass = date.split("/");
        return Integer.parseInt(dateMass[2]) * 365
                + Integer.parseInt(dateMass[1]) * 30
                + Integer.parseInt(dateMass[0]);
    }

    public static boolean isAfterDeadline() {
        return conversionDate(NOW_DATE) > conversionDate(DEAD_LINE_DATE);
    }
}
