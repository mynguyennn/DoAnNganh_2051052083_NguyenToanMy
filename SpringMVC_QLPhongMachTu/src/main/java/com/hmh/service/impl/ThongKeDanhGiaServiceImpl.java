/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.service.impl;

import com.hmh.pojo.DanhGiaBs;
import com.hmh.pojo.TaiKhoan;
import com.hmh.repository.ThongKeDanhGiaRepository;
import com.hmh.service.ThongKeDanhGiaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LENOVO
 */
@Service
public class ThongKeDanhGiaServiceImpl implements ThongKeDanhGiaService{
    @Autowired
    private ThongKeDanhGiaRepository thongKeRepository;

    @Override
    public List<Integer> getDgByIdBs(int id) {
        return this.thongKeRepository.getDgByIdBs(id);
    }

    @Override
    public List<Integer> getBacSi() {
        return this.thongKeRepository.getBacSi();
    }
}
