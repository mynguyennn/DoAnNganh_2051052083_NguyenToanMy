/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.controllers;

import com.hmh.pojo.TaiKhoan;
import com.hmh.service.LapDsKhamService;
import com.hmh.service.LichSuKhamService;
import com.hmh.service.TaiKhoanService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Asus
 */
@RestController
//@RequestMapping("/api")
public class ApiBenhNhanController {

    @Autowired
    private LichSuKhamService lichSuKhamService;

    @Autowired
    private TaiKhoanService taiKhoanService;

    @DeleteMapping("/benhnhan/lichsukham/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void xoaLsPhieuDky(@PathVariable(value = "id") int id) {
        this.lichSuKhamService.xoaLsKham(id);
    }

//    @PostMapping("/dangkykhamapi/")
//    @CrossOrigin
//    public ResponseEntity<TaiKhoan> updateTaikhoan(@RequestParam Map<String, String> params) {
////        if (this.taiKhoanService.authUser(params.get("taiKhoan").toString(), params.get("matKhau").toString()) == true) {
////            TaiKhoan a = taiKhoanService.thayDoiMatKhau(params);
////
////            return new ResponseEntity<>(a, HttpStatus.OK);
////        }
//
//        return new ResponseEntity<>(null, HttpStatus.CREATED);
//    }

}
