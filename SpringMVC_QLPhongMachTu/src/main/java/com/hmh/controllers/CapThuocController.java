/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.controllers;

import com.google.gson.Gson;
import com.hmh.pojo.ChiTietDv;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import com.hmh.pojo.ChiTietThuoc;
import com.hmh.pojo.HoaDon;
import static com.hmh.pojo.LoaiThuoc_.idloaiThuoc;
import com.hmh.pojo.PhieuDangKy;
import com.hmh.pojo.PhieuKhamBenh;
import com.hmh.pojo.TaiKhoan;
import com.hmh.pojo.Thuoc;
import com.hmh.service.CapThuocService;
import com.hmh.service.KhamBenhService;
import com.hmh.service.LapDsKhamService;
import com.hmh.service.LapPhieuKhamService;
import com.hmh.service.QuanLyThuocService;
import com.hmh.service.TaiKhoanService;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
public class CapThuocController {

    @Autowired
    private LapDsKhamService phieuDangKyService;

    @Autowired
    private TaiKhoanService taiKhoanService;

    @Autowired
    private LapPhieuKhamService lapPhieuKhamService;

    @Autowired
    private CapThuocService capThuocService;

    @Autowired
    private KhamBenhService khamBenhService;

    @Autowired
    private QuanLyThuocService quanLyThuocService;

    @GetMapping("/bacsi/capthuoc")
    public String capthuoc(Model model,
            Authentication authentication, @RequestParam("idPDK") int idPDK, @RequestParam Map<String, String> params) {

        model.addAttribute("addChiTietThuoc", new ChiTietThuoc());
        model.addAttribute("addHoaDon", new HoaDon());
        model.addAttribute("loaiThuoc", this.quanLyThuocService.getLoaiThuoc());

        model.addAttribute("listThuoc", this.capThuocService.getListThuoc(params));
        model.addAttribute("listThuoc", this.capThuocService.timKiemThuoc(params));

        if (authentication != null) {
            UserDetails user = taiKhoanService.loadUserByUsername(authentication.getName());
            TaiKhoan u = taiKhoanService.getTaiKhoan(user.getUsername()).get(0);
            model.addAttribute("user", u);

        }

        model.addAttribute("listThuocByID", capThuocService.layThuocByPhieuDangKyId(idPDK));
        PhieuDangKy phieuDangKy = this.khamBenhService.getPDK(idPDK);

        model.addAttribute("listThuocBanDau", this.capThuocService.getListThuoc(params));

        model.addAttribute("idPDK", idPDK);
        model.addAttribute("idpdk", phieuDangKy);

        return "capthuoc";
    }

    @GetMapping("/bacsi/capthuoc/{id}")
    public String khamBenhByID(Model model, @RequestParam(name = "err", required = false) String err,
            @PathVariable(value = "id") int id,
            @RequestParam Map<String, String> params, Authentication authentication) {

        model.addAttribute("addChiTietThuoc", new ChiTietThuoc());
        model.addAttribute("addHoaDon", new HoaDon());

        model.addAttribute("loaiThuoc", this.quanLyThuocService.getLoaiThuoc());

        model.addAttribute("listThuoc", this.capThuocService.getListThuoc(params));
        model.addAttribute("listThuoc", this.capThuocService.timKiemThuoc(params));
        PhieuDangKy phieuDangKy = this.khamBenhService.getPDK(id);

        List<ChiTietThuoc> listThuoc = this.capThuocService.layThuocByPhieuDangKyId(id);

        if (authentication != null) {
            UserDetails user = taiKhoanService.loadUserByUsername(authentication.getName());
            TaiKhoan u = taiKhoanService.getTaiKhoan(user.getUsername()).get(0);
            model.addAttribute("user", u);
        }

        model.addAttribute("idpdk", phieuDangKy);
        model.addAttribute("listThuocByID", listThuoc);

        model.addAttribute("err", err);

        return "capthuoc";
    }

    @PostMapping("/bacsi/capthuoc")
    public String taoChiTietThuocvaHoaDon(Model model, @ModelAttribute(value = "addChiTietThuoc") ChiTietThuoc cct, @RequestParam Map<String, String> params,
            @RequestParam("idPDK") int idPDK, @ModelAttribute(value = "addHoaDon") HoaDon hd) throws UnsupportedEncodingException {

        PhieuDangKy pdk = this.phieuDangKyService.getPhieuDangKyById(idPDK);
        String err = "";
        Thuoc thuoc = this.quanLyThuocService.getThuocById(cct.getIdThuoc().getIdThuoc());
        int slThuoc = thuoc.getSoLuong();
        int slBan = cct.getSoLuongSd();
        int slConLai = slThuoc - slBan;

        if (slBan > slConLai) {
            err = "Số lượng thuốc không đủ!";
            return "redirect:/bacsi/capthuoc?idPDK=" + pdk.getIdPhieudk();
        }

        if (this.capThuocService.themPhieuThuoc(cct, idPDK)) {
            thuoc.setSoLuong(slConLai);
            this.quanLyThuocService.themThuoc(thuoc);

            return "redirect:/bacsi/capthuoc?idPDK=" + idPDK;
        }

        return "capthuoc";
    }

    @PostMapping("/bacsi/capthuoc/taohoadon")
    public String taoHoaDon(Model model, @RequestParam Map<String, String> params,
            @RequestParam("idPDK") int idPDK, @ModelAttribute("addHoaDon") HoaDon hd) {

        if (this.capThuocService.themHoaDonByPDK(hd, idPDK)) {
            return "redirect:/bacsi/lapphieukham";
        }

        return "capthuoc";
    }

    @GetMapping("/ThongTinThuoc-PDF")
    public void generatePDF(HttpServletResponse response,
            @RequestParam("idPDK") int idPDK,
            @RequestParam Map<String, String> params) throws IOException, DocumentException {

        PhieuDangKy phieuDangKy = this.khamBenhService.getPDK(idPDK);

        if (phieuDangKy != null) {
            List<ChiTietThuoc> danhSachThuoc = capThuocService.layThuocByPhieuDangKyId(idPDK);

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=ThongTinThuoc.pdf");

            OutputStream out = response.getOutputStream();

            Document document = new Document();
            PdfWriter.getInstance(document, out);

            document.open();
            document.add(new Paragraph("TOA THUOC PHONG MACH HEALTH COUCH\n"
                    + "\nID Phieu Dang Ky: " + phieuDangKy.getIdPhieudk()
                    + "\nTen benh nhan: " + phieuDangKy.getIdBn().getHoTen()
                    + "\nNgay kham: " + phieuDangKy.getChonNgaykham()
                    + "\nKet luan: " + phieuDangKy.getIdPk().getKetLuan())
            );

            for (ChiTietThuoc t : danhSachThuoc) {
                document.add(new Paragraph("\n---------------------------------------------"));
                document.add(new Paragraph("Ten thuoc: " + t.getIdThuoc().getTenThuoc()));
                document.add(new Paragraph("So luong: " + t.getSoLuongSd()));
                document.add(new Paragraph("Huong dan su dung: " + t.getHdsd()));
//                document.add(new Paragraph("---------------------------------------------"));
            }
            document.close();

            out.flush();
            out.close();
        }
    }

}
