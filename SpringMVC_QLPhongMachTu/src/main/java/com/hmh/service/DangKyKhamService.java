/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.service;

import com.hmh.pojo.DichVu;
import com.hmh.pojo.PhieuDangKy;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asus
 */
public interface DangKyKhamService {
    List<PhieuDangKy> getPhieuById(int tk);
  
}
