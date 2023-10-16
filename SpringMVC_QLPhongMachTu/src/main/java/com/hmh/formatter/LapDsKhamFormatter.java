/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.formatter;

import com.hmh.pojo.TaiKhoan;
import com.hmh.pojo.UserRole;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author Asus
 */
public class LapDsKhamFormatter implements Formatter<TaiKhoan>{

    @Override
    public TaiKhoan parse(String text, Locale locale) throws ParseException {
        return new TaiKhoan(Integer.parseInt(text));
    }

    @Override
    public String print(TaiKhoan tk, Locale locale) {
        return String.valueOf(tk.getIdTk());
    }
    
}
