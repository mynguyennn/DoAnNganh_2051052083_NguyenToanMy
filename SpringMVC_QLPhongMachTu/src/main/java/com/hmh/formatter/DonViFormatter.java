/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.formatter;

import com.hmh.pojo.DichVu;
import com.hmh.pojo.DonviThuoc;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author Asus
 */
public class DonViFormatter implements Formatter<DonviThuoc>{
    @Override
    public String print(DonviThuoc dv, Locale locale) {
        return String.valueOf(dv.getIddonVi());
    }

    @Override
    public DonviThuoc parse(String id, Locale locale) throws ParseException {
        return new DonviThuoc(Integer.parseInt(id));
    }
}
