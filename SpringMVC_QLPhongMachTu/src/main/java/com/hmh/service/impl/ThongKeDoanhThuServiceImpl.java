/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.service.impl;

import com.hmh.pojo.HoaDon;
import com.hmh.repository.ThongKeDoanhThuRepository;
import com.hmh.service.ThongKeBenhNhanService;
import com.hmh.service.ThongKeDoanhThuService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Asus
 */
@Service
public class ThongKeDoanhThuServiceImpl implements ThongKeDoanhThuService {

    @Autowired
    private ThongKeDoanhThuRepository thongKeDoanhThuRepository;

    @Override
    public List<HoaDon> loadHoaDon(int year) {
        return this.thongKeDoanhThuRepository.loadHoaDon(year);
    }

    @Override
    public List<HoaDon> loadDsTheoQuy(int nam, int quy) {
        return this.thongKeDoanhThuRepository.loadDsTheoQuy(nam, quy);
    }
}
