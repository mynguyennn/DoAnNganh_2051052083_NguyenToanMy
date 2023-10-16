/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.repository.impl;



import com.hmh.repository.BenhNhanRepository;

import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import javax.persistence.criteria.Root;
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
public class BenhNhanRepositoryImpl implements BenhNhanRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    

//    @Override
//    public List<BenhNhan> getBenhNhan(Map<String, String> params) {
//        Session session = this.factory.getObject().getCurrentSession();
//
//        CriteriaBuilder b = session.getCriteriaBuilder();
//        CriteriaQuery<BenhNhan> q = b.createQuery(BenhNhan.class);
//        Root root = q.from(BenhNhan.class);
//        q.select(root);
//
//        Query query = session.createQuery(q);
//
//        return query.getResultList();
//    }

//    @Override
//    public boolean addOrUpdateBenhNhan(BenhNhan bn) {
//        Session session = this.factory.getObject().getCurrentSession();
//        try {
//            if (bn.getIdBn() == null) {
//                session.save(bn);
//            }
//            return true;
//        } catch (HibernateException ex) {
//            ex.printStackTrace();
//        }
//        return false;
//    }

}
