///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.hmh.formatter;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Locale;
//import org.springframework.format.Formatter;
//
///**
// *
// * @author Asus
// */
//public class DateFormatter implements Formatter<Date> {
//
//    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//
//    @Override
//    public String print(Date date, Locale locale) {
//        SimpleDateFormat displayFormat = new SimpleDateFormat("dd-MM-yyyy");
//        return displayFormat.format(date);
//    }
//
//    @Override
//    public Date parse(String text, Locale locale) throws ParseException {
//        try {
//            return dateFormat.parse(text);
//        } catch (ParseException ex) {
//            throw new IllegalArgumentException("Invalid date format. Please use yyyy-MM-dd.", ex);
//        }
//    }
//
//}
