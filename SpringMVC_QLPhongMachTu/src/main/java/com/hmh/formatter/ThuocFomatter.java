/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.formatter;

import com.hmh.pojo.Thuoc;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author Asus
 */
public class ThuocFomatter implements Formatter<Thuoc> {

    @Override
    public String print(Thuoc object, Locale locale) {
        return String.valueOf(object.getIdThuoc());
    }

    @Override
    public Thuoc parse(String text, Locale locale) throws ParseException {
        return new Thuoc(Integer.parseInt(text));
    }

}
