/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.repository.impl;

import com.hmh.pojo.DanhGiaBs;
import com.hmh.pojo.PhieuDangKy;
import com.hmh.pojo.TaiKhoan;
import com.hmh.repository.DanhGiaRepository;
import com.hmh.service.TaiKhoanService;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author LENOVO
 */
@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class DanhGiaRepositoryImpl implements DanhGiaRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private TaiKhoanService taiKhoanService;
    @Autowired
    private Environment env;

    @Override
    public PhieuDangKy getPdkById(int id) {
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
    public List<DanhGiaBs> getDgByIdBs(int id, Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<DanhGiaBs> query = builder.createQuery(DanhGiaBs.class);
        Root<DanhGiaBs> root = query.from(DanhGiaBs.class);
        query.where(builder.equal(root.get("idBs"), id));
        Query q = session.createQuery(query);
         String p = params.get("page");
        if (p != null) {
            int page = Integer.parseInt(p);
           
                int pagesize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
                q.setFirstResult((page-1)*pagesize);
                q.setMaxResults(pagesize);
            
        }

        return q.getResultList();

    }

    @Override
    public boolean luuDanhGia(int id, DanhGiaBs dg, int tk) {
        Session session = this.factory.getObject().getCurrentSession();
        TaiKhoan bacsi = this.taiKhoanService.getTaiKhoanById(id);
        TaiKhoan bn = this.taiKhoanService.getTaiKhoanById(tk);
        try {
            if (dg.getId() == null) {
                dg.setIdBn(bn);
                dg.setIdBs(bacsi);
                session.save(dg);
                return true;
            } else {
                session.update(dg);
            }
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public int demComment() {
        Session session = this.factory.getObject().getCurrentSession();
        Query q = session.createQuery("Select Count(*) From DanhGiaBs");
       

        return Integer.parseInt(q.getSingleResult().toString());
    }

}
