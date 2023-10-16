/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.repository.impl;

import com.hmh.pojo.ChiTietThuoc;
import com.hmh.pojo.HoaDon;
import com.hmh.pojo.PhieuDangKy;
import com.hmh.pojo.PhieuKhamBenh;
import com.hmh.pojo.TaiKhoan;
import com.hmh.pojo.Thuoc;
import com.hmh.pojo.TienKham;
import com.hmh.pojo.UserRole;
import com.hmh.repository.CapThuocRepository;
import com.hmh.repository.KhamBenhRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
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
public class CapThuocRepositoryImpl implements CapThuocRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private KhamBenhRepository khamBenhRepository;

    @Override
    public List<Thuoc> getListThuoc(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        Query query = session.createQuery("From Thuoc");

        return query.getResultList();
    }

    @Override
    public List<Thuoc> timKiemThuoc(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
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
    public boolean themPhieuThuoc(ChiTietThuoc ctThuoc, int idPhieuKham) {
        Session session = this.factory.getObject().getCurrentSession();
        ChiTietThuoc ctt = session.get(ChiTietThuoc.class, idPhieuKham);

        try {
            if (ctThuoc.getIdChitietThuoc() == null) {
                session.save(ctThuoc);
//                ctt.setIdPhieukham(idPhieuKham);
            } else {
                session.update(ctThuoc);
            }

            return true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        }

        return false;
    }

    @Override
    public ChiTietThuoc getChiTietThuocById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ChiTietThuoc> query = builder.createQuery(ChiTietThuoc.class);
        Root<ChiTietThuoc> root = query.from(ChiTietThuoc.class);
        query.where(builder.equal(root.get("idChitietThuoc"), id));
        javax.persistence.Query q = session.createQuery(query);
        List<ChiTietThuoc> results = q.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public List<ChiTietThuoc> layThuocByPhieuDangKyId(int idPDK) {

        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        List<Predicate> predicates = new ArrayList<>();
        Root chiTietThuoc = q.from(ChiTietThuoc.class);
        q.select(chiTietThuoc);

        PhieuDangKy pdk = khamBenhRepository.getPDK(idPDK);
        Predicate p = b.equal(chiTietThuoc.get("idPhieukham"), pdk.getIdPk());
        predicates.add(p);
        q.where(predicates.toArray(Predicate[]::new));

        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public boolean themHoaDonByPDK(HoaDon hd, int idPDK) {
        Session session = this.factory.getObject().getCurrentSession();

        try {
            if (hd.getIdHoadon() == null) {
                session.save(hd);
            } else {
                session.update(hd);
            }

            return true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        }

        return false;
    }

    @Override
    public TienKham getTienKham(int tienKham) {
        Session session = this.factory.getObject().getCurrentSession();
        javax.persistence.Query q = session.createQuery("FROM TienKham WHERE tienKham = :tienKham");
        q.setParameter("tienKham", tienKham);
        return (TienKham) q.getSingleResult();
    }

}
