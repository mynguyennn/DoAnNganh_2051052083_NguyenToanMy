/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.repository.impl;

import com.hmh.pojo.DichVu;
import com.hmh.pojo.PhieuDangKy;
import com.hmh.pojo.TaiKhoan;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.hmh.repository.LapDsKhamRepository;
import com.hmh.repository.UserRoleRepository;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;

/**
 *
 * @author Asus
 */
@Repository
@Transactional
public class LapDsKhamRepositoryImpl implements LapDsKhamRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<PhieuDangKy> getPhieuDangKy(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("From PhieuDangKy");

        return q.getResultList();
    }

    @Override
    public List<TaiKhoan> getBacSi() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("From TaiKhoan Where idRole=2");

        return q.getResultList();
    }

    @Override
    public Boolean trangThai(int id, TaiKhoan tk) {
        Session session = this.factory.getObject().getCurrentSession();
        PhieuDangKy pdk = session.get(PhieuDangKy.class, id);
        try {
            if (pdk.getTrangThaidky() == 1) {
                pdk.setTrangThaidky((short) 0);
                pdk.setIdYt(null);
                pdk.setIdBs(null);
            } else {
                pdk.setTrangThaidky((short) 1);
                pdk.setIdYt(tk);
                pdk.setIdBs(tk);
            }
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean themPhieuDangKy(PhieuDangKy pdk) {
        Session session = this.factory.getObject().getCurrentSession();

        try {
            session.save(pdk);
            return true;
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    @Override
    public TaiKhoan getIdBacSi(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TaiKhoan> query = builder.createQuery(TaiKhoan.class);
        Root<TaiKhoan> root = query.from(TaiKhoan.class);
        query.where(
                builder.and(
                        builder.equal(root.get("idTk"), id))
        );
        Query q = session.createQuery(query);
        List<TaiKhoan> results = q.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public List<PhieuDangKy> timKiemPDK(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PhieuDangKy> query = builder.createQuery(PhieuDangKy.class);
        Root<PhieuDangKy> root = query.from(PhieuDangKy.class);
        query = query.select(root);

        if (params != null) {
            String kwDate = params.get("kwDate");
            if (kwDate != null && !kwDate.isEmpty()) {
                Date date = Date.valueOf(kwDate);
                Predicate p1 = builder.equal(root.get("chonNgaykham"), date);
                query.where(p1);
            }
        }

        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public List<PhieuDangKy> timKiemPDK_LSK(int idBn, Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PhieuDangKy> query = builder.createQuery(PhieuDangKy.class);
        Root<PhieuDangKy> root = query.from(PhieuDangKy.class);
        query = query.select(root);

        Predicate finalPredicate = null;
        String kwDate = params.get("kwDate");

        Predicate idBnPredicate = builder.equal(root.get("idBn"), idBn);
        finalPredicate = idBnPredicate;

        if (kwDate != null && !kwDate.isEmpty()) {
            try {
                Date date = Date.valueOf(kwDate);
                Predicate p1 = builder.equal(root.get("chonNgaykham"), date);
                if (finalPredicate != null) {
                    finalPredicate = builder.and(finalPredicate, p1);
                } else {
                    finalPredicate = p1;
                }
            } catch (IllegalArgumentException e) {

            }
        }

        if (finalPredicate != null) {
            query.where(finalPredicate);
        }

        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public PhieuDangKy getPhieuDangKyById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PhieuDangKy> query = builder.createQuery(PhieuDangKy.class);
        Root<PhieuDangKy> root = query.from(PhieuDangKy.class);
        query.where(builder.equal(root.get("idPhieudk"), id));
        Query q = session.createQuery(query);
        List<PhieuDangKy> results = q.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public boolean themVaCapNhat(PhieuDangKy pdk) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            if (pdk.getIdPhieudk() != null) {
                //pdk ban sao
                PhieuDangKy existingPDK = session.get(PhieuDangKy.class, pdk.getIdPhieudk());

                if (existingPDK != null) {

                    existingPDK.setIdBs(pdk.getIdBs());
                    existingPDK.setIdYt(pdk.getIdYt());
                    existingPDK.setTrangThaidky(pdk.getTrangThaidky());
                    session.update(existingPDK);

                    if (existingPDK.getTrangThaidky() != null && existingPDK.getTrangThaidky() == 1) {
                        existingPDK.setTrangThaidky((short) 0);
                        existingPDK.setIdYt(null);
                        existingPDK.setIdBs(null);
                    } else {
                        existingPDK.setTrangThaidky((short) 1);
                    }

                    return true;
                }
            }
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public PhieuDangKy themPDK(PhieuDangKy pdk) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(pdk);

        return pdk;
    }

    @Override
    public List<PhieuDangKy> getPDKByIdTaiKhoan(int idBn) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PhieuDangKy> query = builder.createQuery(PhieuDangKy.class);
        Root<PhieuDangKy> root = query.from(PhieuDangKy.class);
        query.where(builder.equal(root.get("idBn"), idBn));
        Query q = session.createQuery(query);
        List<PhieuDangKy> results = q.getResultList();
        return results;
    }

    
    @Override
    public boolean xoaPDK(int id) {
       Session session = this.factory.getObject().getCurrentSession();
        PhieuDangKy tk = this.getPhieuDangKyById(id);
        try {
            session.delete(tk);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean xoaIdBn(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        PhieuDangKy tk = this.getPhieuDangKyById(id);
        try {
            session.delete(tk.getIdBn());
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean tuChoi(PhieuDangKy pdk) {
     Session session = this.factory.getObject().getCurrentSession();
        try {
            if(pdk.getIdPhieudk() != null)
            {
                pdk.setTuChoi((short) 1);
                session.update(pdk);
                return true;
            }
        } catch (Exception e) {
             e.printStackTrace();
            return false;
        }
        return false;
    }

}
