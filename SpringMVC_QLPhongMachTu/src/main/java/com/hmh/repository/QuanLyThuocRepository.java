/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.repository;

import com.hmh.pojo.DichVu;
import com.hmh.pojo.DonviThuoc;
import com.hmh.pojo.LoaiThuoc;
import com.hmh.pojo.Thuoc;
import com.hmh.pojo.TienKham;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asus
 */
public interface QuanLyThuocRepository {

    List<Thuoc> getThuoc(String name);

    boolean themThuoc(Thuoc thuoc);

    Thuoc getThuocById(int id);

    boolean xoaThuoc(int id);

    List<DonviThuoc> getDonViThuoc();

    TienKham getTienKham();

    public TienKham thaydoiTienKham(TienKham tienKham);

    TienKham getTienKhamById(int id);

    boolean themDichVu(DichVu dv);

    List<Thuoc> timKiemThuoc(Map<String, String> params);

    List<LoaiThuoc> getLoaiThuoc();

    Thuoc getThuocByLoaiThuoc(int id);

}
