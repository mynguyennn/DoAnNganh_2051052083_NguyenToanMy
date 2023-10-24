/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.controllers;

import com.hmh.pojo.TaiKhoan;
import com.hmh.repository.LichSuKhamRepository;
import com.hmh.service.DanhGiaService;
import com.hmh.service.LichSuKhamService;
import com.hmh.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Asus
 */
@Controller
public class LichSuKhamController {

    @Autowired
    private LichSuKhamService lichSuKhamService;

    @Autowired
    private TaiKhoanService taiKhoanService;

    @Autowired
    private DanhGiaService danhGiaService;

    @GetMapping("/benhnhan/lichsukham")
    public String lichsukham(Model model, Authentication authentication) {

        model.addAttribute("user", new TaiKhoan());

        UserDetails user = this.taiKhoanService.loadUserByUsername(authentication.getName());

        TaiKhoan u = this.taiKhoanService.getTaiKhoanByUsername(user.getUsername());

        model.addAttribute("lskham", this.lichSuKhamService.getPhieuDangKy(u));
        
        

        model.addAttribute("user", u);

        return "lichsukham";

    }

}
