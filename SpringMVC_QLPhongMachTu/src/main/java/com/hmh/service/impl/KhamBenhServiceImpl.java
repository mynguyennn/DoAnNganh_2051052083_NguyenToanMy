/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.service.impl;

import com.hmh.pojo.ChiTietDv;
import com.hmh.pojo.DichVu;
import com.hmh.pojo.PhieuDangKy;
import com.hmh.pojo.PhieuKhamBenh;
import com.hmh.repository.KhamBenhRepository;
import com.hmh.service.KhamBenhService;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Asus
 */
@Service
public class KhamBenhServiceImpl implements KhamBenhService {

    @Autowired
    private KhamBenhRepository khamBenhRepository;

    @Override
    public PhieuDangKy getPDK(int id) {
        return this.khamBenhRepository.getPDK(id);
    }

    @Override
    public List<DichVu> getDichVu() {
        return this.khamBenhRepository.getDichVu();
    }

    @Override
    public List<PhieuDangKy> getLichSuKham(Map<String, String> params, int idBn) {
        return this.khamBenhRepository.getLichSuKham(params, idBn);
    }

    @Override
    public boolean themPhieuKhamBenh(PhieuKhamBenh pkb, int idPdk) {
        java.util.Date currentDate = new java.util.Date();
        Timestamp timestamp = new Timestamp(currentDate.getTime());

//        int newIdPKB = pkb.getIdPhieukham();
//        PhieuDangKy phieuDangKy = this.khamBenhRepository.getPDK(id);
//        
//        phieuDangKy.setIdPk(pkb);
        pkb.setNgayKhamBenh(timestamp);

        return this.khamBenhRepository.themPhieuKhamBenh(pkb, idPdk);
    }

    @Override
    public List<ChiTietDv> getDvByIdPdk(int id) {
        return this.khamBenhRepository.getDvByIdPdk(id);
    }

    @Override
    public List<PhieuDangKy> getPkbyIdPdk(int id) {
        return this.khamBenhRepository.getPkByIdPdk(id);
    }

}
