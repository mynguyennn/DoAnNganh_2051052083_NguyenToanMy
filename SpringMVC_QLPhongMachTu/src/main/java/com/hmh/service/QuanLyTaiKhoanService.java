/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.service;

import com.hmh.pojo.PhieuDangKy;
import com.hmh.pojo.TaiKhoan;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author Asus
 */
public interface QuanLyTaiKhoanService {

    List<TaiKhoan> getTaiKhoanAdmin(String username);

    boolean themTaiKhoan(TaiKhoan tk);

    TaiKhoan getTaiKhoanById(int id);

    boolean xoaTaiKhoan(int id);

    List<TaiKhoan> timKiemTK(Map<String, String> params);
    

}
