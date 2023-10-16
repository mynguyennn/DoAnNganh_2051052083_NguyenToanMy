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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Asus
 */
@Entity
@Table(name = "chi_tiet_dv")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ChiTietDv.findAll", query = "SELECT c FROM ChiTietDv c"),
    @NamedQuery(name = "ChiTietDv.findByIdchitietDV", query = "SELECT c FROM ChiTietDv c WHERE c.idchitietDV = :idchitietDV")})
public class ChiTietDv implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_chitietDV")
    private Integer idchitietDV;
    @JoinColumn(name = "id_dv", referencedColumnName = "id_dv")
    @ManyToOne
    private DichVu idDv;
    @JoinColumn(name = "id_pdk", referencedColumnName = "id_phieudk")
    @ManyToOne
    private PhieuDangKy idPdk;

    public ChiTietDv() {
    }

    public ChiTietDv(Integer idchitietDV) {
        this.idchitietDV = idchitietDV;
    }

    public Integer getIdchitietDV() {
        return idchitietDV;
    }

    public void setIdchitietDV(Integer idchitietDV) {
        this.idchitietDV = idchitietDV;
    }

    public DichVu getIdDv() {
        return idDv;
    }

    public void setIdDv(DichVu idDv) {
        this.idDv = idDv;
    }

    public PhieuDangKy getIdPdk() {
        return idPdk;
    }

    public void setIdPdk(PhieuDangKy idPdk) {
        this.idPdk = idPdk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idchitietDV != null ? idchitietDV.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChiTietDv)) {
            return false;
        }
        ChiTietDv other = (ChiTietDv) object;
        if ((this.idchitietDV == null && other.idchitietDV != null) || (this.idchitietDV != null && !this.idchitietDV.equals(other.idchitietDV))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hmh.pojo.ChiTietDv[ idchitietDV=" + idchitietDV + " ]";
    }
    
}
