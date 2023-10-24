/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.controllers;

import com.hmh.pojo.DanhGiaBs;
import com.hmh.pojo.HoaDon;
import com.hmh.pojo.PhieuDangKy;
import com.hmh.pojo.TaiKhoan;
import com.hmh.service.ThongKeBenhNhanService;
import com.hmh.service.ThongKeDanhGiaService;
import com.hmh.service.ThongKeDoanhThuService;
import java.sql.Timestamp;
import java.util.ArrayList;
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
 * @author LENOVO
 */
@Controller
public class ThongKeDanhGiaController {

    @Autowired
    private ThongKeDanhGiaService thongKeDGService;

    public double tinhtb(List<Integer> danhgia) {
        return danhgia.stream().mapToDouble(Integer::doubleValue).average().orElse(0.0);
    }

    @GetMapping("/admin/thongkedanhgia")
    public String thongKeDanhGia(Model model) {

        return "thongkedanhgia";
    }

    @PostMapping("/admin/thongkedanhgia")
    public String thongKeDoanhThuu(Model model,
            DanhGiaBs dgBs) {

        List<Integer> bacsi = this.thongKeDGService.getBacSi();

        List<Double> dsDg = new ArrayList<>();

        for (Integer idbs : bacsi) {
            List<Integer> ds =  this.thongKeDGService.getDgByIdBs(idbs);
            double tbsao = tinhtb(ds);
            dsDg.add(tbsao);
        }

        model.addAttribute("dsbacsi", bacsi);
        model.addAttribute("tb", dsDg);
        return "thongkedanhgia";
    }

}
