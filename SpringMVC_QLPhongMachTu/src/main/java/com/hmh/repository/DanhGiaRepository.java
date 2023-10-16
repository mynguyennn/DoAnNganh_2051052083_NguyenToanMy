/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.hmh.repository;

import com.hmh.pojo.DanhGiaBs;
import com.hmh.pojo.PhieuDangKy;
import com.hmh.pojo.TaiKhoan;
import java.util.List;
import java.util.Map;

/**
 *
 * @author LENOVO
 */
public interface DanhGiaRepository {
    public PhieuDangKy getPdkById(int id);
    
    boolean  luuDanhGia(int id, DanhGiaBs dg, int tk);
    
    List<DanhGiaBs> getDgByIdBs(int id, Map<String, String> params);
    
    int demComment();
   
    
}
