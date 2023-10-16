/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.service.impl;

import com.hmh.pojo.ChiTietDv;
import com.hmh.pojo.ChiTietThuoc;
import com.hmh.pojo.HoaDon;
import com.hmh.pojo.PhieuDangKy;
import com.hmh.pojo.PhieuKhamBenh;
import com.hmh.pojo.Thuoc;
import com.hmh.pojo.TienKham;
import com.hmh.repository.CapThuocRepository;
import com.hmh.repository.KhamBenhRepository;
import com.hmh.repository.LapDsKhamRepository;
import com.hmh.service.CapThuocService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Asus
 */
@Service
public class CapThuocServiceImpl implements CapThuocService {

    @Autowired
    private CapThuocRepository capThuocRepository;

    @Autowired
    private LapDsKhamRepository lapDsKhamRepository;

    @Autowired
    private KhamBenhRepository khamBenhRepository;

    @Override
    public List<Thuoc> getListThuoc(Map<String, String> params) {
        return this.capThuocRepository.getListThuoc(params);
    }

    @Override
    public List<Thuoc> timKiemThuoc(Map<String, String> params) {
        return this.capThuocRepository.timKiemThuoc(params);
    }

    @Override
    public boolean themPhieuThuoc(ChiTietThuoc ctThuoc, int idPhieuKham) {
        PhieuDangKy phieuDangKy = lapDsKhamRepository.getPhieuDangKyById(idPhieuKham);

        PhieuKhamBenh phieuKhamBenh = phieuDangKy.getIdPk();
        ctThuoc.setIdPhieukham(phieuKhamBenh);

        return this.capThuocRepository.themPhieuThuoc(ctThuoc, idPhieuKham);
    }

    @Override
    public ChiTietThuoc getChiTietThuocById(int id) {
        return this.capThuocRepository.getChiTietThuocById(id);
    }

    @Override
    public List<ChiTietThuoc> layThuocByPhieuDangKyId(int idPDK) {
        return this.capThuocRepository.layThuocByPhieuDangKyId(idPDK);
    }

    @Override
    public boolean themHoaDonByPDK(HoaDon hd, int idPDK) {

        PhieuDangKy phieuDangKy = this.khamBenhRepository.getPDK(idPDK);

        //tong tien thuoc
        BigDecimal tongTienThuoc = BigDecimal.ZERO;

        List<ChiTietThuoc> danhSachThuoc = capThuocRepository.layThuocByPhieuDangKyId(idPDK);
        for (ChiTietThuoc t : danhSachThuoc) {
            BigDecimal soLuongSd = new BigDecimal(t.getSoLuongSd());
            BigDecimal giaThuoc = new BigDecimal(t.getIdThuoc().getGiaThuoc());
            BigDecimal tienThuoc = soLuongSd.multiply(giaThuoc);
            tongTienThuoc = tongTienThuoc.add(tienThuoc);
        }
        long tienThuocLong = tongTienThuoc.setScale(0, RoundingMode.HALF_UP).longValue();

        //tong tien dich vu
        BigDecimal tongTienDV = BigDecimal.ZERO;

        List<ChiTietDv> danhSachDV = khamBenhRepository.getDvByIdPdk(idPDK);
        for (ChiTietDv dv : danhSachDV) {
            BigDecimal giaDV = new BigDecimal(dv.getIdDv().getGiaDv());
            tongTienDV = tongTienDV.add(giaDV);
        }

        long tongTienDVLong = tongTienDV.setScale(0, RoundingMode.HALF_UP).longValue();

        TienKham tienKham = new TienKham();
        tienKham.setIdtienKham(1);

        hd.setTienKham(tienKham);
        hd.setIdPhieudky(phieuDangKy);
        hd.setTienThuoc(tienThuocLong);
        hd.setTienDv(tongTienDVLong);

//        hd.setTienThuoc();
        return this.capThuocRepository.themHoaDonByPDK(hd, idPDK);
    }

    @Override
    public TienKham getTienKham(int tienKham) {
        return this.capThuocRepository.getTienKham(tienKham);
    }

}
