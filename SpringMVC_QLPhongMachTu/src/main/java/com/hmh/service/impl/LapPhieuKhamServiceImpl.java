/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.service.impl;

import com.hmh.pojo.PhieuDangKy;
import com.hmh.repository.LapDsKhamRepository;
import com.hmh.repository.LapPhieuKhamRepository;
import com.hmh.service.LapPhieuKhamService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Asus
 */
@Service
public class LapPhieuKhamServiceImpl implements LapPhieuKhamService {

    @Autowired
    private LapPhieuKhamRepository lapPhieuKhamRepository;

    @Override
    public List<PhieuDangKy> getPhieuDangKy(int idBs) {
        return this.lapPhieuKhamRepository.getPhieuDangKy(idBs);
    }
    
    

}
