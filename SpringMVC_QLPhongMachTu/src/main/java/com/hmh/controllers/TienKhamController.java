/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.controllers;

import com.hmh.pojo.TaiKhoan;
import com.hmh.pojo.TienKham;
import com.hmh.service.CapThuocService;
import com.hmh.service.QuanLyThuocService;
import com.hmh.service.TaiKhoanService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Asus
 */
@Controller
public class TienKhamController {

    @Autowired
    private QuanLyThuocService quanLyThuocService;

    @GetMapping("/admin/tienkham")
    public String tienkham(Model model) {
        model.addAttribute("tienKham", this.quanLyThuocService.getTienKham());
        return "tienkham";
    }

    @GetMapping("/admin/tienkham/{id}")
    public String tienkham(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("tienKham", this.quanLyThuocService.getTienKham());
        return "tienkham";
    }

    @PostMapping("/admin/tienkham")
    public String thayDoiTienKham(Model model, @RequestParam Map<String, String> params,
            @RequestParam("newTienKham") String newTienKham) {

        Integer id = Integer.parseInt(params.get("tienKham"));
        TienKham tk = this.quanLyThuocService.getTienKhamById(id);

        Long tienkhams = Long.parseLong(newTienKham);
        tk.setTienKham(tienkhams);

        this.quanLyThuocService.thaydoiTienKham(tk);
        return "redirect:tienkham/" + tk.getIdtienKham();
    }
}
