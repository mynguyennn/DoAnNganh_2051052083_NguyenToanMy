/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.hmh.service;

import com.hmh.pojo.DanhGiaBs;
import com.hmh.pojo.TaiKhoan;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public interface ThongKeDanhGiaService {
    List<Integer> getDgByIdBs(int id);
    
    List<Integer> getBacSi();
}
