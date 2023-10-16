/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.service.impl;

import com.hmh.pojo.TaiKhoan;
import com.hmh.pojo.UserRole;
import com.hmh.repository.TaiKhoanRepository;
import com.hmh.repository.UserRoleRepository;
import com.hmh.service.UserRoleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Asus
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Override
    public List<UserRole> getUserRole() {
        return this.userRoleRepository.getUserRole();
    }


}
