/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Asus
 */
@Entity
@Table(name = "phieu_kham_benh")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PhieuKhamBenh.findAll", query = "SELECT p FROM PhieuKhamBenh p"),
    @NamedQuery(name = "PhieuKhamBenh.findByIdPhieukham", query = "SELECT p FROM PhieuKhamBenh p WHERE p.idPhieukham = :idPhieukham"),
    @NamedQuery(name = "PhieuKhamBenh.findByTrieuChung", query = "SELECT p FROM PhieuKhamBenh p WHERE p.trieuChung = :trieuChung"),
    @NamedQuery(name = "PhieuKhamBenh.findByKetLuan", query = "SELECT p FROM PhieuKhamBenh p WHERE p.ketLuan = :ketLuan"),
    @NamedQuery(name = "PhieuKhamBenh.findByNgayKhamBenh", query = "SELECT p FROM PhieuKhamBenh p WHERE p.ngayKhamBenh = :ngayKhamBenh")})
public class PhieuKhamBenh implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_phieukham")
    private Integer idPhieukham;
    @Size(max = 50)
    @Column(name = "trieu_chung")
    private String trieuChung;
    @Size(max = 50)
    @Column(name = "ket_luan")
    private String ketLuan;
    @Column(name = "ngay_kham_benh")
    @Temporal(TemporalType.DATE)
    private Date ngayKhamBenh;
    @OneToMany(mappedBy = "idPk")
    @JsonIgnore
    private Set<PhieuDangKy> phieuDangKySet;
    @OneToMany(mappedBy = "idPhieukham")
    @JsonIgnore
    private Set<ChiTietThuoc> chiTietThuocSet;

    public PhieuKhamBenh() {
    }

    public PhieuKhamBenh(Integer idPhieukham) {
        this.idPhieukham = idPhieukham;
    }

    public Integer getIdPhieukham() {
        return idPhieukham;
    }

    public void setIdPhieukham(Integer idPhieukham) {
        this.idPhieukham = idPhieukham;
    }

    public String getTrieuChung() {
        return trieuChung;
    }

    public void setTrieuChung(String trieuChung) {
        this.trieuChung = trieuChung;
    }

    public String getKetLuan() {
        return ketLuan;
    }

    public void setKetLuan(String ketLuan) {
        this.ketLuan = ketLuan;
    }

    public Date getNgayKhamBenh() {
        return ngayKhamBenh;
    }

    public void setNgayKhamBenh(Date ngayKhamBenh) {
        this.ngayKhamBenh = ngayKhamBenh;
    }

    @XmlTransient
    public Set<PhieuDangKy> getPhieuDangKySet() {
        return phieuDangKySet;
    }

    public void setPhieuDangKySet(Set<PhieuDangKy> phieuDangKySet) {
        this.phieuDangKySet = phieuDangKySet;
    }

    @XmlTransient
    public Set<ChiTietThuoc> getChiTietThuocSet() {
        return chiTietThuocSet;
    }

    public void setChiTietThuocSet(Set<ChiTietThuoc> chiTietThuocSet) {
        this.chiTietThuocSet = chiTietThuocSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPhieukham != null ? idPhieukham.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PhieuKhamBenh)) {
            return false;
        }
        PhieuKhamBenh other = (PhieuKhamBenh) object;
        if ((this.idPhieukham == null && other.idPhieukham != null) || (this.idPhieukham != null && !this.idPhieukham.equals(other.idPhieukham))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hmh.pojo.PhieuKhamBenh[ idPhieukham=" + idPhieukham + " ]";
    }
    
}
