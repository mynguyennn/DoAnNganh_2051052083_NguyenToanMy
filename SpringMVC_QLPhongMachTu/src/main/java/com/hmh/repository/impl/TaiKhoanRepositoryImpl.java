/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.repository.impl;

import com.hmh.pojo.PhieuDangKy;
import com.hmh.pojo.TaiKhoan;
import com.hmh.pojo.UserRole;
import com.hmh.repository.TaiKhoanRepository;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Asus
 */
@Repository
@Transactional
public class TaiKhoanRepositoryImpl implements TaiKhoanRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactoryBean;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public boolean addTaiKhoan(TaiKhoan tk) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();

        try {
            if (tk.getIdTk() == null) {
                session.save(tk);
            } else {
                session.update(tk);
            }

            return true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        }

        return false;
    }

    @Override
    public List<TaiKhoan> getTaiKhoan(String username) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TaiKhoan> query = builder.createQuery(TaiKhoan.class);
        Root root = query.from(TaiKhoan.class);
        query = query.select(root);

        if (!username.isEmpty()) {
            Predicate p = builder.equal(root.get("taiKhoan").as(String.class), username.trim());
            query = query.where(p);
        }

        Query q = session.createQuery(query);
        return q.getResultList();
    }

    @Override
    public UserRole getRoleBenhNhan(String role) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        Query q = session.createQuery("FROM UserRole WHERE chucVu = :role");
        q.setParameter("role", role);
        return (UserRole) q.getSingleResult();
    }

    @Override
    public TaiKhoan getTaiKhoanById(int id) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        return session.get(TaiKhoan.class, id);
    }

    @Override
    public TaiKhoan getTaiKhoanByUsername(String username) {

        Session s = this.sessionFactoryBean.getObject().getCurrentSession();
        Query q = s.createQuery("FROM TaiKhoan WHERE taiKhoan=:un");
        q.setParameter("un", username);

        return (TaiKhoan) q.getSingleResult();
    }

    @Override
    public boolean doiMatKhau(TaiKhoan tk) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();

        try {
            session.update(tk);
            return true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean authUser(String username, String password) {
        TaiKhoan tk = this.getTaiKhoanByUsername(username);

        return this.passwordEncoder.matches(password, tk.getMatKhau());
    }

    @Override
    public TaiKhoan addUser(TaiKhoan u) {
        Session s = this.sessionFactoryBean.getObject().getCurrentSession();
        s.save(u);

        return u;
    }

    @Override
    public TaiKhoan thayDoiMatKhau(TaiKhoan a) {
        Session s = this.sessionFactoryBean.getObject().getCurrentSession();
        s.update(a);
        return a;
    }
    
    @Override
    public List<TaiKhoan> getListTaiKhoan() {
        Session s = this.sessionFactoryBean.getObject().getCurrentSession();
        Query q = s.createQuery("From TaiKhoan");

        return q.getResultList();
    }
}
