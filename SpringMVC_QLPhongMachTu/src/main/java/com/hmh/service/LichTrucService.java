/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.hmh.service;

import com.hmh.pojo.ChiTietThoiGianTruc;
import com.hmh.pojo.TaiKhoan;
import com.hmh.pojo.ThoiGianTruc;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author LENOVO
 */
public interface LichTrucService {

    List<TaiKhoan> getTkYtaBs();

    List<ChiTietThoiGianTruc> getChiTietTgTruc();

    List<ChiTietThoiGianTruc> getLich(Date fromDate);

    List<ThoiGianTruc> getTg();

    boolean addAndUpdate(ChiTietThoiGianTruc tg, TaiKhoan idTk, List<Date> date, List<Integer> idtgTruc);

    public ChiTietThoiGianTruc getChiTietThoiGianTrucById(int id);

    List<Object> getChiTietThoiGianTrucByIDTK(TaiKhoan idTk);

    List<ChiTietThoiGianTruc> getChiTietTgtByidTk(TaiKhoan id);

    boolean xoaLichTruc(int id);

    boolean update(ChiTietThoiGianTruc tg);
}
