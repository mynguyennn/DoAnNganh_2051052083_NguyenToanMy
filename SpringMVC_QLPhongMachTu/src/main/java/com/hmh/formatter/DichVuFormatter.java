/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.formatter;

import com.hmh.pojo.DichVu;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author Asus
 */
public class DichVuFormatter implements Formatter<DichVu> {

    @Override
    public String print(DichVu dv, Locale locale) {
        return String.valueOf(dv.getIdDv());
    }

    @Override
    public DichVu parse(String id, Locale locale) throws ParseException {
        return new DichVu(Integer.parseInt(id));
    }
}
