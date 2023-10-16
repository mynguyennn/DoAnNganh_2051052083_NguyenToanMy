/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.controllers;

import com.hmh.pojo.ChiTietThoiGianTruc;
import com.hmh.pojo.TaiKhoan;
import com.hmh.pojo.ThoiGianTruc;
import com.hmh.service.LichTrucService;
import com.hmh.service.QuanLyTaiKhoanService;
import com.hmh.service.TaiKhoanService;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
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
 * @author LENOVO
 */
@Controller
public class LichTrucController {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private QuanLyTaiKhoanService quanLyTaiKhoanService;
    @Autowired
    private LichTrucService lichTrucService;

    @Autowired
    private CustomDateEditor customDateEditor;

    @Autowired
    private TaiKhoanService taiKhoanService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, customDateEditor);
    }

    @ModelAttribute
    public void getLich(Model model) {
        List<Date> dateList = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.add(Calendar.WEEK_OF_YEAR, 1);

        dateList.add(calendar.getTime());
        for (int i = 0; i < 6; i++) {
            calendar.add(Calendar.DAY_OF_WEEK, 1);
            dateList.add(calendar.getTime());

        }
        model.addAttribute("caTruc", this.lichTrucService.getTg());
        model.addAttribute("lichtruc", new ChiTietThoiGianTruc());
        model.addAttribute("dateList", dateList);
    }

    @GetMapping("/admin/lichtruc")
    public String lichTruc(Model model, Authentication authentication,
            @RequestParam(name = "msg", required = false) String msg,
            @RequestParam Map<String, String> params) {

//         model.addAttribute("lich", this.lichTrucService.getLich(dateList.get(0)));
        model.addAttribute("selectedDates", new ArrayList<Date>());
        model.addAttribute("tk", this.lichTrucService.getTkYtaBs());
        model.addAttribute("lichtruc", new ChiTietThoiGianTruc());
        model.addAttribute("listCTLT", this.lichTrucService.getChiTietTgTruc());
        model.addAttribute("msg", msg);
        return "lichtruc";
    }

    @GetMapping("/admin/lichtruc/{id}")
    public String loadLichTruc(Model model, @PathVariable(value = "id") int id,
            @RequestParam Map<String, String> params) {

        model.addAttribute("tk", this.lichTrucService.getTkYtaBs());
        model.addAttribute("idtk", this.quanLyTaiKhoanService.getTaiKhoanById(id));
        model.addAttribute("listCTLT", this.lichTrucService.getChiTietTgTruc());
        return "lichtruc";
    }

    @PostMapping("/admin/lichtruc")
    public String layLichTruc(Model model, @RequestParam("selectedDates") List<String> selectedDates,
            @RequestParam("caTrucId") String caTrucId, @RequestParam(value = "id") TaiKhoan id, ChiTietThoiGianTruc tg, BindingResult rs) throws ParseException, UnsupportedEncodingException, MessagingException {

        String msg = "";
        boolean msgs = false;
        TaiKhoan taiKhoan = this.taiKhoanService.getTaiKhoanById(id.getIdTk());
        List<Date> dates = new ArrayList<>();
        List<Integer> idtgTruc = new ArrayList<>();
        List<ChiTietThoiGianTruc> cttgt = this.lichTrucService.getChiTietTgtByidTk(id);
        List<ChiTietThoiGianTruc> DScttgt = this.lichTrucService.getChiTietTgTruc();
        List<Date> dateAfter = new ArrayList<>();
        List<Integer> timeAfter = new ArrayList<>();
        int idtgTruc1;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (String selectedDate : selectedDates) {
            tg = new ChiTietThoiGianTruc();

            String[] parts = selectedDate.split("-");

            if (parts.length == 4) {
                caTrucId = parts[0];
                idtgTruc1 = Integer.parseInt(caTrucId.toString());

                String year = parts[3];
                String month = parts[2];
                String day = parts[1];
                String date = day + "-" + month + "-" + year;

                Date dateDate = dateFormat.parse(date);
                dates.add(dateDate);
                idtgTruc.add(idtgTruc1);

            }

        }
        for (String selectedDate : selectedDates) {
            String[] parts = selectedDate.split("-");

            if (parts.length == 4) {
                caTrucId = parts[0];
                idtgTruc1 = Integer.parseInt(caTrucId.toString());

                String year = parts[3];
                String month = parts[2];
                String day = parts[1];
                String date = day + "-" + month + "-" + year;

                Date dateDate = dateFormat.parse(date);

                boolean isDuplicate = false;
                int demNgay = 0;
                int demCa = 0;

                for (ChiTietThoiGianTruc cttgts : DScttgt) {
                    if (dateDate.equals(cttgts.getNgayDkyTruc())) {
                        demNgay++;
                        if (idtgTruc1 == cttgts.getIdTgTruc().getIdtgTruc()) {
                            demCa++;
                        }
                    }
                }

                if (demNgay < 6 && demCa < 2) {
                    for (ChiTietThoiGianTruc cttgts : cttgt) {
                        if (idtgTruc1 == cttgts.getIdTgTruc().getIdtgTruc() && dateDate.equals(cttgts.getNgayDkyTruc())) {
                            isDuplicate = true;
                            msg = "Trùng ca trực và ngày đăng ký!";
                            return "redirect:/admin/lichtruc" + "?msg=" + URLEncoder.encode(msg, "UTF-8");
                        }

                    }

                    if (!isDuplicate) {
                        timeAfter.add(idtgTruc1);
                        dateAfter.add(dateDate);
                        this.lichTrucService.addAndUpdate(tg, id, dateAfter, timeAfter);
//                        MimeMessage message = javaMailSender.createMimeMessage();
//                        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
//
//                        String nguoinhan = taiKhoan.getEmail();
//                        String tennguoinhan = taiKhoan.getHoTen();
//
//                        helper.setTo(nguoinhan);
//                        helper.setSubject("Lịch Trực Trong Tuần Sau");
//
//                        String content = "<html><body>"
//                                + "<p> Chào: " + tennguoinhan
//                                + "<!/p>"
//                                + "<p> Lịch trực của bạn trong tuần sau là: "
//                                + "</p>"
//                                + "</body></html>";
//                        for (Date d : dateAfter) {
//                            content += "<p>" + d + "</p>";
//                        }
//
//                        content += "</body></html>";
//
//                        helper.setText(content, true);
//
//                        javaMailSender.send(message);
//                       
                        msg = "Lưu ca trực thành công!";
                        return "redirect:/admin/lichtruc" + "?msg=" + URLEncoder.encode(msg, "UTF-8");

                    }

                    msg = "Không được có hơn 6 người trong một ngày trực và hơn 2 người trong một ca trực!";
                    return "redirect:/admin/lichtruc" + "?msg=" + URLEncoder.encode(msg, "UTF-8");
                }
            }
        }

        return "redirect:/admin/lichtruc";
    }

}
