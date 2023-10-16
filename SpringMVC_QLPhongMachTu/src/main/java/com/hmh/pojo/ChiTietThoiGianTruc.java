/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Asus
 */
@Entity
@Table(name = "chi_tiet_thoi_gian_truc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ChiTietThoiGianTruc.findAll", query = "SELECT c FROM ChiTietThoiGianTruc c"),
    @NamedQuery(name = "ChiTietThoiGianTruc.findByIdChiTietTgTruc", query = "SELECT c FROM ChiTietThoiGianTruc c WHERE c.idChiTietTgTruc = :idChiTietTgTruc"),
    @NamedQuery(name = "ChiTietThoiGianTruc.findByNgayDkyTruc", query = "SELECT c FROM ChiTietThoiGianTruc c WHERE c.ngayDkyTruc = :ngayDkyTruc"),
    @NamedQuery(name = "ChiTietThoiGianTruc.findByTrangThaiTruc", query = "SELECT c FROM ChiTietThoiGianTruc c WHERE c.trangThaiTruc = :trangThaiTruc")})
public class ChiTietThoiGianTruc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_chi_tiet_tg_truc")
    private Integer idChiTietTgTruc;
    @Column(name = "ngay_dky_truc")
    @Temporal(TemporalType.DATE)
    private Date ngayDkyTruc;
    @Column(name = "trang_thai_truc")
    private Short trangThaiTruc;
    @JoinColumn(name = "id_tk", referencedColumnName = "id_tk")
    @ManyToOne
    private TaiKhoan idTk;
    @JoinColumn(name = "id_tg_truc", referencedColumnName = "id_tgTruc")
    @ManyToOne
    private ThoiGianTruc idTgTruc;

    public ChiTietThoiGianTruc() {
    }

    public ChiTietThoiGianTruc(Integer idChiTietTgTruc) {
        this.idChiTietTgTruc = idChiTietTgTruc;
    }

    public Integer getIdChiTietTgTruc() {
        return idChiTietTgTruc;
    }

    public void setIdChiTietTgTruc(Integer idChiTietTgTruc) {
        this.idChiTietTgTruc = idChiTietTgTruc;
    }

    public Date getNgayDkyTruc() {
        return ngayDkyTruc;
    }

    public void setNgayDkyTruc(Date ngayDkyTruc) {
        this.ngayDkyTruc = ngayDkyTruc;
    }

    public Short getTrangThaiTruc() {
        return trangThaiTruc;
    }

    public void setTrangThaiTruc(Short trangThaiTruc) {
        this.trangThaiTruc = trangThaiTruc;
    }

    public TaiKhoan getIdTk() {
        return idTk;
    }

    public void setIdTk(TaiKhoan idTk) {
        this.idTk = idTk;
    }

    public ThoiGianTruc getIdTgTruc() {
        return idTgTruc;
    }

    public void setIdTgTruc(ThoiGianTruc idTgTruc) {
        this.idTgTruc = idTgTruc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idChiTietTgTruc != null ? idChiTietTgTruc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChiTietThoiGianTruc)) {
            return false;
        }
        ChiTietThoiGianTruc other = (ChiTietThoiGianTruc) object;
        if ((this.idChiTietTgTruc == null && other.idChiTietTgTruc != null) || (this.idChiTietTgTruc != null && !this.idChiTietTgTruc.equals(other.idChiTietTgTruc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hmh.pojo.ChiTietThoiGianTruc[ idChiTietTgTruc=" + idChiTietTgTruc + " ]";
    }
    
}
