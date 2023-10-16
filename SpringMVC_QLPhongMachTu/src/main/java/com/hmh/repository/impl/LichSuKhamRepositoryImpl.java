/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.repository.impl;

import com.hmh.pojo.PhieuDangKy;
import com.hmh.pojo.TaiKhoan;
import com.hmh.repository.LichSuKhamRepository;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.HibernateException;
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
public class LichSuKhamRepositoryImpl implements LichSuKhamRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Object> getPhieuDangKy(TaiKhoan idBn) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("From PhieuDangKy Where idBn =: idBn");

        q.setParameter("idBn", idBn);
        return q.getResultList();
    }

    @Override
    public PhieuDangKy getLsKhamId(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        return session.get(PhieuDangKy.class, id);
    }

    @Override
    public boolean xoaLsKham(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        PhieuDangKy pdk = this.getLsKhamId(id);
        try {
            session.delete(pdk);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

//    @Override
//    public void themPDK(PhieuDangKy pdk) {
//        Session s = this.factory.getObject().getCurrentSession();
//        s.delete(pdk);
//    }

}
