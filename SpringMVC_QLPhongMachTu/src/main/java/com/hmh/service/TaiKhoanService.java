/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.service;

import com.hmh.pojo.TaiKhoan;
import com.hmh.pojo.UserRole;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Asus
 */
public interface TaiKhoanService extends UserDetailsService {

    boolean addTaiKhoan(TaiKhoan tk);

    List<TaiKhoan> getTaiKhoan(String username);

    UserRole getRoleBenhNhan(String role);

    TaiKhoan getTaiKhoanById(int id);

    TaiKhoan getTaiKhoanByUsername(String username);

    boolean doiMatKhau(TaiKhoan tk);

    boolean authUser(String username, String password);

    TaiKhoan addUser(Map<String, String> params, MultipartFile avatar);

    //reactjs
    TaiKhoan thayDoiMatKhau(Map<String, String> params);
    
    public TaiKhoan loadUserByUsernameQuenPass(String username);
    
    List<TaiKhoan> getListTaiKhoan();
}
