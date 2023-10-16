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
 * @author Asus
 */
@Entity
@Table(name = "chi_tiet_thuoc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ChiTietThuoc.findAll", query = "SELECT c FROM ChiTietThuoc c"),
    @NamedQuery(name = "ChiTietThuoc.findByIdChitietThuoc", query = "SELECT c FROM ChiTietThuoc c WHERE c.idChitietThuoc = :idChitietThuoc"),
    @NamedQuery(name = "ChiTietThuoc.findBySoLuongSd", query = "SELECT c FROM ChiTietThuoc c WHERE c.soLuongSd = :soLuongSd"),
    @NamedQuery(name = "ChiTietThuoc.findByHdsd", query = "SELECT c FROM ChiTietThuoc c WHERE c.hdsd = :hdsd")})
public class ChiTietThuoc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_chitiet_thuoc")
    private Integer idChitietThuoc;
    @Column(name = "so_luong_sd")
    private Integer soLuongSd;
    @Size(max = 500)
    @Column(name = "hdsd")
    private String hdsd;
    @JoinColumn(name = "id_phieukham", referencedColumnName = "id_phieukham")
    @ManyToOne
    private PhieuKhamBenh idPhieukham;
    @JoinColumn(name = "id_thuoc", referencedColumnName = "id_thuoc")
    @ManyToOne
    private Thuoc idThuoc;

    public ChiTietThuoc() {
    }

    public ChiTietThuoc(Integer idChitietThuoc) {
        this.idChitietThuoc = idChitietThuoc;
    }

    public Integer getIdChitietThuoc() {
        return idChitietThuoc;
    }

    public void setIdChitietThuoc(Integer idChitietThuoc) {
        this.idChitietThuoc = idChitietThuoc;
    }

    public Integer getSoLuongSd() {
        return soLuongSd;
    }

    public void setSoLuongSd(Integer soLuongSd) {
        this.soLuongSd = soLuongSd;
    }

    public String getHdsd() {
        return hdsd;
    }

    public void setHdsd(String hdsd) {
        this.hdsd = hdsd;
    }

    public PhieuKhamBenh getIdPhieukham() {
        return idPhieukham;
    }

    public void setIdPhieukham(PhieuKhamBenh idPhieukham) {
        this.idPhieukham = idPhieukham;
    }

    public Thuoc getIdThuoc() {
        return idThuoc;
    }

    public void setIdThuoc(Thuoc idThuoc) {
        this.idThuoc = idThuoc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idChitietThuoc != null ? idChitietThuoc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChiTietThuoc)) {
            return false;
        }
        ChiTietThuoc other = (ChiTietThuoc) object;
        if ((this.idChitietThuoc == null && other.idChitietThuoc != null) || (this.idChitietThuoc != null && !this.idChitietThuoc.equals(other.idChitietThuoc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hmh.pojo.ChiTietThuoc[ idChitietThuoc=" + idChitietThuoc + " ]";
    }
    
}
