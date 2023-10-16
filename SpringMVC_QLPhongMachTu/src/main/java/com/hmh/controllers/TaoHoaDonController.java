/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.controllers;

import com.hmh.pojo.HoaDon;
import com.hmh.pojo.PhieuDangKy;
import com.hmh.pojo.PhieuKhamBenh;
import com.hmh.pojo.TaiKhoan;
import com.hmh.service.CapThuocService;
import com.hmh.service.LapDsKhamService;
import com.hmh.service.TaiKhoanService;
import com.hmh.service.ThanhToanService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.hmh.momoclasses.Environment;
import com.hmh.momoclasses.PaymentResponse;
import com.hmh.enums.RequestType;
import com.hmh.momoprocessor.CreateOrderMoMo;
import com.hmh.share.utils.LogUtils;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
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
public class TaoHoaDonController {

    @Autowired
    private TaiKhoanService taiKhoanService;

    @Autowired
    private ThanhToanService thanhToanService;

    @Autowired
    private CustomDateEditor customDateEditor;

    @Autowired
    private LapDsKhamService lapDsKhamService;

    @Autowired
    private CapThuocService capThuocService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, customDateEditor);
    }

    @GetMapping("/yta/taohoadon")
    public String taohoadon(Model model, Authentication authentication) {
//        model.addAttribute("addHoaDon", new HoaDon());
        if (authentication != null) {
            UserDetails user = taiKhoanService.loadUserByUsername(authentication.getName());
            TaiKhoan u = taiKhoanService.getTaiKhoan(user.getUsername()).get(0);
            model.addAttribute("user", u);
        }

        return "taohoadon";
    }

    @GetMapping("/yta/taohoadon/{id}")
    public String taohoadonById(Model model, @PathVariable(value = "id") int id, @RequestParam Map<String, String> params, Authentication authentication) {

        if (authentication != null) {
            UserDetails user = taiKhoanService.loadUserByUsername(authentication.getName());
            TaiKhoan u = taiKhoanService.getTaiKhoan(user.getUsername()).get(0);
            model.addAttribute("user", u);
        }

        model.addAttribute("idHD", this.thanhToanService.getHoaDonById(id));

        return "taohoadon";
    }

    @GetMapping("/taohoadon")
    public String momoPay(Model model, @RequestParam(value = "id") int id) throws Exception {
        HoaDon hd = this.thanhToanService.getHoaDonById(id);

        BigDecimal tienThuoc = BigDecimal.valueOf(hd.getTienThuoc());
        BigDecimal tienDv = BigDecimal.valueOf(hd.getTienDv());
        BigDecimal tienKham = BigDecimal.valueOf(hd.getTienKham().getTienKham());

        BigDecimal tongTien = tienThuoc.add(tienDv).add(tienKham);

        LogUtils.init();
        String requestId = String.valueOf(System.currentTimeMillis());
        String orderId = String.valueOf(System.currentTimeMillis());
        long amount = tongTien.longValue();

        String orderInfo = "Thanh toán hóa đơn";
        String returnURL = "redirect:yta/taohoadon/" + id;
        String notifyURL = "redirect:yta/taohoadon/" + id;
        Environment environment = Environment.selectEnv("dev");
        PaymentResponse captureWalletMoMoResponse = CreateOrderMoMo.process(environment, orderId, requestId, Long.toString(amount), orderInfo, returnURL, notifyURL, "", RequestType.CAPTURE_WALLET, Boolean.TRUE);
        String url = captureWalletMoMoResponse.getPayUrl();
        return "redirect:" + url;

    }

    @GetMapping("/ThongHoaDon-PDF")
    public void generatePDF(HttpServletResponse response,
            @RequestParam("id") int id,
            @RequestParam Map<String, String> params) throws IOException, DocumentException {

        HoaDon hd = this.thanhToanService.getHoaDonById(id);

        if (hd != null) {

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=ThongHoaDon.pdf");

            OutputStream out = response.getOutputStream();

            Document document = new Document();
            PdfWriter.getInstance(document, out);
            long tongtien = hd.getTienKham().getTienKham() + hd.getTienDv() + hd.getTienThuoc();

//            double tienThoiLai = tienKhachDua - tongtien;
            document.open();
            document.add(new Paragraph("             HOA DON PHONG KHÁM YOUR HEALTH"
                    +    "\n                         Go Vap"
                    +    "\n                      -----------------------"
                    +    "\n                Ten benh nhan: " + hd.getIdPhieudky().getIdBn().getHoTen()
                    +    "\n                Trieu chung: " +hd.getIdPhieudky().getIdPk().getTrieuChung()
                    +    "\n                Ket luan: " +hd.getIdPhieudky().getIdPk().getKetLuan()
                    +    "\n                Tien kham: " + hd.getTienKham().getTienKham() + " vnd"
                    +    "\n                Tien dich vu: " + hd.getTienDv() + " vnd"
                    +    "\n                Tien thuoc: " + hd.getTienThuoc() + " vnd"
                    +    "\n                      -----------------------"
                    +    "\n                Tong tien: " + tongtien + " vnd"
                    +    "\n                         Cam on quy khach da su dung dich"
            ));

            document.close();

            out.flush();
            out.close();
        }
    }

    @PostMapping("/yta/taohoadon")
    public String xacNhanThanhToan(Model model, @ModelAttribute(value = "addHoaDon") HoaDon hd, @RequestParam("id") int id) {

        if (this.thanhToanService.xacNhanHD(id)) {
            return "redirect:/yta/thanhtoan";
        }
        return "taohoadon";
    }
}
