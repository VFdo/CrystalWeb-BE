package com.groupp.crystalweb.common;

public abstract class DateFormats {
    public static final String LOCAL_TIME = "HH:mm";
    public static final String LOCAL_DATE = "dd-MM-yyyy";
    public static final String LOCAL_DATE_TIME = LOCAL_DATE + " " +LOCAL_TIME;

    private DateFormats() {
        throw new IllegalStateException("DateFormats class");
    }
}