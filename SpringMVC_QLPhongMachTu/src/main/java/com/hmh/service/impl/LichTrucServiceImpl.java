/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.service.impl;

import com.hmh.pojo.ChiTietThoiGianTruc;
import com.hmh.pojo.TaiKhoan;
import com.hmh.pojo.ThoiGianTruc;
import com.hmh.repository.LichTrucRepository;
import com.hmh.service.LichTrucService;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LENOVO
 */
@Service
public class LichTrucServiceImpl implements LichTrucService {

    @Autowired
    private LichTrucRepository lichTrucRepository;

    @Override
    public List<TaiKhoan> getTkYtaBs() {
        return this.lichTrucRepository.getTkYtaBs();
    }

    @Override
    public List<ChiTietThoiGianTruc> getChiTietTgTruc() {
        return this.lichTrucRepository.getChiTietTgTruc();
    }

    @Override
    public List<ChiTietThoiGianTruc> getLich(Date fromDate) {
        return this.lichTrucRepository.getLich(fromDate);
    }

    @Override
    public List<ThoiGianTruc> getTg() {
        return this.lichTrucRepository.getTg();
    }

    @Override
    public boolean addAndUpdate(ChiTietThoiGianTruc tg, TaiKhoan idTk, List<Date> date, List<Integer> idtgTruc) {
        return this.lichTrucRepository.addAndUpdate(tg, idTk, date, idtgTruc);
    }

    @Override
    public ChiTietThoiGianTruc getChiTietThoiGianTrucById(int id) {
        return this.lichTrucRepository.getChiTietThoiGianTrucById(id);
    }

    @Override
    public boolean xoaLichTruc(int id) {
        return this.lichTrucRepository.xoaLichTruc(id);
    }

    @Override
    public List<Object> getChiTietThoiGianTrucByIDTK(TaiKhoan idTk) {
        return this.lichTrucRepository.getChiTietThoiGianTrucByIDTK(idTk);

    }

    @Override
    public List<ChiTietThoiGianTruc> getChiTietTgtByidTk(TaiKhoan idTk) {
        return this.lichTrucRepository.getChiTietTgtByidTk(idTk);
    }

    @Override
    public boolean update(ChiTietThoiGianTruc tg) {
        return this.lichTrucRepository.update(tg);
    }

}
