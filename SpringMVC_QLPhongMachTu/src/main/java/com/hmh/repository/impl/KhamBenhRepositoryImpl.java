/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.repository.impl;

import com.hmh.pojo.ChiTietDv;
import com.hmh.pojo.DichVu;
import com.hmh.pojo.PhieuDangKy;
import com.hmh.pojo.PhieuKhamBenh;
import com.hmh.repository.KhamBenhRepository;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
public class KhamBenhRepositoryImpl implements KhamBenhRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public PhieuDangKy getPDK(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        return session.get(PhieuDangKy.class, id);
    }

    @Override
    public List<DichVu> getDichVu() {
        Session session = this.factory.getObject().getCurrentSession();
        Query query = session.createQuery("From DichVu");
        return query.getResultList();
    }

    @Override
    public List<PhieuDangKy> getLichSuKham(Map<String, String> params, int idBn) {
        Session session = this.factory.getObject().getCurrentSession();
        Query query = session.createQuery("FROM PhieuDangKy p WHERE p.idBn.idTk = :idBn");
        query.setParameter("idBn", idBn);
        return query.getResultList();
    }

    @Override
    public boolean themPhieuKhamBenh(PhieuKhamBenh pkb, int idPdk) {
        Session session = this.factory.getObject().getCurrentSession();
        PhieuDangKy pdk = session.get(PhieuDangKy.class, idPdk);

        try {
            if (pkb.getIdPhieukham() == null) {
                pdk.setIdPk(pkb);
                session.save(pkb);
            } else {
                session.update(pkb);
            }

            return true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        }

        return false;

    }

    @Override
    public List<ChiTietDv> getDvByIdPdk(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ChiTietDv> query = builder.createQuery(ChiTietDv.class);
        Root<ChiTietDv> root = query.from(ChiTietDv.class);
        query.select(root).where(builder.equal(root.get("idPdk"), id));
        return session.createQuery(query).getResultList();
    }

    @Override
    public List<PhieuDangKy> getPkByIdPdk(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PhieuDangKy> query = builder.createQuery(PhieuDangKy.class);
        Root<PhieuDangKy> root = query.from(PhieuDangKy.class);
        query.select(root).where(builder.equal(root.get("idPhieudk"), id));
        return session.createQuery(query).getResultList();
    }

}
