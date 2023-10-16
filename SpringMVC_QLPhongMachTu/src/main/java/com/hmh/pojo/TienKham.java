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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Asus
 */
@Entity
@Table(name = "tien_kham")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TienKham.findAll", query = "SELECT t FROM TienKham t"),
    @NamedQuery(name = "TienKham.findByIdtienKham", query = "SELECT t FROM TienKham t WHERE t.idtienKham = :idtienKham"),
    @NamedQuery(name = "TienKham.findByTienKham", query = "SELECT t FROM TienKham t WHERE t.tienKham = :tienKham")})
public class TienKham implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tienKham")
    private Integer idtienKham;
    @Column(name = "tien_kham")
    private Long tienKham;
    @OneToMany(mappedBy = "tienKham")
    @JsonIgnore
    private Set<HoaDon> hoaDonSet;

    public TienKham() {
    }

    public TienKham(Integer idtienKham) {
        this.idtienKham = idtienKham;
    }

    public Integer getIdtienKham() {
        return idtienKham;
    }

    public void setIdtienKham(Integer idtienKham) {
        this.idtienKham = idtienKham;
    }

    public Long getTienKham() {
        return tienKham;
    }

    public void setTienKham(Long tienKham) {
        this.tienKham = tienKham;
    }

    @XmlTransient
    public Set<HoaDon> getHoaDonSet() {
        return hoaDonSet;
    }

    public void setHoaDonSet(Set<HoaDon> hoaDonSet) {
        this.hoaDonSet = hoaDonSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtienKham != null ? idtienKham.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TienKham)) {
            return false;
        }
        TienKham other = (TienKham) object;
        if ((this.idtienKham == null && other.idtienKham != null) || (this.idtienKham != null && !this.idtienKham.equals(other.idtienKham))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hmh.pojo.TienKham[ idtienKham=" + idtienKham + " ]";
    }
    
}
