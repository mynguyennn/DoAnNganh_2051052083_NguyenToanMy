/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.service;

import com.hmh.pojo.HoaDon;
import com.hmh.pojo.PhieuDangKy;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asus
 */
public interface ThanhToanService {

    List<HoaDon> getHoaDon(Map<String, String> params);

    HoaDon getHoaDonById(int id);
    
    boolean xacNhanHD(int idHd);
    
    List<HoaDon> timKiemPDK(Map<String, String> params);
}
