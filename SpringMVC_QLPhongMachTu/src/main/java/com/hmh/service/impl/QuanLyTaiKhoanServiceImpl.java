/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.hmh.pojo.PhieuDangKy;
import com.hmh.pojo.TaiKhoan;
import com.hmh.repository.QuanLyTaiKhoanRepository;

import com.hmh.service.QuanLyTaiKhoanService;
import java.io.IOException;

import java.util.List;
import java.util.Map;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Asus
 */
@Service
//        ("userDetailsService")
public class QuanLyTaiKhoanServiceImpl implements QuanLyTaiKhoanService {

    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private QuanLyTaiKhoanRepository quanLyTaiKhoanRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<TaiKhoan> getTaiKhoanAdmin(String username) {
        return this.quanLyTaiKhoanRepository.getTaiKhoanAdmin(username);
    }

    @Override
    public boolean themTaiKhoan(TaiKhoan tk) {
        String pass = tk.getMatKhau();

        if (tk.getIdTk() == null) {
            tk.setMatKhau(this.passwordEncoder.encode(pass));
        }

        if (!tk.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(tk.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                tk.setAvt(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(TaiKhoanServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return this.quanLyTaiKhoanRepository.themTaiKhoan(tk);
    }

    @Override
    public TaiKhoan getTaiKhoanById(int id) {
        return this.quanLyTaiKhoanRepository.getTaiKhoanById(id);
    }

    @Override
    public boolean xoaTaiKhoan(int id) {
        if (quanLyTaiKhoanRepository.getChiTietDichVuByPdk(id) != null) {
            this.quanLyTaiKhoanRepository.xoaCTDichVuBypdky(id);
            this.quanLyTaiKhoanRepository.xoaHoaDonByPDK(id);
            this.quanLyTaiKhoanRepository.xoaCTThoiGianTrucByTK(id);
            this.quanLyTaiKhoanRepository.xoaPhieuDangKyByTK(id);
            return this.quanLyTaiKhoanRepository.xoaTaiKhoan(id);
        } else {
            this.quanLyTaiKhoanRepository.xoaCTThoiGianTrucByTK(id);
            return this.quanLyTaiKhoanRepository.xoaTaiKhoan(id);
        }
    }

    @Override
    public List<TaiKhoan> timKiemTK(Map<String, String> params) {
        return this.quanLyTaiKhoanRepository.timKiemTK(params);
    }

}
