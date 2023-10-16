/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.repository;

import com.hmh.pojo.ChiTietThuoc;
import com.hmh.pojo.HoaDon;
import com.hmh.pojo.PhieuDangKy;
import com.hmh.pojo.PhieuKhamBenh;
import com.hmh.pojo.TaiKhoan;
import com.hmh.pojo.Thuoc;
import com.hmh.pojo.TienKham;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asus
 */
public interface CapThuocRepository {

    List<Thuoc> getListThuoc(Map<String, String> params);

    List<Thuoc> timKiemThuoc(Map<String, String> params);

    boolean themPhieuThuoc(ChiTietThuoc ctThuoc, int idPhieuKham);

    public ChiTietThuoc getChiTietThuocById(int id);

    List<ChiTietThuoc> layThuocByPhieuDangKyId(int idPDK);
    
    boolean themHoaDonByPDK(HoaDon hd, int idPDK);
    
    TienKham getTienKham(int tienKham);
}
