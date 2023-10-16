/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.service.impl;

import com.hmh.pojo.PhieuDangKy;
import com.hmh.pojo.TaiKhoan;
import com.hmh.repository.LichSuKhamRepository;
import com.hmh.service.LichSuKhamService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Asus
 */
@Service
public class LichSuKhamServiceImpl implements LichSuKhamService {

    @Autowired
    private LichSuKhamRepository lichSuKhamRepository;

    @Override
    public List<Object> getPhieuDangKy(TaiKhoan idBn) {
        return lichSuKhamRepository.getPhieuDangKy(idBn);
    }

    @Override
    public PhieuDangKy getLsKhamId(int id) {
        return this.lichSuKhamRepository.getLsKhamId(id);
    }

    @Override
    public boolean xoaLsKham(int id) {
        return this.lichSuKhamRepository.xoaLsKham(id);
    }

}
