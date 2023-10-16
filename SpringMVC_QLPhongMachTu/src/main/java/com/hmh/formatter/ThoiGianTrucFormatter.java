/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.formatter;


import com.hmh.pojo.ThoiGianTruc;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author LENOVO
 */
public class ThoiGianTrucFormatter implements Formatter<ThoiGianTruc> {

    @Override
    public String print(ThoiGianTruc tgTruc, Locale locale) {
         return  String.valueOf(tgTruc.getIdtgTruc());
    }

    @Override
    public ThoiGianTruc parse(String text, Locale locale) throws ParseException {
       return new ThoiGianTruc(Integer.parseInt(text));
    }
}
