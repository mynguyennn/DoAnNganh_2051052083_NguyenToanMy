/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.service.impl;

import com.hmh.pojo.ChiTietDv;
import com.hmh.repository.ChiTietDVRepository;
import com.hmh.service.ChiTietDVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Asus
 */
@Service
public class ChiTietDVServiceImpl implements ChiTietDVService {

    @Autowired
    private ChiTietDVRepository chiTietDVRepository;

    @Override
    public boolean themVaCapNhat(ChiTietDv dv, int idPdk) {
        return this.chiTietDVRepository.themVaCapNhat(dv, idPdk);
    }
}
