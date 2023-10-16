/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.repository.impl;

import com.hmh.pojo.ChiTietThoiGianTruc;
import com.hmh.pojo.TaiKhoan;
import com.hmh.pojo.ThoiGianTruc;
import com.hmh.repository.LichTrucRepository;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * @author LENOVO
 */
@Repository
@Transactional
public class LichTrucRepositoryImpl implements LichTrucRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<TaiKhoan> getTkYtaBs() {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TaiKhoan> query = builder.createQuery(TaiKhoan.class);
        Root<TaiKhoan> root = query.from(TaiKhoan.class);
        query.select(root);

        Predicate role = builder.or(builder.equal((root.get("idRole")), 2),
                builder.equal(root.get("idRole"), 3));

        query = query.where(role);
        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public List<ChiTietThoiGianTruc> getChiTietTgTruc() {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<ChiTietThoiGianTruc> q = b.createQuery(ChiTietThoiGianTruc.class);
        Root<ChiTietThoiGianTruc> root = q.from(ChiTietThoiGianTruc.class);
        q.select(root);

        Query query = session.createQuery(q);

        return query.getResultList();
    }

    @Override
    public List<ChiTietThoiGianTruc> getLich(Date fromDate) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ChiTietThoiGianTruc> query = builder.createQuery(ChiTietThoiGianTruc.class);
        Root<ChiTietThoiGianTruc> root = query.from(ChiTietThoiGianTruc.class);
        query.select(root);

        if (fromDate != null) {
            // Tạo điều kiện lọc lịch từ ngày truyền vào trở đi
            Predicate fromDatePredicate = builder.greaterThanOrEqualTo(root.get("ngayDkyTruc"), fromDate);
            query.where(fromDatePredicate);
        }

        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public List<ThoiGianTruc> getTg() {
        Session session = this.factory.getObject().getCurrentSession();
        Query query = session.createQuery("From ThoiGianTruc");
        return query.getResultList();
    }

    @Override
    public boolean addAndUpdate(ChiTietThoiGianTruc tg, TaiKhoan idTk, List<Date> date, List<Integer> idtgTruc) {
        Session session = this.factory.getObject().getCurrentSession();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();

        try {
            for (Date dates : date) {
                tg.setNgayDkyTruc(dates);
                tg.setIdTk(idTk);
                tg.setTrangThaiTruc((short) 0);
                session.save(tg);
                for (Integer tg1 : idtgTruc) {
                    ThoiGianTruc idTGT = session.get(ThoiGianTruc.class, tg1);
                    tg.setIdTgTruc(idTGT);
                }
            }

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return false;

    }

    @Override
    public boolean xoaLichTruc(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        ChiTietThoiGianTruc ct = this.getChiTietThoiGianTrucById(id);
        try {
            session.delete(ct);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public ChiTietThoiGianTruc getChiTietThoiGianTrucById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        return session.get(ChiTietThoiGianTruc.class, id);
    }

    @Override
    public List<Object> getChiTietThoiGianTrucByIDTK(TaiKhoan idTk) {
        Session s = this.factory.getObject().getCurrentSession();
        javax.persistence.Query q = s.createQuery("From ChiTietThoiGianTruc Where idTk =: idTk");

        q.setParameter("idTk", idTk);
        return q.getResultList();
    }

    @Override
    public List<ChiTietThoiGianTruc> getChiTietTgtByidTk(TaiKhoan idTk) {
        Session session = this.factory.getObject().getCurrentSession();
        Query query = session.createQuery("From ChiTietThoiGianTruc Where idTk =: idTk");
        query.setParameter("idTk", idTk);
        return query.getResultList();
    }

    @Override
    public boolean update(ChiTietThoiGianTruc tg) {
        Session session = this.factory.getObject().getCurrentSession();
        if (tg.getIdChiTietTgTruc() != null) {
            tg.setTrangThaiTruc((short) 1);
            session.update(tg);
            session.flush();
            return true;
        }
        return false;
    }

}
