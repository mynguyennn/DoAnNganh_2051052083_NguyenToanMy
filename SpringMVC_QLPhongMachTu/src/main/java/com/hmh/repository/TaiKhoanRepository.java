/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.repository;

import com.hmh.pojo.TaiKhoan;
import com.hmh.pojo.UserRole;
import java.util.List;

/**
 *
 * @author Asus
 */
public interface TaiKhoanRepository {

    boolean addTaiKhoan(TaiKhoan tk);

    List<TaiKhoan> getTaiKhoan(String username);

    UserRole getRoleBenhNhan(String role);

    TaiKhoan getTaiKhoanById(int id);

    TaiKhoan getTaiKhoanByUsername(String username);

    boolean doiMatKhau(TaiKhoan tk);

    boolean authUser(String username, String password);

    TaiKhoan addUser(TaiKhoan u);

    //reactjs
    TaiKhoan thayDoiMatKhau(TaiKhoan a);

    List<TaiKhoan> getListTaiKhoan();
}
