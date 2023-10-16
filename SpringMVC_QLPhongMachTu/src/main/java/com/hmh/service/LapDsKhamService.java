/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.service;

import com.hmh.pojo.PhieuDangKy;
import com.hmh.pojo.TaiKhoan;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asus
 */
public interface LapDsKhamService {

    List<PhieuDangKy> getPhieuDangKy(Map<String, String> params);

    public List<TaiKhoan> getBacSi();

    Boolean trangThai(int id, TaiKhoan tk);

    boolean themPhieuDangKy(PhieuDangKy pdk);

    List<PhieuDangKy> timKiemPDK(Map<String, String> params);

    public PhieuDangKy getPhieuDangKyById(int id);

    boolean themVaCapNhat(PhieuDangKy pdk);

    List<PhieuDangKy> timKiemPDK_LSK(int idBn, Map<String, String> params);

    PhieuDangKy dangKyKhamAPI(Map<String, String> params, int idBn);
    
    List<PhieuDangKy> getPDKByIdTaiKhoan(int idBn);
    
    boolean xoaPDK(int id);

   boolean xoaIdBn(int id);
    
   boolean tuChoi(PhieuDangKy pdk);
}
