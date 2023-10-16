/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.repository.impl;

import com.hmh.pojo.PhieuDangKy;
import com.hmh.repository.ThongKeRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Tuple;

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
 * @author LENOVO
 */
@Repository
@Transactional
public class ThongKeRepositoryImpl implements ThongKeRepository{

     @Autowired
    private LocalSessionFactoryBean factory;
    @Override
    public List<Integer> demBenhNhan() {
       List<Integer> thang = new ArrayList<>();
       try{
           Session session = this.factory.getObject().getCurrentSession();
           CriteriaBuilder builder = session.getCriteriaBuilder();
           CriteriaQuery<Tuple> query =  builder.createTupleQuery();
           Root<PhieuDangKy> root = query.from(PhieuDangKy.class);
           query.multiselect(builder.function("MOTH", Integer.class, root.get("chonNgaykham")).alias("moth"), builder.count(root).alias("count"));
           query.groupBy(builder.function("MOTH", Integer.class, root.get("")));
       }
       catch(HibernateException ex)
       {
           ex.printStackTrace();
       }
       
       return thang;
        }
    
}
