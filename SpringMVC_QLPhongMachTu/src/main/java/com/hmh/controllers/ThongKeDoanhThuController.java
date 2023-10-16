/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.controllers;

import com.hmh.pojo.HoaDon;
import com.hmh.pojo.PhieuDangKy;
import com.hmh.service.ThongKeBenhNhanService;
import com.hmh.service.ThongKeDoanhThuService;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Asus
 */
@Controller
public class ThongKeDoanhThuController {

    @Autowired
    private ThongKeDoanhThuService thongKeDoanhThuService;

    @GetMapping("/admin/thongkedoanhthu")
    public String thongKeDoanhThu(Model model) {

        return "thongkedoanhthu";
    }

    @PostMapping("/admin/thongkedoanhthu")
    public String thongKeDoanhThuu(Model model, @RequestParam("year") int year,
             PhieuDangKy pdk) {

        long[] monthlyRevenues = new long[12];

//        long[] quyRevenues = new long[4];

        //doanh thu nam
        List<HoaDon> hoaDonList = this.thongKeDoanhThuService.loadHoaDon(year);

//        List<HoaDon> hdQuy = this.thongKeDoanhThuService.loadDsTheoQuy(year, month);
        for (HoaDon hoaDon : hoaDonList) {
            if (hoaDon.getNgayThanhToan() != null) {
                Timestamp timestamp = (Timestamp) hoaDon.getNgayThanhToan();
                Date ngayThanhToan = new Date(timestamp.getTime());
                int monthIndex = ngayThanhToan.getMonth();
                long revenue = hoaDon.getTienThuoc() + hoaDon.getTienDv() + hoaDon.getTienKham().getTienKham();
                monthlyRevenues[monthIndex] += revenue;
            }
        }

        long thang1 = monthlyRevenues[0];
        long thang2 = monthlyRevenues[1];
        long thang3 = monthlyRevenues[2];
        long thang4 = monthlyRevenues[3];
        long thang5 = monthlyRevenues[4];
        long thang6 = monthlyRevenues[5];
        long thang7 = monthlyRevenues[6];
        long thang8 = monthlyRevenues[7];
        long thang9 = monthlyRevenues[8];
        long thang10 = monthlyRevenues[9];
        long thang11 = monthlyRevenues[10];
        long thang12 = monthlyRevenues[11];

        //Doanh thu Quy
//        for (HoaDon hdQuys : hdQuy) {
//            if (hdQuys.getNgayThanhToan() != null) {
//                Timestamp timestamp = (Timestamp) hdQuys.getNgayThanhToan(); 
//                Date ngayThanhToan = new Date(timestamp.getTime());
//                int monthIndex = ngayThanhToan.getMonth();
//                long revenue = hdQuys.getTienThuoc() + hdQuys.getTienDv() + hdQuys.getTienKham().getTienKham();
//                monthlyRevenues[monthIndex] += revenue;
//            }
//        }
        long quy1 = thang1 + thang2 + thang3;
        long quy2 = thang4 + thang5 + thang6;
        long quy3 = thang7 + thang8 + thang9;
        long quy4 = thang10 + thang11 + thang12;

        model.addAttribute("year", year);

        model.addAttribute("thang1", thang1);
        model.addAttribute("thang2", thang2);
        model.addAttribute("thang3", thang3);
        model.addAttribute("thang4", thang4);
        model.addAttribute("thang5", thang5);
        model.addAttribute("thang6", thang6);
        model.addAttribute("thang7", thang7);
        model.addAttribute("thang8", thang8);
        model.addAttribute("thang9", thang9);
        model.addAttribute("thang10", thang10);
        model.addAttribute("thang11", thang11);
        model.addAttribute("thang12", thang12);

        model.addAttribute("quy1", quy1);
        model.addAttribute("quy2", quy2);
        model.addAttribute("quy3", quy3);
        model.addAttribute("quy4", quy4);

        return "thongkedoanhthu";
    }
}
