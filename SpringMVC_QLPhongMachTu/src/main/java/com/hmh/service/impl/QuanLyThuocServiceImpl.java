/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.service.impl;

import com.hmh.pojo.DichVu;
import com.hmh.pojo.DonviThuoc;
import com.hmh.pojo.LoaiThuoc;
import com.hmh.pojo.Thuoc;
import com.hmh.pojo.TienKham;
import com.hmh.repository.QuanLyThuocRepository;
import com.hmh.service.QuanLyThuocService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Asus
 */
@Service
public class QuanLyThuocServiceImpl implements QuanLyThuocService {

    @Autowired
    private QuanLyThuocRepository quanLyThuocRepository;

    @Override
    public List<Thuoc> getThuoc(String name) {
        return this.quanLyThuocRepository.getThuoc(name);
    }

    @Override
    public boolean themThuoc(Thuoc thuoc) {
        return this.quanLyThuocRepository.themThuoc(thuoc);
    }

    @Override
    public Thuoc getThuocById(int id) {
        return this.quanLyThuocRepository.getThuocById(id);
    }

    @Override
    public boolean xoaThuoc(int id) {
        return this.quanLyThuocRepository.xoaThuoc(id);
    }

    @Override
    public List<DonviThuoc> getDonViThuoc() {
        return this.quanLyThuocRepository.getDonViThuoc();
    }

    @Override
    public TienKham getTienKham() {
        return this.quanLyThuocRepository.getTienKham();
    }

    @Override
    public TienKham thaydoiTienKham(TienKham tienKham) {
        return this.quanLyThuocRepository.thaydoiTienKham(tienKham);
    }

    @Override
    public TienKham getTienKhamById(int id) {
        return this.quanLyThuocRepository.getTienKhamById(id);

    }

    @Override
    public boolean themDichVu(DichVu dv) {
        return this.quanLyThuocRepository.themDichVu(dv);
    }

    @Override
    public List<Thuoc> timKiemThuoc(Map<String, String> params) {
        return this.quanLyThuocRepository.timKiemThuoc(params);
    }

    @Override
    public List<LoaiThuoc> getLoaiThuoc() {
        return this.quanLyThuocRepository.getLoaiThuoc();
    }

    @Override
    public Thuoc getThuocByLoaiThuoc(int id) {
        return this.quanLyThuocRepository.getThuocByLoaiThuoc(id);
    }
}
