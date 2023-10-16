/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.service.impl;

import com.hmh.pojo.PhieuDangKy;
import com.hmh.pojo.TaiKhoan;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hmh.repository.LapDsKhamRepository;
import com.hmh.repository.TaiKhoanRepository;
import com.hmh.service.LapDsKhamService;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

/**
 *
 * @author Asus
 */
@Service
public class LapDsKhamServiceImpl implements LapDsKhamService {

    @Autowired
    private LapDsKhamRepository lapDsKhamRepository;

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Override
    public List<PhieuDangKy> getPhieuDangKy(Map<String, String> params) {
        return this.lapDsKhamRepository.getPhieuDangKy(params);
    }

    @Override
    public List<TaiKhoan> getBacSi() {
        return this.lapDsKhamRepository.getBacSi();
    }

    @Override
    public Boolean trangThai(int id, TaiKhoan tk) {
        return this.lapDsKhamRepository.trangThai(id, tk);
    }

    @Override
    public boolean themPhieuDangKy(PhieuDangKy pdk) {
        TaiKhoan tk = taiKhoanRepository.getTaiKhoan(pdk.getTenBenhNhanDky()).get(0);
        Date ngayGioHienTai = new Date();
        Timestamp thoiGianTaoPhieu = new Timestamp(ngayGioHienTai.getTime());

        pdk.setThoiGianTaophieu(thoiGianTaoPhieu);
        pdk.setIdBn(tk);
        pdk.setTrangThaidky((short) 0);

        return this.lapDsKhamRepository.themPhieuDangKy(pdk);
    }

    @Override
    public List<PhieuDangKy> timKiemPDK(Map<String, String> params) {
        return lapDsKhamRepository.timKiemPDK(params);
    }

    @Override
    public PhieuDangKy getPhieuDangKyById(int id) {
        return this.lapDsKhamRepository.getPhieuDangKyById(id);
    }

    @Override
    public boolean themVaCapNhat(PhieuDangKy pdk) {
        return this.lapDsKhamRepository.themVaCapNhat(pdk);
    }

//    @Override
//    public int demSoLuongXacNhanTrongNgay(LocalDate ngayHienTai) {
//        return this.lapDsKhamRepository.demSoLuongXacNhanTrongNgay(ngayHienTai);
//    }
    @Override
    public List<PhieuDangKy> timKiemPDK_LSK(int idBn, Map<String, String> params) {
        return this.lapDsKhamRepository.timKiemPDK_LSK(idBn, params);
    }

    @Override
    public PhieuDangKy dangKyKhamAPI(Map<String, String> params, int idBn) {

        TaiKhoan tk = taiKhoanRepository.getTaiKhoanById(idBn);

        PhieuDangKy pdk = new PhieuDangKy();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date ngayGioHienTai = new Date();
        Timestamp thoiGianTaoPhieu = new Timestamp(ngayGioHienTai.getTime());
        Date parsedDate = null;

        try {
            parsedDate = dateFormat.parse(params.get("chonNgaykham"));
        } catch (ParseException ex) {
            Logger.getLogger(LapDsKhamServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        pdk.setIdBn(tk);
        pdk.setChonNgaykham(parsedDate);
        pdk.setThoiGianKham(params.get("thoiGianKham"));
        pdk.setThoiGianTaophieu(thoiGianTaoPhieu);
        pdk.setTrangThaidky((short) 0);

        this.lapDsKhamRepository.themPDK(pdk);
        return pdk;
    }

    @Override
    public List<PhieuDangKy> getPDKByIdTaiKhoan(int idBn) {
        return this.lapDsKhamRepository.getPDKByIdTaiKhoan(idBn);
    }

    @Override
    public boolean xoaPDK(int id) {
        return  this.lapDsKhamRepository.xoaPDK(id);
    }

    @Override
    public boolean xoaIdBn(int id) {
        return  this.lapDsKhamRepository.xoaIdBn(id);
    }

    @Override
    public boolean tuChoi(PhieuDangKy pdk) {
       return  this.lapDsKhamRepository.tuChoi(pdk);
    }
}
