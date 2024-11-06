package com.example.tekushop.common;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTime {
    public static LocalDate convertStringToLocalDate(String dateTimeString) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


        LocalDateTime localDateTime = LocalDateTime.parse(dateTimeString, formatter);


        return localDateTime.toLocalDate();
    }
}
