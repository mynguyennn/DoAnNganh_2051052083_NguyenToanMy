/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.repository.impl;

import com.hmh.pojo.PhieuDangKy;
import com.hmh.repository.LapPhieuKhamRepository;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Asus
 */
@Repository
@Transactional
public class LapPhieuKhamRepositoryImpl implements LapPhieuKhamRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<PhieuDangKy> getPhieuDangKy(int idBs) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM PhieuDangKy WHERE trangThaidky = :trangThaidky AND idBs.idTk = :idBs");
        q.setParameter("trangThaidky", (short) 1);
         q.setParameter("idBs", idBs);

        return q.getResultList();

    }

    
}
