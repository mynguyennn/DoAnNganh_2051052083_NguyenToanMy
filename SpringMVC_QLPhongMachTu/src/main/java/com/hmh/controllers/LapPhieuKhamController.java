/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.controllers;

import com.hmh.pojo.PhieuDangKy;
import com.hmh.pojo.TaiKhoan;
import com.hmh.service.LapDsKhamService;
import com.hmh.service.LapPhieuKhamService;
import com.hmh.service.TaiKhoanService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Asus
 */
@Controller
public class LapPhieuKhamController {

    @Autowired
    private LapDsKhamService phieuDangKyService;

    @Autowired
    private TaiKhoanService taiKhoanService;

    @Autowired
    private LapPhieuKhamService lapPhieuKhamService;

    @GetMapping("/bacsi/lapphieukham")
    public String lapphieukham(Model model, Authentication authentication, @RequestParam Map<String, String> params) {

        if (authentication != null) {
            UserDetails user = taiKhoanService.loadUserByUsername(authentication.getName());
            TaiKhoan u = taiKhoanService.getTaiKhoan(user.getUsername()).get(0);
            model.addAttribute("user", u);

            model.addAttribute("dsbenhnhan", this.phieuDangKyService.getPhieuDangKy(params));
            model.addAttribute("dsbenhnhan", this.lapPhieuKhamService.getPhieuDangKy(u.getIdTk()));
        }

        return "lapphieukham";
    }

}
