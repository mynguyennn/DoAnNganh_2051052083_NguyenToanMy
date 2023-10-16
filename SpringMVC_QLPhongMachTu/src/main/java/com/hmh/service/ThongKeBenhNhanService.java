/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.service;

import com.hmh.pojo.HoaDon;
import com.hmh.pojo.PhieuDangKy;
import java.util.List;

/**
 *
 * @author Asus
 */
public interface ThongKeBenhNhanService{

    List<PhieuDangKy> loadDS(int year, int month);

    List<PhieuDangKy> loadDsTheoQuy(int nam, int quy);
    
    
}
