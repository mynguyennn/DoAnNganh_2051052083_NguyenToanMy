/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.repository.impl;

import com.hmh.pojo.DichVu;
import com.hmh.pojo.DonviThuoc;
import com.hmh.pojo.LoaiThuoc;
import com.hmh.pojo.TaiKhoan;
import com.hmh.pojo.Thuoc;
import com.hmh.pojo.TienKham;
import com.hmh.repository.QuanLyThuocRepository;
import java.util.List;
import java.util.Map;
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

/**
 *
 * @author Asus
 */
@Repository
@Transactional
public class QuanLyThuocRepositoryImpl implements QuanLyThuocRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactoryBean;

    @Override
    public List<Thuoc> getThuoc(String name) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Thuoc> query = builder.createQuery(Thuoc.class);
        Root root = query.from(Thuoc.class);
        query = query.select(root);
        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public boolean themThuoc(Thuoc thuoc) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();

        try {
            if (thuoc.getIdThuoc() == null) {
                session.save(thuoc);
            } else {
                session.update(thuoc);
            }

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Thuoc getThuocById(int id) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        return session.get(Thuoc.class, id);
    }

    @Override
    public boolean xoaThuoc(int id) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        Thuoc t = this.getThuocById(id);
        try {
            session.delete(t);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<DonviThuoc> getDonViThuoc() {
        Session s = this.sessionFactoryBean.getObject().getCurrentSession();
        javax.persistence.Query q = s.createQuery("From DonviThuoc");

        return q.getResultList();
    }

    @Override
    public TienKham getTienKham() {
        Session s = this.sessionFactoryBean.getObject().getCurrentSession();
        javax.persistence.Query q = s.createQuery("From TienKham");

        return (TienKham) q.getSingleResult();
    }

    @Override
    public TienKham thaydoiTienKham(TienKham tienKham) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        if (tienKham.getIdtienKham() != null) {
            session.update(tienKham);
        }
        return tienKham;
    }

    @Override
    public TienKham getTienKhamById(int id) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        return session.get(TienKham.class, id);
    }

    @Override
    public boolean themDichVu(DichVu dv) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();

        try {
            if (dv.getIdDv() == null) {
                session.save(dv);
            } else {
                session.update(dv);
            }

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Thuoc> timKiemThuoc(Map<String, String> params) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Thuoc> query = builder.createQuery(Thuoc.class);
        Root root = query.from(Thuoc.class);
        query = query.select(root);

        if (params != null) {
            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                Predicate p1 = builder.like(root.get("tenThuoc"), String.format("%%%s%%", kw));
                query.where(p1);
            }
        }

        javax.persistence.Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public List<LoaiThuoc> getLoaiThuoc() {
        Session s = this.sessionFactoryBean.getObject().getCurrentSession();
        javax.persistence.Query q = s.createQuery("From LoaiThuoc");

        return q.getResultList();
    }

    @Override
    public Thuoc getThuocByLoaiThuoc(int id) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Thuoc> query = builder.createQuery(Thuoc.class);
        Root<Thuoc> root = query.from(Thuoc.class);
        query.where(builder.equal(root.get("idThuoc"), id));
        Query q = session.createQuery(query);
        List<Thuoc> results = q.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

}
