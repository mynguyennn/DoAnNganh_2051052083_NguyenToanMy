/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.controllers;

import com.hmh.pojo.ChiTietThoiGianTruc;
import com.hmh.pojo.TaiKhoan;
import com.hmh.pojo.ThoiGianTruc;
import com.hmh.service.LichTrucService;
import com.hmh.service.TaiKhoanService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Asus
 */
@Controller
public class DangKyLichYTController {

    @Autowired
    private LichTrucService lichTrucService;

    @Autowired
    private TaiKhoanService taiKhoanService;

    @GetMapping("/yta/dangkylichYT")
    public String dangkylichYT(Model model, Authentication authentication) throws ParseException {

        UserDetails user = this.taiKhoanService.loadUserByUsername(authentication.getName());

        TaiKhoan u = this.taiKhoanService.getTaiKhoanByUsername(user.getUsername());

        model.addAttribute("listCTLT", this.lichTrucService.getChiTietThoiGianTrucByIDTK(u));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        String formattedDate = dateFormat.format(currentDate);
        Date ngayHienTai = dateFormat.parse(formattedDate);

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date currentTime = new Date();
        String currentTimeStr = timeFormat.format(currentTime);
        Date gioHienTai = timeFormat.parse(currentTimeStr);

        boolean button = false;

        List<Object> chiTietThoiGianTrucs = this.lichTrucService.getChiTietThoiGianTrucByIDTK(u);

        for (Object chiTiet : chiTietThoiGianTrucs) {
            ChiTietThoiGianTruc chiTietThoiGianTruc = (ChiTietThoiGianTruc) chiTiet;

            ThoiGianTruc thoiGianTruc = chiTietThoiGianTruc.getIdTgTruc();
            Date startTime = thoiGianTruc.getBatDau();
            Date endTime = thoiGianTruc.getKetThuc();

//            Date ngayDkyTruc = chiTietThoiGianTruc.getNgayDkyTruc();
            if (gioHienTai.after(startTime) && gioHienTai.before(endTime)) {
                button = true;

            }
        }
        model.addAttribute("buttonds", button);
        model.addAttribute("gioHienTai", gioHienTai);

        model.addAttribute("ngayHienTai", ngayHienTai);
        model.addAttribute("user", u);
        return "dangkylichYT";
    }

    @GetMapping("/yta/dangkylichYT/{id}")
    public String xacnhan(Model model, @PathVariable(value = "id") int id,
            Authentication authentication) {
        ChiTietThoiGianTruc ct = this.lichTrucService.getChiTietThoiGianTrucById(id);

        if (ct.getIdChiTietTgTruc() != null) {
//                ct.setTrangThaiTruc((short)1);              
            if (lichTrucService.update(ct)) {
                return "redirect:/yta/dangkylichYT";
            }
        }

        return "dangkylichYT";
    }
}
