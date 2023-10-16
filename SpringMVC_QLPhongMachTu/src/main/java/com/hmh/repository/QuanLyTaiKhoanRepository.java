/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.repository;

import com.hmh.pojo.ChiTietDv;
import com.hmh.pojo.ChiTietThoiGianTruc;
import com.hmh.pojo.HoaDon;
import com.hmh.pojo.PhieuDangKy;
import com.hmh.pojo.TaiKhoan;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asus
 */
public interface QuanLyTaiKhoanRepository {

    List<TaiKhoan> getTaiKhoanAdmin(String username);

    boolean themTaiKhoan(TaiKhoan tk);

    TaiKhoan getTaiKhoanById(int id);

    boolean xoaTaiKhoan(int id);

    List<TaiKhoan> timKiemTK(Map<String, String> params);

    //XOA ACCOUNT
    public List<PhieuDangKy> getPhieuDangKyByTK(int idTaiKhoan);

    public boolean xoaPhieuDangKyByTK(int idTaiKhoan);
    
    public List<ChiTietThoiGianTruc> getCTThoiGianTrucByTK(int idTaiKhoan);

    public boolean xoaCTThoiGianTrucByTK(int idTaiKhoan);

    List<ChiTietDv> getChiTietDichVuByPdk(int idTaiKhoan);

    boolean xoaCTDichVuBypdky(int idTaiKhoan);

    public List<HoaDon> getHoaDonByPDK(int idTaiKhoan);

    boolean xoaHoaDonByPDK(int idTaiKhoan);
}
