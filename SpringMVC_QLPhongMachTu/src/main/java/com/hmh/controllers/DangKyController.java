/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.controllers;

import com.hmh.pojo.TaiKhoan;
import com.hmh.service.TaiKhoanService;
import java.beans.PropertyEditorSupport;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Asus
 */
@Controller
public class DangKyController {

//    @Autowired
//    private Cloudinary cloudinary;
    @Autowired
    private TaiKhoanService userDetailsService;
    @Autowired
    private CustomDateEditor customDateEditor;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, customDateEditor);
    }
//    @InitBinder
//    public void initBinder1(WebDataBinder binder) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
//            @Override
//            public void setAsText(String text) throws IllegalArgumentException {
//                try {
//                    Date date = dateFormat.parse(text);
//                    if (date.after(new Date())) {
//                        throw new IllegalArgumentException("Ngày sinh không được trong tương lai.");
//                    }
//                    setValue(date);
//                } catch (Exception e) {
//                    throw new IllegalArgumentException("Định dạng ngày không hợp lệ.");
//                }
//            }
//        });
//    }
    @GetMapping("/dangky")
    public String dangky(Model model, @RequestParam(name = "msg", required = false) String msg) {
        model.addAttribute("msg", msg);
        model.addAttribute("user", new TaiKhoan());
        return "dangky";
    }

    @PostMapping("/dangky")
    public String dangky(Model model, @ModelAttribute(value = "user") TaiKhoan user, BindingResult rs) throws UnsupportedEncodingException {
        String msg = "";
        String username = user.getTaiKhoan();

        if (!rs.hasErrors()) {
            if (!user.getTaiKhoan().isEmpty() && !user.getMatKhau().isEmpty() && !user.getHoTen().isEmpty() && !user.getConfirmmatKhau().isEmpty() && !user.getEmail().isEmpty() && !user.getGioiTinh().isEmpty()
                    && user.getNgaySinh() != null && !user.getSdt().isEmpty() && !user.getDiaChi().isEmpty()) {
                if (userDetailsService.getTaiKhoan(username).isEmpty()) {
                    if (user.getMatKhau().equals(user.getConfirmmatKhau())) {
                        if (this.userDetailsService.addTaiKhoan(user) == true) {
                            return "redirect:/dangnhap";
                        } else {
                            msg = "Lỗi!!";
                            return "redirect:/dangky" + "?msg=" + URLEncoder.encode(msg, "UTF-8");
                        }

                    } else {
                        msg = "Mật khẩu không khớp!!";
                        return "redirect:/dangky" + "?msg=" + URLEncoder.encode(msg, "UTF-8");
                    }

                } else {
                    msg = "Tên người dùng không hợp lệ!";
                    return "redirect:/dangky" + "?msg=" + URLEncoder.encode(msg, "UTF-8");
                }
            } else {
                msg = "Vui lòng nhập đầy đủ thông tin!";
                return "redirect:/dangky" + "?msg=" + URLEncoder.encode(msg, "UTF-8");
            }
        }

        return "dangky";
    }
}
