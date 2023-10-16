/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.service.impl;

import com.hmh.pojo.DichVu;
import com.hmh.pojo.PhieuDangKy;
import com.hmh.repository.DangKyKhamRepository;
import com.hmh.service.DangKyKhamService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Asus
 */
@Service
public class DangKyKhamServiceImpl implements DangKyKhamService{

    @Autowired
    private DangKyKhamRepository dangKyKhamRepository;

    @Override
    public List<PhieuDangKy> getPhieuById(int tk) {
        return this.getPhieuById(tk);
        }
    

    
}
