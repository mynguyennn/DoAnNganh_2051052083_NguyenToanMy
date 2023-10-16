/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.service.impl;

import com.hmh.pojo.PhieuDangKy;
import com.hmh.pojo.PhieuKhamBenh;
import com.hmh.repository.PhieuKhamBenhRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hmh.service.PhieuDangKyService;

/**
 *
 * @author Asus
 */
@Service
public class PhieuDangKyServiceImpl implements PhieuDangKyService {

    @Autowired
    private PhieuKhamBenhRepository phieuKhamBenhRepository;
    
    @Override
    public List<PhieuDangKy> getPhieuDangKy() {
        return this.phieuKhamBenhRepository.getPhieuDangKy();
    }

}
