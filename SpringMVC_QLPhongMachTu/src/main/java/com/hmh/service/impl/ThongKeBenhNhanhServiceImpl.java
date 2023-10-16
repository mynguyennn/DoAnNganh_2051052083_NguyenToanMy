/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.service.impl;

import com.hmh.pojo.HoaDon;
import com.hmh.pojo.PhieuDangKy;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hmh.repository.ThongKeBenhNhanRepository;
import com.hmh.service.ThongKeBenhNhanService;

/**
 *
 * @author LENOVO
 */
@Service
public class ThongKeBenhNhanhServiceImpl implements ThongKeBenhNhanService {

    @Autowired
    private ThongKeBenhNhanRepository thongKeRepository;

    @Override
    public List<PhieuDangKy> loadDS(int year, int month) {
        return this.thongKeRepository.loadDS(year, month);
    }

    @Override
    public List<PhieuDangKy> loadDsTheoQuy(int nam, int quy) {
        return this.thongKeRepository.loadDsTheoQuy(nam, quy);
    }

 

}
