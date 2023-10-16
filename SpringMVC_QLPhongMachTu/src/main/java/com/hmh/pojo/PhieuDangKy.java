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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Asus
 */
@Entity
@Table(name = "phieu_dang_ky")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PhieuDangKy.findAll", query = "SELECT p FROM PhieuDangKy p"),
    @NamedQuery(name = "PhieuDangKy.findByIdPhieudk", query = "SELECT p FROM PhieuDangKy p WHERE p.idPhieudk = :idPhieudk"),
    @NamedQuery(name = "PhieuDangKy.findByTrangThaidky", query = "SELECT p FROM PhieuDangKy p WHERE p.trangThaidky = :trangThaidky"),
    @NamedQuery(name = "PhieuDangKy.findByChonNgaykham", query = "SELECT p FROM PhieuDangKy p WHERE p.chonNgaykham = :chonNgaykham"),
    @NamedQuery(name = "PhieuDangKy.findByThoiGianTaophieu", query = "SELECT p FROM PhieuDangKy p WHERE p.thoiGianTaophieu = :thoiGianTaophieu"),
    @NamedQuery(name = "PhieuDangKy.findByThoiGianKham", query = "SELECT p FROM PhieuDangKy p WHERE p.thoiGianKham = :thoiGianKham")})
public class PhieuDangKy implements Serializable {

    @Transient
    private String tenBenhNhanDky;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_phieudk")
    private Integer idPhieudk;
    @Column(name = "trangThai_dky")
    private Short trangThaidky;
    @Column(name = "tu_choi")
    private Short tuChoi;
    @Column(name = "chon_ngaykham")
    @Temporal(TemporalType.DATE)
    private Date chonNgaykham;
    @Column(name = "thoi_gian_taophieu")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thoiGianTaophieu;
    @Size(max = 45)
    @Column(name = "thoi_gian_kham")
    private String thoiGianKham;
    @OneToMany(mappedBy = "idPhieudky")
    @JsonIgnore
    private Set<HoaDon> hoaDonSet;
    @JoinColumn(name = "id_pk", referencedColumnName = "id_phieukham")
    @ManyToOne
    private PhieuKhamBenh idPk;
    @JoinColumn(name = "id_bn", referencedColumnName = "id_tk")
    @ManyToOne
    private TaiKhoan idBn;
    @JoinColumn(name = "id_bs", referencedColumnName = "id_tk")
    @ManyToOne
    private TaiKhoan idBs;
    @JoinColumn(name = "id_yt", referencedColumnName = "id_tk")
    @ManyToOne
    private TaiKhoan idYt;
    @OneToMany(mappedBy = "idPdk")
    @JsonIgnore
    private Set<ChiTietDv> chiTietDvSet;

    public PhieuDangKy() {
    }

    public PhieuDangKy(Integer idPhieudk) {
        this.idPhieudk = idPhieudk;
    }

    public Integer getIdPhieudk() {
        return idPhieudk;
    }

    public void setIdPhieudk(Integer idPhieudk) {
        this.idPhieudk = idPhieudk;
    }

    public Short getTrangThaidky() {
        return trangThaidky;
    }

    public void setTrangThaidky(Short trangThaidky) {
        this.trangThaidky = trangThaidky;
    }

    public Date getChonNgaykham() {
        return chonNgaykham;
    }

    public void setChonNgaykham(Date chonNgaykham) {
        this.chonNgaykham = chonNgaykham;
    }

    public Date getThoiGianTaophieu() {
        return thoiGianTaophieu;
    }

    public void setThoiGianTaophieu(Date thoiGianTaophieu) {
        this.thoiGianTaophieu = thoiGianTaophieu;
    }

    public String getThoiGianKham() {
        return thoiGianKham;
    }

    public void setThoiGianKham(String thoiGianKham) {
        this.thoiGianKham = thoiGianKham;
    }

    @XmlTransient
    public Set<HoaDon> getHoaDonSet() {
        return hoaDonSet;
    }

    public void setHoaDonSet(Set<HoaDon> hoaDonSet) {
        this.hoaDonSet = hoaDonSet;
    }

    public PhieuKhamBenh getIdPk() {
        return idPk;
    }

    public void setIdPk(PhieuKhamBenh idPk) {
        this.idPk = idPk;
    }

    public TaiKhoan getIdBn() {
        return idBn;
    }

    public void setIdBn(TaiKhoan idBn) {
        this.idBn = idBn;
    }

    public TaiKhoan getIdBs() {
        return idBs;
    }

    public void setIdBs(TaiKhoan idBs) {
        this.idBs = idBs;
    }

    public TaiKhoan getIdYt() {
        return idYt;
    }

    public void setIdYt(TaiKhoan idYt) {
        this.idYt = idYt;
    }

    @XmlTransient
    public Set<ChiTietDv> getChiTietDvSet() {
        return chiTietDvSet;
    }

    public void setChiTietDvSet(Set<ChiTietDv> chiTietDvSet) {
        this.chiTietDvSet = chiTietDvSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPhieudk != null ? idPhieudk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PhieuDangKy)) {
            return false;
        }
        PhieuDangKy other = (PhieuDangKy) object;
        if ((this.idPhieudk == null && other.idPhieudk != null) || (this.idPhieudk != null && !this.idPhieudk.equals(other.idPhieudk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hmh.pojo.PhieuDangKy[ idPhieudk=" + idPhieudk + " ]";
    }

    /**
     * @return the tenBenhNhanDky
     */
    public String getTenBenhNhanDky() {
        return tenBenhNhanDky;
    }

    /**
     * @param tenBenhNhanDky the tenBenhNhanDky to set
     */
    public void setTenBenhNhanDky(String tenBenhNhanDky) {
        this.tenBenhNhanDky = tenBenhNhanDky;
    }

    /**
     * @return the tuChoi
     */
    public Short getTuChoi() {
        return tuChoi;
    }

    /**
     * @param tuChoi the tuChoi to set
     */
    public void setTuChoi(Short tuChoi) {
        this.tuChoi = tuChoi;
    }

}
