/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.service.impl;

import com.hmh.repository.ThongKeDanhGiaRepository;
import com.hmh.service.ThongKeDanhGiaService;
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
}
