/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.service.impl;

import com.hmh.repository.BenhNhanRepository;
import com.hmh.service.BenhNhanService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Asus
 */
@Service
public class BenhNhanServiceImpl implements BenhNhanService {

    @Autowired
    private BenhNhanRepository benhNhanRepository;

//    @Override
//    public List<BenhNhan> getBenhNhan(Map<String, String> params) {
//        return this.benhNhanRepository.getBenhNhan(params);
//    }

//    @Override
//    public boolean addOrUpdateBenhNhan(BenhNhan bn) {
//        return this.benhNhanRepository.addOrUpdateBenhNhan(bn);
//    }

}
