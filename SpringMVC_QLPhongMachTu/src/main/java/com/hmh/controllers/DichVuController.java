/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.controllers;

import com.hmh.pojo.DichVu;
import com.hmh.pojo.HoaDon;
import com.hmh.service.KhamBenhService;
import com.hmh.service.QuanLyThuocService;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Asus
 */
@Controller
public class DichVuController {

    @Autowired
    private KhamBenhService khamBenhService;

    @Autowired
    private QuanLyThuocService quanLyThuocService;

    @GetMapping("/admin/dichvu")
    public String dichvu(Model model, @RequestParam(name = "err", required = false) String err) {
        model.addAttribute("listDV", this.khamBenhService.getDichVu());
        model.addAttribute("taoDV", new DichVu());
         model.addAttribute("err", err);
        return "dichvu";
    }

    @PostMapping("/admin/dichvu")
    public String themDichVu(Model model, @ModelAttribute(value = "taoDV") DichVu dv, BindingResult rs) throws UnsupportedEncodingException {
        String err="";
        if (!rs.hasErrors()) {
            if (!dv.getTenDv().isEmpty() && dv.getGiaDv() != null) {
                if (quanLyThuocService.themDichVu(dv) == true) {
                    return "redirect:/admin/dichvu";
                }
            }
            else
            {
                err = "Vui lòng nhập đầy đủ thông tin!";
                return "redirect:/admin/dichvu" + "?err=" + URLEncoder.encode(err, "UTF-8");
            }
        }

        return "dichvu";
    }
}
