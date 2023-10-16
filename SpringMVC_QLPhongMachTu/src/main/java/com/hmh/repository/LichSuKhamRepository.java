/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.repository;

import com.hmh.pojo.PhieuDangKy;
import com.hmh.pojo.TaiKhoan;
import java.util.List;

/**
 *
 * @author Asus
 */


public interface LichSuKhamRepository {

    List<Object> getPhieuDangKy(TaiKhoan idBn);

    PhieuDangKy getLsKhamId(int id);

    boolean xoaLsKham(int id);
}
