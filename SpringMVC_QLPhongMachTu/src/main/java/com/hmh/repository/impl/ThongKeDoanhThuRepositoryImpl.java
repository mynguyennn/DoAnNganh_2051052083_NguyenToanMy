/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.repository.impl;

import com.hmh.pojo.HoaDon;
import com.hmh.pojo.PhieuDangKy;
import com.hmh.repository.ThongKeDoanhThuRepository;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
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
public class ThongKeDoanhThuRepositoryImpl implements ThongKeDoanhThuRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<HoaDon> loadHoaDon(int year) {
        Session s = this.factory.getObject().getCurrentSession();

        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<HoaDon> query = builder.createQuery(HoaDon.class);
        Root<HoaDon> root = query.from(HoaDon.class);

        Predicate yearPre = builder.equal(builder.function("YEAR", Integer.class, root.get("ngayThanhToan")), year);
//        Predicate monthPre = builder.equal(builder.function("MONTH", Integer.class, root.get("ngayThanhToan")), month);

        query = query.select(root).where(builder.and(yearPre));
        Query q = s.createQuery(query);

        List<HoaDon> hoaDonList = q.getResultList();

        return hoaDonList;
    }

    @Override
    public List<HoaDon> loadDsTheoQuy(int nam, int quy) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<HoaDon> query = builder.createQuery(HoaDon.class);
        Root root = query.from(HoaDon.class);

        int startMonth = (quy - 1) * 3 + 1;
        int endMonth = startMonth + 2;

        Predicate yearPre = builder.equal(builder.function("YEAR", Integer.class, root.get("ngayThanhToan")), nam);
        Predicate monthPre = builder.between(builder.function("MONTH", Integer.class, root.get("ngayThanhToan")), startMonth, endMonth);

        query = query.select(root).where(builder.and(yearPre, monthPre));
        Query q = session.createQuery(query);
        return q.getResultList();
    }
}
