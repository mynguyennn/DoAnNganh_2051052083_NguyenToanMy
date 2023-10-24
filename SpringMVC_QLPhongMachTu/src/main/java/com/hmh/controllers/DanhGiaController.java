/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.controllers;

import com.hmh.pojo.TaiKhoan;
import com.hmh.pojo.DanhGiaBs;
import com.hmh.pojo.PhieuDangKy;
import com.hmh.repository.LichSuKhamRepository;
import com.hmh.service.DanhGiaService;
import com.hmh.service.LapDsKhamService;
import com.hmh.service.LichSuKhamService;
import com.hmh.service.TaiKhoanService;
import java.io.UnsupportedEncodingException;
import static java.lang.System.err;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author LENOVO
 */
@Controller
@ControllerAdvice
public class DanhGiaController {

    @Autowired
    private DanhGiaService danhGiaService;

    @Autowired
    private TaiKhoanService taiKhoanService;
    @Autowired
    private LapDsKhamService lapDsKhamService;
    @Autowired
    private Environment env;

//    @GetMapping("/benhnhan/danhgia")
//    public String danhgia(Model model, Authentication authentication, @RequestParam Map<String, String> params) {
//        model.addAttribute("danhgia", new DanhGiaBs());
//        return "danhgia";
//    }
    @GetMapping("/benhnhan/danhgia/{id}/{idpdk}")
    public String layDl(Model model, @PathVariable(value = "id") int id, @RequestParam Map<String, String> params, @PathVariable(value = "idpdk") int idpdk,@RequestParam(name = "err", required = false) String err) {

        model.addAttribute("bacsi", this.taiKhoanService.getTaiKhoanById(id));
        model.addAttribute("dsPdk", this.lapDsKhamService.getPhieuDangKyById(idpdk));
        model.addAttribute("dsdanhgia", this.danhGiaService.getDgByIdBs(id, params));
        model.addAttribute("dsdanhgia", this.danhGiaService.getDanhGia(idpdk));
        model.addAttribute("danhgia", new DanhGiaBs());
         model.addAttribute("err", err);
        int dem = this.danhGiaService.demComment();
        int pageSize = Integer.parseInt(env.getProperty("PAGE_SIZE").toString());
        model.addAttribute("pages", Math.ceil(dem * 1.0 / pageSize));

//        List<DanhGiaBs> danhGiaBs = this.danhGiaService.getDgByIdBs(id);
//        List<Integer> sao = new ArrayList<>();
//        for(DanhGiaBs d : danhGiaBs)
//        {
//            sao.add(d.getDanhGia());
//        }
//        if (danhGiaBs.isEmpty())
//        {
//             sum = sao.stream().mapToInt(Integer::intValue).sum();
//             tb = (double) sum / sao.size();
//        }
////        else {
////            for(DanhGiaBs d : danhGiaBs)
////            {
////                sum += d.getDanhGia();
////            }
////        }
////        double tb = sum / danhGiaBs.size();
//        model.addAttribute("tb", tb);
        return "danhgia";

    }

    @PostMapping("/benhnhan/danhgia/{id}/{idpdk}")
    public String lapdanhgia(Model model, @ModelAttribute(value = "danhgia") DanhGiaBs dg,
            BindingResult rs, @PathVariable(value = "id") int id, Authentication authentication, @PathVariable(value = "idpdk") int idpdk) throws UnsupportedEncodingException {

          String err = "";
        if (authentication != null) {
            UserDetails userDetails = taiKhoanService.loadUserByUsername(authentication.getName());
            Integer tk = this.taiKhoanService.getTaiKhoanByUsername(userDetails.getUsername()).getIdTk();
            if (!rs.hasErrors()) {
                if(dg.getDanhGia() != null) {
                    if (this.danhGiaService.luuDanhGia(id, dg, tk, idpdk) == true) {
                        return "redirect:/benhnhan/danhgia/{id}/{idpdk}";
                    }
                }
                else
                {
                    err = "Vui lòng chọn đánh giá!";
                            return "redirect:/benhnhan/danhgia/{id}/{idpdk}" + "?err=" + URLEncoder.encode(err, "UTF-8");
                }

            }

        }

        return "danhgia";
    }
}
