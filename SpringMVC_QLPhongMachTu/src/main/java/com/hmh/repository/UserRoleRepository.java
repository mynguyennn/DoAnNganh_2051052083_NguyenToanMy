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
public interface UserRoleRepository {
    List<UserRole> getUserRole();
//    void luuUserRole(TaiKhoan tk, UserRole userRole) ;
}
