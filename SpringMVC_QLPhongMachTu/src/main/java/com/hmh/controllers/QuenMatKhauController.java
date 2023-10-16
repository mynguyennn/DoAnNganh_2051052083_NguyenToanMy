/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.controllers;

import com.hmh.pojo.TaiKhoan;
import com.hmh.service.TaiKhoanService;
import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Message;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.Random;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.twilio.type.PhoneNumber;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Asus
 */
@Controller
@PropertySource("classpath:configs.properties")
public class QuenMatKhauController {

    @Autowired
    private TaiKhoanService taiKhoanService;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Environment evn;
    
    
    private Map<String, String> maXacNhanMap = new HashMap<>();

    @GetMapping("/quenmatkhau")
    public String quenmatkhau(Model model, @RequestParam(name = "err", required = false) String err) {
        model.addAttribute("err", err);
        model.addAttribute("tk", new TaiKhoan());
        return "quenmatkhau";
    }

    @PostMapping("/quenmatkhau")
    public String xacNhanTK(Model model, @ModelAttribute("tk") TaiKhoan tk) throws MessagingException, UnsupportedEncodingException {
        String username = tk.getTaiKhoan();
        TaiKhoan taiKhoan = this.taiKhoanService.loadUserByUsernameQuenPass(username);
        List<TaiKhoan> listTK = this.taiKhoanService.getListTaiKhoan();
        String err = "";
        Random so = new Random();
        int s = so.nextInt(1000000);
        System.out.println(s);
        maXacNhanMap.put(username, String.valueOf(s));
         String tennguoinhan = taiKhoan.getHoTen();
        if (!tk.getTaiKhoan().isEmpty()) {
            if (taiKhoan != null) {

                
//                    String smsBody = "Xin chào" +tennguoinhan +"!"
//                            +"\nMã xác nhận của bạn là: " + String.valueOf(s);
//                    Twilio.init(this.evn.getProperty("twilio.accountSid"), this.evn.getProperty("twilio.authToken"));
//                    
//                     com.twilio.rest.api.v2010.account.Message sms  =  com.twilio.rest.api.v2010.account.Message.creator(
//                                                        new PhoneNumber("+84837525517"),
//                                                        new PhoneNumber(this.evn.getProperty("twilio.phoneNumber")),
//                                                        smsBody).create();

               
                
//                MimeMessage message = javaMailSender.createMimeMessage();
//                MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
//
//                String nguoinhan = taiKhoan.getEmail();
////                String tennguoinhan = taiKhoan.getHoTen();
//
//                helper.setTo(nguoinhan);
//                helper.setSubject("MÃ XÁC NHẬN MẬT KHẨU");
//
//                String content = "<html><body>"
//                        + "<p> Chào: " + tennguoinhan
//                        + "<!/p>"
//                        + "<p> Mã xác nhận của bạn là: " + String.valueOf(s)
//                        + "</p>"
//                        + "</body></html>";
//
//                helper.setText(content, true);
//
//                javaMailSender.send(message);
//                return "redirect:/thaydoimatkhau/" + taiKhoan.getIdTk();
            }

        } else {
            err = "Tài khoản không tồn tại!";
            return "redirect:/quenmatkhau" + "?err=" + URLEncoder.encode(err, "UTF-8");
        }

        return "quenmatkhau";
    }

    @GetMapping("/thaydoimatkhau")
    public String thayDoiMk(Model model) {
        model.addAttribute("tk", new TaiKhoan());
        return "thaydoimatkhau";
    }

    @GetMapping("/thaydoimatkhau/{id}")
    public String thayDoiMkByIdTK(Model model, @PathVariable("id") int id, @RequestParam(name = "err", required = false) String err) {

        TaiKhoan tk = this.taiKhoanService.getTaiKhoanById(id);
        model.addAttribute("user", tk);
        model.addAttribute("err", err);
        return "thaydoimatkhau";
    }

    @PostMapping("/thaydoimatkhau")
    public String thayDoiMkByIdTK(Model model, @RequestParam Map<String, String> params,
            @RequestParam("maXacNhan") int maXacNhan,
            @RequestParam("matKhauMoi") String matKhauMoi,
            @RequestParam("xacNhanMatKhauMoi") String xacNhanMatKhauMoi, HttpSession session) throws UnsupportedEncodingException {
        String err = "";
        Integer id = Integer.parseInt(params.get("user"));

        TaiKhoan taiKhoan = taiKhoanService.getTaiKhoanById(id);

        String storedMaXacNhan = maXacNhanMap.get(taiKhoan.getTaiKhoan());

        int maXacNhanParse = Integer.parseInt(storedMaXacNhan);
        if (maXacNhan != 0 && !matKhauMoi.isEmpty() && !xacNhanMatKhauMoi.isEmpty()) {
            if (maXacNhanParse == maXacNhan) {

                if (!matKhauMoi.equals(xacNhanMatKhauMoi)) {
                    err = "Mật khẩu mới không khớp!";
//                    session.setAttribute("quenpass", "Mật khẩu mới không khớp!");
                    return "redirect:/thaydoimatkhau" + id + "?err=" + URLEncoder.encode(err, "UTF-8");
                }
                String hashedPassword = passwordEncoder.encode(matKhauMoi);
                taiKhoan.setMatKhau(hashedPassword);

                boolean capNhatThanhCong = taiKhoanService.doiMatKhau(taiKhoan);

                if (capNhatThanhCong) {
                    maXacNhanMap.remove(taiKhoan.getTaiKhoan());
                    session.setAttribute("thanhcong", "Đổi mật khẩu thành công.");
                    return "redirect:/";
                } else {
                    err = "Lỗi khi cập nhật mật khẩu.";
//                    session.setAttribute("quenpass", "Lỗi khi cập nhật mật khẩu.");
                    return "redirect:/thaydoimatkhau" + id + "?err=" + URLEncoder.encode(err, "UTF-8");
                }
            } else {
                err = "Mã xác nhận không hợp lệ!";
                return "redirect:/thaydoimatkhau" + id + "?err=" + URLEncoder.encode(err, "UTF-8");
//                session.setAttribute("quenpass", "Mã xác nhận không hợp lệ!");
            }
        } else {
            err = "Vui Lòng nhập đầy đủ thông tin!";
            return "redirect:/thaydoimatkhau" + id + "?err=" + URLEncoder.encode(err, "UTF-8");
        }

//        return "thaydoimatkhau";
    }

}
