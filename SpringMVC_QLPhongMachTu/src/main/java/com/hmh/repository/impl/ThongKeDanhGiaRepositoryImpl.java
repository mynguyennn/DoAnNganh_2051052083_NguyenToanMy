/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.repository.impl;

import com.hmh.pojo.DanhGiaBs;
import com.hmh.pojo.TaiKhoan;
import com.hmh.repository.ThongKeDanhGiaRepository;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author LENOVO
 */
@Transactional
@Repository
public class ThongKeDanhGiaRepositoryImpl implements ThongKeDanhGiaRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Integer> getDgByIdBs(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<DanhGiaBs> query = builder.createQuery(DanhGiaBs.class);
        Root<DanhGiaBs> root = query.from(DanhGiaBs.class);
        query.select(root);
        query.where(builder.equal(root.get("idBs"), id));
        Query q = session.createQuery(query);
        List<DanhGiaBs> danhGiaList = q.getResultList();

        List<Integer> ratings = danhGiaList.stream()
                .map(DanhGiaBs::getDanhGia)
                .collect(Collectors.toList());
        return ratings;
    }

    @Override
    public List<Integer> getBacSi() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("Select idTk From TaiKhoan Where idRole=2");
        return q.getResultList();
    }
}
