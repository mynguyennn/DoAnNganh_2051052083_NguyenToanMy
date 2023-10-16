/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Asus
 */
@Entity
@Table(name = "thuoc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Thuoc.findAll", query = "SELECT t FROM Thuoc t"),
    @NamedQuery(name = "Thuoc.findByIdThuoc", query = "SELECT t FROM Thuoc t WHERE t.idThuoc = :idThuoc"),
    @NamedQuery(name = "Thuoc.findByTenThuoc", query = "SELECT t FROM Thuoc t WHERE t.tenThuoc = :tenThuoc"),
    @NamedQuery(name = "Thuoc.findByXuatXu", query = "SELECT t FROM Thuoc t WHERE t.xuatXu = :xuatXu"),
    @NamedQuery(name = "Thuoc.findByGiaThuoc", query = "SELECT t FROM Thuoc t WHERE t.giaThuoc = :giaThuoc"),
    @NamedQuery(name = "Thuoc.findBySoLuong", query = "SELECT t FROM Thuoc t WHERE t.soLuong = :soLuong")})
public class Thuoc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_thuoc")
    private Integer idThuoc;
    @Size(max = 50)
    @Column(name = "ten_thuoc")
    private String tenThuoc;
    @Size(max = 50)
    @Column(name = "xuat_xu")
    private String xuatXu;
    @Column(name = "gia_thuoc")
    private Long giaThuoc;
    @Column(name = "so_luong")
    private Integer soLuong;
    @JoinColumn(name = "don_vi", referencedColumnName = "id_donVi")
    @ManyToOne
    private DonviThuoc donVi;
    @JoinColumn(name = "loai_thuoc", referencedColumnName = "idloai_thuoc")
    @ManyToOne
    private LoaiThuoc loaiThuoc;
    @OneToMany(mappedBy = "idThuoc")
    @JsonIgnore
    private Set<ChiTietThuoc> chiTietThuocSet;

    public Thuoc() {
    }

    public Thuoc(Integer idThuoc) {
        this.idThuoc = idThuoc;
    }

    public Integer getIdThuoc() {
        return idThuoc;
    }

    public void setIdThuoc(Integer idThuoc) {
        this.idThuoc = idThuoc;
    }

    public String getTenThuoc() {
        return tenThuoc;
    }

    public void setTenThuoc(String tenThuoc) {
        this.tenThuoc = tenThuoc;
    }

    public String getXuatXu() {
        return xuatXu;
    }

    public void setXuatXu(String xuatXu) {
        this.xuatXu = xuatXu;
    }

    public Long getGiaThuoc() {
        return giaThuoc;
    }

    public void setGiaThuoc(Long giaThuoc) {
        this.giaThuoc = giaThuoc;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public DonviThuoc getDonVi() {
        return donVi;
    }

    public void setDonVi(DonviThuoc donVi) {
        this.donVi = donVi;
    }

    public LoaiThuoc getLoaiThuoc() {
        return loaiThuoc;
    }

    public void setLoaiThuoc(LoaiThuoc loaiThuoc) {
        this.loaiThuoc = loaiThuoc;
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
        hash += (idThuoc != null ? idThuoc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Thuoc)) {
            return false;
        }
        Thuoc other = (Thuoc) object;
        if ((this.idThuoc == null && other.idThuoc != null) || (this.idThuoc != null && !this.idThuoc.equals(other.idThuoc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hmh.pojo.Thuoc[ idThuoc=" + idThuoc + " ]";
    }

}
