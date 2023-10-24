/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.service.impl;

import com.hmh.pojo.DanhGiaBs;
import com.hmh.pojo.PhieuDangKy;
import com.hmh.pojo.TaiKhoan;
import com.hmh.repository.DanhGiaRepository;
import com.hmh.service.DanhGiaService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LENOVO
 */
@Service
public class DanhGiaServiceImpl implements DanhGiaService{

    @Autowired 
    public DanhGiaRepository danhGiaRepository;
    
    @Override
    public PhieuDangKy getPdkById(int id) {
        return this.danhGiaRepository.getPdkById(id);
    }

    @Override
    public boolean luuDanhGia(int id, DanhGiaBs dg, int tk, int idPdk) {
//        dg.setDanhGia(dg.getDanhGia());
       return this.danhGiaRepository.luuDanhGia(id, dg, tk, idPdk);
    }

    @Override
    public List<DanhGiaBs> getDgByIdBs(int id, Map<String, String> params) {
        return this.danhGiaRepository.getDgByIdBs(id,params );
    }

    @Override
    public int demComment() {
       return this.danhGiaRepository.demComment();
    }

    @Override
    public List<DanhGiaBs> getDanhGia(int id) {
       return  this.danhGiaRepository.getDanhGia(id);
    }
    
}
