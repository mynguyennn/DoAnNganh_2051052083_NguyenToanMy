/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.controllers;

import com.hmh.pojo.Thuoc;
import com.hmh.service.QuanLyThuocService;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Asus
 */
@Controller
public class QuanLyThuocController {

    @Autowired
    private QuanLyThuocService quanLyThuocService;

    @ModelAttribute
    public void commonAttr(Model model) {
        model.addAttribute("donviThuoc", this.quanLyThuocService.getDonViThuoc());
        model.addAttribute("loaiThuoc", this.quanLyThuocService.getLoaiThuoc());

    }

    @GetMapping("/admin/quanlythuoc")
    public String loadDSThuoc(Model model, @RequestParam Map<String, String> params, @RequestParam(name = "err", required = false) String err) {
        model.addAttribute("thuoc", new Thuoc());
        model.addAttribute("qlThuoc", this.quanLyThuocService.getThuoc(null));
        model.addAttribute("qlThuoc", this.quanLyThuocService.timKiemThuoc(params));
        model.addAttribute("err", err);
        return "quanlythuoc";
    }

    @GetMapping("/admin/quanlythuoc/{id}")
    public String updateThuoc(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("thuoc", this.quanLyThuocService.getThuocById(id));
        model.addAttribute("qlThuoc", this.quanLyThuocService.getThuoc(null));
        return "quanlythuoc";
    }

    @PostMapping("/admin/quanlythuoc")
    public String themThuoc(Model model, @ModelAttribute(value = "thuoc") Thuoc t, BindingResult rs) throws UnsupportedEncodingException {
        String err = "";
        if (!rs.hasErrors()) {
            if (!t.getTenThuoc().isEmpty() && t.getGiaThuoc() != 0 && t.getSoLuong() != 0 && !t.getXuatXu().isEmpty() && t.getDonVi().getIddonVi() != null && t.getLoaiThuoc().getIdloaiThuoc() != null) {
                if (quanLyThuocService.themThuoc(t) == true) {
                    return "redirect:/admin/quanlythuoc";
                }
            } else {
                err = "Vui lòng nhập đầy đủ thông tin!";
                model.addAttribute("qlThuoc", this.quanLyThuocService.getThuoc(null));
                return "redirect:/admin/quanlythuoc" + "?err=" + URLEncoder.encode(err, "UTF-8");
            }
        }

        model.addAttribute("qlThuoc", this.quanLyThuocService.getThuoc(null));

        return "quanlythuoc";

    }
}
