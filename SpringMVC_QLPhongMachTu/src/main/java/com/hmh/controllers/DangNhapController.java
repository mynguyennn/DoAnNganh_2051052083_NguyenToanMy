/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.controllers;

import com.hmh.pojo.TaiKhoan;
import com.hmh.service.TaiKhoanService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Asus
 */
@Controller
public class DangNhapController {


    @RequestMapping("/dangnhap")
    public String dangnhap(Model model) {
        return "dangnhap";
    }

//    @PostMapping("/dangnhap")
//    public String add(@ModelAttribute(value = "user") @Valid TaiKhoan user,
//            BindingResult rs) {
//
//        if (!rs.hasErrors()) {
//            if (this.tks.addTaiKhoan(user) == true) {
//                return "redirect:/";
//            }
//        }
//
//        return "dangnhap";
//    }

}
