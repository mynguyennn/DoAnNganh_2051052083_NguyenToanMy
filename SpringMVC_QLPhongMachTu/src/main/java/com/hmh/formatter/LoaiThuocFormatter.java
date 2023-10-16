/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.formatter;

import com.hmh.pojo.DonviThuoc;
import com.hmh.pojo.LoaiThuoc;
import com.hmh.pojo.TaiKhoan;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author Asus
 */
public class LoaiThuocFormatter implements Formatter<LoaiThuoc> {

    @Override
    public String print(LoaiThuoc lt, Locale locale) {
        return String.valueOf(lt.getIdloaiThuoc());
    }

    @Override
    public LoaiThuoc parse(String id, Locale locale) throws ParseException {
        return new LoaiThuoc(Integer.parseInt(id));
    }
}
