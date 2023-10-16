/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.repository.impl;

import com.hmh.pojo.ChiTietThuoc;
import com.hmh.pojo.HoaDon;
import com.hmh.pojo.PhieuDangKy;
import com.hmh.pojo.TaiKhoan;
import com.hmh.repository.ThanhToanRepository;
import com.hmh.service.ThanhToanService;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
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
public class ThanhToanRepositoryImpl implements ThanhToanRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactoryBean;

    @Autowired
    private ThanhToanService thanhToanService;

    @Override
    public List<HoaDon> getHoaDon(Map<String, String> params) {
        Session s = this.sessionFactoryBean.getObject().getCurrentSession();
        Query q = s.createQuery("From HoaDon");

        return q.getResultList();
    }

    @Override
    public HoaDon getHoaDonById(int id) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        return session.get(HoaDon.class, id);
    }

    @Override
    public boolean xacNhanHD(int idHd) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
//        HoaDon hd = session.get(HoaDon.class, idHd);

        HoaDon hd = this.thanhToanService.getHoaDonById(idHd);

//        LoaiThanhToan ltt =
        java.util.Date currentDate = new java.util.Date();

        Timestamp timestamp = new Timestamp(currentDate.getTime());

        try {
            if (hd.getIdHoadon() == null) {
                session.save(hd);
            } else {
//                hd.setLoaiThanhToan(loaiThanhToan);
                hd.setNgayThanhToan(timestamp);
                session.update(hd);
            }

            return true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        }

        return false;
    }

    @Override
    public List<HoaDon> timKiemPDK(Map<String, String> params) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<HoaDon> query = builder.createQuery(HoaDon.class);
        Root root = query.from(HoaDon.class);
        query = query.select(root);

        if (params != null) {
            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                Integer id = Integer.parseInt(kw);
                Predicate p1 = builder.equal(root.get("idPhieudky"), id);
                query.where(p1);
            }
        }

        Query q = session.createQuery(query);
        return q.getResultList();
    }

}
