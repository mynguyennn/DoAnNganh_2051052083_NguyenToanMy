/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.repository.impl;

import com.hmh.pojo.ChiTietDv;
import com.hmh.pojo.PhieuDangKy;
import com.hmh.repository.ChiTietDVRepository;
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
public class ChiTietDVRepositoryImpl implements ChiTietDVRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public boolean themVaCapNhat(ChiTietDv dv, int idPdk) {
        Session session = this.factory.getObject().getCurrentSession();
        PhieuDangKy pdk = session.get(PhieuDangKy.class, idPdk);

        try {
            if (dv.getIdchitietDV() == null) {
                session.save(dv);
                dv.setIdPdk(pdk);

            } else {
                session.update(dv);
            }

            return true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        }

        return false;
    }
}
