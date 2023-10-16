/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.repository.impl;

import com.hmh.pojo.HoaDon;
import com.hmh.pojo.PhieuDangKy;
import com.hmh.pojo.PhieuKhamBenh;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Transactional;
import com.hmh.repository.ThongKeBenhNhanRepository;

/**
 *
 * @author Asus
 */
@Repository
@Transactional
public class ThongKeBenhNhanRepositoryImpl implements ThongKeBenhNhanRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<PhieuDangKy> loadDS(int year, int month) {
        Session s = this.factory.getObject().getCurrentSession();

        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<PhieuDangKy> query = builder.createQuery(PhieuDangKy.class);
        Root root = query.from(PhieuDangKy.class);

        Predicate yearPre = builder.equal(builder.function("YEAR", Integer.class, root.get("chonNgaykham")), year);
        Predicate monthPre = builder.equal(builder.function("MONTH", Integer.class, root.get("chonNgaykham")), month);

        query = query.select(root).where(builder.and(yearPre, monthPre));
        Query q = s.createQuery(query);

        return q.getResultList();
    }

    @Override
    public List<PhieuDangKy> loadDsTheoQuy(int nam, int quy) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PhieuDangKy> query = builder.createQuery(PhieuDangKy.class);
        Root root = query.from(PhieuDangKy.class);

        int startMonth = (quy - 1) * 3 + 1;
        int endMonth = startMonth + 2;

        Predicate yearPre = builder.equal(builder.function("YEAR", Integer.class, root.get("chonNgaykham")), nam);
        Predicate monthPre = builder.between(builder.function("MONTH", Integer.class, root.get("chonNgaykham")), startMonth, endMonth);

        query = query.select(root).where(builder.and(yearPre, monthPre));
        Query q = session.createQuery(query);
        return q.getResultList();
    }

    

}
