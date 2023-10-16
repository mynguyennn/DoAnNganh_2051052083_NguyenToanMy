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
@Table(name = "loai_thuoc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LoaiThuoc.findAll", query = "SELECT l FROM LoaiThuoc l"),
    @NamedQuery(name = "LoaiThuoc.findByIdloaiThuoc", query = "SELECT l FROM LoaiThuoc l WHERE l.idloaiThuoc = :idloaiThuoc"),
    @NamedQuery(name = "LoaiThuoc.findByTenLoaiThuoc", query = "SELECT l FROM LoaiThuoc l WHERE l.tenLoaiThuoc = :tenLoaiThuoc")})
public class LoaiThuoc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idloai_thuoc")
    private Integer idloaiThuoc;
    @Size(max = 45)
    @Column(name = "ten_loai_thuoc")
    private String tenLoaiThuoc;
    @OneToMany(mappedBy = "loaiThuoc")
    @JsonIgnore
    private Set<Thuoc> thuocSet;

    public LoaiThuoc() {
    }

    public LoaiThuoc(Integer idloaiThuoc) {
        this.idloaiThuoc = idloaiThuoc;
    }

    public Integer getIdloaiThuoc() {
        return idloaiThuoc;
    }

    public void setIdloaiThuoc(Integer idloaiThuoc) {
        this.idloaiThuoc = idloaiThuoc;
    }

    public String getTenLoaiThuoc() {
        return tenLoaiThuoc;
    }

    public void setTenLoaiThuoc(String tenLoaiThuoc) {
        this.tenLoaiThuoc = tenLoaiThuoc;
    }

    @XmlTransient
    public Set<Thuoc> getThuocSet() {
        return thuocSet;
    }

    public void setThuocSet(Set<Thuoc> thuocSet) {
        this.thuocSet = thuocSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idloaiThuoc != null ? idloaiThuoc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoaiThuoc)) {
            return false;
        }
        LoaiThuoc other = (LoaiThuoc) object;
        if ((this.idloaiThuoc == null && other.idloaiThuoc != null) || (this.idloaiThuoc != null && !this.idloaiThuoc.equals(other.idloaiThuoc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hmh.pojo.LoaiThuoc[ idloaiThuoc=" + idloaiThuoc + " ]";
    }
    
}
