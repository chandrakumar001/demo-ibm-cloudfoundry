package com.example.demo.exception;

import java.time.LocalDateTime;

public class DateHelper {

    public static String getDatetimeStamp() {
        return LocalDateTime.now().toString();
    }
}
