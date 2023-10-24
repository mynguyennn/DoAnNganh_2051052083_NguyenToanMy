/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.pojo;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author LENOVO
 */
@Entity
@Table(name = "danh_gia_bs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DanhGiaBs.findAll", query = "SELECT d FROM DanhGiaBs d"),
    @NamedQuery(name = "DanhGiaBs.findById", query = "SELECT d FROM DanhGiaBs d WHERE d.id = :id"),
    @NamedQuery(name = "DanhGiaBs.findByDanhGia", query = "SELECT d FROM DanhGiaBs d WHERE d.danhGia = :danhGia"),
    @NamedQuery(name = "DanhGiaBs.findByBinhLuan", query = "SELECT d FROM DanhGiaBs d WHERE d.binhLuan = :binhLuan")})
public class DanhGiaBs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "danh_gia")
    private Integer danhGia;
    @Size(max = 200)
    @Column(name = "binh_luan")
    private String binhLuan;
    @JoinColumn(name = "id_pdk", referencedColumnName = "id_phieudk")
    @ManyToOne
    private PhieuDangKy idPdk;
    @JoinColumn(name = "id_bs", referencedColumnName = "id_tk")
    @ManyToOne
    private TaiKhoan idBs;
    @JoinColumn(name = "id_bn", referencedColumnName = "id_tk")
    @ManyToOne
    private TaiKhoan idBn;

    public DanhGiaBs() {
    }

    public DanhGiaBs(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(Integer danhGia) {
        this.danhGia = danhGia;
    }

    public String getBinhLuan() {
        return binhLuan;
    }

    public void setBinhLuan(String binhLuan) {
        this.binhLuan = binhLuan;
    }

    public PhieuDangKy getIdPdk() {
        return idPdk;
    }

    public void setIdPdk(PhieuDangKy idPdk) {
        this.idPdk = idPdk;
    }

    public TaiKhoan getIdBs() {
        return idBs;
    }

    public void setIdBs(TaiKhoan idBs) {
        this.idBs = idBs;
    }

    public TaiKhoan getIdBn() {
        return idBn;
    }

    public void setIdBn(TaiKhoan idBn) {
        this.idBn = idBn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DanhGiaBs)) {
            return false;
        }
        DanhGiaBs other = (DanhGiaBs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hmh.pojo.DanhGiaBs[ id=" + id + " ]";
    }
    
}
