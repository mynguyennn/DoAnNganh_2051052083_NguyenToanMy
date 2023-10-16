/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.repository.impl;

import com.hmh.pojo.ChiTietDv;
import com.hmh.pojo.ChiTietThoiGianTruc;
import com.hmh.pojo.HoaDon;
import com.hmh.pojo.PhieuDangKy;
import com.hmh.pojo.TaiKhoan;
import com.hmh.repository.QuanLyTaiKhoanRepository;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Asus
 */
@Repository
@Transactional
public class QuanLyTaiKhoanRepositoryImpl implements QuanLyTaiKhoanRepository {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<TaiKhoan> getTaiKhoanAdmin(String username) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TaiKhoan> query = builder.createQuery(TaiKhoan.class);
        Root root = query.from(TaiKhoan.class);
        query = query.select(root);

//        String kw  = 
//        if (!username.isEmpty()) {
//            Predicate p = builder.equal(root.get("taiKhoan").as(String.class), username.trim());
//            query = query.where(p);
//        }
        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public boolean themTaiKhoan(TaiKhoan tk) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            if (tk.getIdTk() == null) {
                session.save(tk);
            } else {
                session.update(tk);
            }

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public TaiKhoan getTaiKhoanById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        return session.get(TaiKhoan.class, id);

    }

    @Override
    public boolean xoaTaiKhoan(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        TaiKhoan tk = this.getTaiKhoanById(id);
        try {
            session.delete(tk);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<TaiKhoan> timKiemTK(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TaiKhoan> query = builder.createQuery(TaiKhoan.class);
        Root root = query.from(TaiKhoan.class);
        query = query.select(root);

        if (params != null) {
            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                Predicate p1 = builder.like(root.get("hoTen"), String.format("%%%s%%", kw));
                query.where(p1);
            }
        }

//        query.orderBy(builder.desc(root.get("idTk")));
        Query q = session.createQuery(query);
        return q.getResultList();
    }

    //XOA ACCOUNT
    @Override
    public List<PhieuDangKy> getPhieuDangKyByTK(int idTaiKhoan) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PhieuDangKy> query = builder.createQuery(PhieuDangKy.class);
        Root root = query.from(PhieuDangKy.class);
        TaiKhoan tk = this.getTaiKhoanById(idTaiKhoan);
        if (tk != null) {
            Predicate condition = builder.or(
                    builder.equal(root.get("idBs"), tk.getIdTk()),
                    builder.equal(root.get("idBn"), tk.getIdTk()),
                    builder.equal(root.get("idYt"), tk.getIdTk())
            );
            query.select(root).where(condition);
            Query q = session.createQuery(query);
            return q.getResultList();
        }
        return null;

    }

    @Override
    public boolean xoaPhieuDangKyByTK(int idTaiKhoan) {
        Session session = this.factory.getObject().getCurrentSession();
        List<PhieuDangKy> phieuDangKys = this.getPhieuDangKyByTK(idTaiKhoan);
        try {
            for (PhieuDangKy phieuDangKy : phieuDangKys) {
                session.delete(phieuDangKy);
            }
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }

    }


    @Override
    public List<ChiTietThoiGianTruc> getCTThoiGianTrucByTK(int idTaiKhoan) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ChiTietThoiGianTruc> query = builder.createQuery(ChiTietThoiGianTruc.class);
        Root root = query.from(ChiTietThoiGianTruc.class);
        TaiKhoan tk = this.getTaiKhoanById(idTaiKhoan);
        if (tk != null) {
            query.select(root).where(builder.equal(root.get("idTk"), tk.getIdTk()));
            Query q = session.createQuery(query);
            return q.getResultList();
        }
        return null;
    }

    @Override
    public boolean xoaCTThoiGianTrucByTK(int idTaiKhoan) {
        Session session = this.factory.getObject().getCurrentSession();
        List<ChiTietThoiGianTruc> cttgtruc = this.getCTThoiGianTrucByTK(idTaiKhoan);
        try {
            for (ChiTietThoiGianTruc truc : cttgtruc) {
                session.delete(truc);
            }
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<ChiTietDv> getChiTietDichVuByPdk(int idTaiKhoan) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ChiTietDv> query = builder.createQuery(ChiTietDv.class);
        Root root = query.from(ChiTietDv.class);
        List<PhieuDangKy> pdk = this.getPhieuDangKyByTK(idTaiKhoan);
        if (pdk.size() > 0) {
            for (PhieuDangKy dk : pdk) {
                query.select(root).where(builder.equal(root.get("idPdk").get("idPhieudk"), dk.getIdPhieudk()));
                Query q = session.createQuery(query);
                return q.getResultList();
            }
        }
        return null;
    }

    @Override
    public boolean xoaCTDichVuBypdky(int idTaiKhoan) {
        Session session = this.factory.getObject().getCurrentSession();
        List<ChiTietDv> ctdvs = this.getChiTietDichVuByPdk(idTaiKhoan);
        try {
            for (ChiTietDv dv : ctdvs) {
                session.delete(dv);
            }
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<HoaDon> getHoaDonByPDK(int idTaiKhoan) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<HoaDon> query = builder.createQuery(HoaDon.class);
        Root root = query.from(HoaDon.class);
        List<PhieuDangKy> pdk = this.getPhieuDangKyByTK(idTaiKhoan);

        if (pdk.size() > 0) {
            for (PhieuDangKy dk : pdk) {
                query.select(root).where(builder.equal(root.get("idPhieudky").get("idPhieudk"), dk.getIdPhieudk()));
                Query q = session.createQuery(query);
                return q.getResultList();
            }
        }
        return null;
    }

    @Override
    public boolean xoaHoaDonByPDK(int idTaiKhoan) {
        Session session = this.factory.getObject().getCurrentSession();
        List<HoaDon> dhs = this.getHoaDonByPDK(idTaiKhoan);
        try {
            for (HoaDon dh : dhs) {
                session.delete(dh);
            }
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

}
