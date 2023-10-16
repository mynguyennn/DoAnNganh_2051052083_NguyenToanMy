/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.controllers;

import com.hmh.pojo.PhieuDangKy;
import com.hmh.pojo.TaiKhoan;
import com.hmh.service.DangKyKhamService;

//import com.hmh.service.DangKyKhamService;
import com.hmh.service.LapDsKhamService;
import com.hmh.service.LichSuKhamService;
import com.hmh.service.TaiKhoanService;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.Temporal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Asus
 */
@Controller
public class DangKyKhamController {

    @Autowired
    private TaiKhoanService taiKhoanService;

//    @Autowired
//    private DangKyKhamService dangKyKhamService;
    @Autowired
    private LapDsKhamService lapDsKhamService;

    @Autowired
    private LichSuKhamService lichSuKhamService;

    @Autowired
    private DangKyKhamService dangKyKhamService;

    @Autowired
    private CustomDateEditor customDateEditor;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, customDateEditor);
    }

    @GetMapping("/benhnhan/dangkykham")
    public String dangkykham(Model model, Authentication authentication, @RequestParam(name = "err", required = false) String err) {
//        model.addAttribute("user", new TaiKhoan());
        model.addAttribute("themphieudky", new PhieuDangKy());
        UserDetails user = taiKhoanService.loadUserByUsername(authentication.getName());
        TaiKhoan u = taiKhoanService.getTaiKhoan(user.getUsername()).get(0);
        model.addAttribute("user", u);

        model.addAttribute("user", this.taiKhoanService.getTaiKhoan(authentication.getName()).get(0));
//        model.addAttribute("getDV", this.dangKyKhamService.getDichVu());
        model.addAttribute("err", err);
        return "dangkykham";
    }

    @GetMapping("/benhnhan/dangkykham/{id}")
    public String getBenhNhanId(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("user", this.taiKhoanService.getTaiKhoanById(id));
        return "dangkykham";
    }

    @PostMapping("/benhnhan/dangkykham")
    public String benhNhanCapNhat(Model model,
            Authentication authentication, @ModelAttribute(value = "user") TaiKhoan tk) {

        String err = "";

        if (this.taiKhoanService.addTaiKhoan(tk) == true) {

            return "redirect:/benhnhan/dangkykham";

        } else {
            err = "Cập nhật thông tin không thành công!";
        }

        model.addAttribute("err", err);
        return "dangkykham";
    }

    @PostMapping("/benhnhan/dangkykham_pdk")
    public String benhNhanDkyKham(Model model,
            Authentication authentication, @ModelAttribute(value = "themphieudky") PhieuDangKy pdk,
            BindingResult rs, HttpSession session, @RequestParam Map<String, String> params) throws UnsupportedEncodingException {
        String err = "";

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String username = userDetails.getUsername();

        TaiKhoan tk = this.taiKhoanService.getTaiKhoanByUsername(username);

        List<PhieuDangKy> listPDK = (List<PhieuDangKy>) this.lapDsKhamService.getPDKByIdTaiKhoan(tk.getIdTk());

        PhieuDangKy phieuDkyCuoiCung = null;

        Date ngayGioHienTai = new Date();

        if (pdk.getChonNgaykham() != null && !pdk.getThoiGianKham().isEmpty()) {

            if (listPDK.isEmpty()) {

                this.lapDsKhamService.themPhieuDangKy(pdk);

                listPDK = (List<PhieuDangKy>) this.lapDsKhamService.getPDKByIdTaiKhoan(tk.getIdTk());
                return "redirect:/benhnhan/lichsukham";
            } else {
                for (PhieuDangKy pdks : listPDK) {

                    phieuDkyCuoiCung = pdks;

                    if (phieuDkyCuoiCung != null) {
                        Calendar calHienTai = Calendar.getInstance();
                        calHienTai.setTime(ngayGioHienTai);
                        int gioHienTai = calHienTai.get(Calendar.HOUR_OF_DAY);
                        int phutHienTai = calHienTai.get(Calendar.MINUTE);

                        Calendar calCuoiCung = Calendar.getInstance();
                        calCuoiCung.setTime(phieuDkyCuoiCung.getThoiGianTaophieu());
                        int gioCuoiCung = calCuoiCung.get(Calendar.HOUR_OF_DAY);
                        int phutCuoiCung = calCuoiCung.get(Calendar.MINUTE);

                        int tongPhutHienTai = gioHienTai * 60 + phutHienTai;
                        int tongPhutCuoiCung = gioCuoiCung * 60 + phutCuoiCung;

                        int khoangThoiGianPhut = tongPhutHienTai - tongPhutCuoiCung;

                        if (khoangThoiGianPhut <= 1) {
                            err = "Bệnh nhân chỉ được phép gửi phiếu đăng ký sau 1p kể từ lần gửi trước đó!";
                            return "redirect:/benhnhan/dangkykham" + "?err=" + URLEncoder.encode(err, "UTF-8");
                        }

                    }
//                    Date thoiGianTaoPhieu = pdks.getThoiGianTaophieu();
//                    model.addAttribute("thoiGianTaoPhieu", thoiGianTaoPhieu);
                }

            }

        } else {
            err = "Vui lòng nhập đủ thông tin!";
            return "redirect:/benhnhan/dangkykham" + "?err=" + URLEncoder.encode(err, "UTF-8");
        }

        this.lapDsKhamService.themPhieuDangKy(pdk);
        return "redirect:/benhnhan/lichsukham";

//        return "dangkykham";
    }
}
