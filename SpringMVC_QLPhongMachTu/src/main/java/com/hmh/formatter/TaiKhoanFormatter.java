/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.formatter;

import com.hmh.pojo.TaiKhoan;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author LENOVO
 */
public class TaiKhoanFormatter implements Formatter<TaiKhoan>{

    @Override
    public String print(TaiKhoan tk, Locale locale) {
         return  String.valueOf(tk.getIdTk());
         }

    @Override
    public TaiKhoan parse(String text, Locale locale) throws ParseException {
       return new TaiKhoan(Integer.parseInt(text));
    }
    
}
