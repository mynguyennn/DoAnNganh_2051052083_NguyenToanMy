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
@Table(name = "dich_vu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DichVu.findAll", query = "SELECT d FROM DichVu d"),
    @NamedQuery(name = "DichVu.findByIdDv", query = "SELECT d FROM DichVu d WHERE d.idDv = :idDv"),
    @NamedQuery(name = "DichVu.findByTenDv", query = "SELECT d FROM DichVu d WHERE d.tenDv = :tenDv"),
    @NamedQuery(name = "DichVu.findByGiaDv", query = "SELECT d FROM DichVu d WHERE d.giaDv = :giaDv")})
public class DichVu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_dv")
    private Integer idDv;
    @Size(max = 45)
    @Column(name = "ten_dv")
    private String tenDv;
    @Column(name = "gia_dv")
    private Long giaDv;
    @OneToMany(mappedBy = "idDv")
    @JsonIgnore
    private Set<ChiTietDv> chiTietDvSet;

    public DichVu() {
    }

    public DichVu(Integer idDv) {
        this.idDv = idDv;
    }

    public Integer getIdDv() {
        return idDv;
    }

    public void setIdDv(Integer idDv) {
        this.idDv = idDv;
    }

    public String getTenDv() {
        return tenDv;
    }

    public void setTenDv(String tenDv) {
        this.tenDv = tenDv;
    }

    public Long getGiaDv() {
        return giaDv;
    }

    public void setGiaDv(Long giaDv) {
        this.giaDv = giaDv;
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
        hash += (idDv != null ? idDv.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DichVu)) {
            return false;
        }
        DichVu other = (DichVu) object;
        if ((this.idDv == null && other.idDv != null) || (this.idDv != null && !this.idDv.equals(other.idDv))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hmh.pojo.DichVu[ idDv=" + idDv + " ]";
    }
    
}
