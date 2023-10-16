/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.controllers;

import com.hmh.pojo.PhieuDangKy;
import com.hmh.service.LapDsKhamService;
import com.hmh.service.PhieuDangKyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Asus
 */
@RestController
//@RequestMapping("/api")
public class ApiYTaController {

    @Autowired
    private LapDsKhamService lapDsKhamService;
    
    @Autowired
    private PhieuDangKyService phieuDangKyService;
    
    
    @DeleteMapping("/yta/lapdskham/tuchoi/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") int id) {
            this.lapDsKhamService.xoaPDK(id);
       
        
    }
    
}
