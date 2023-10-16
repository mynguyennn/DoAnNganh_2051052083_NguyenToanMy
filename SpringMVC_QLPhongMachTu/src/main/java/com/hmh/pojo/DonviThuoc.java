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
@Table(name = "donvi_thuoc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DonviThuoc.findAll", query = "SELECT d FROM DonviThuoc d"),
    @NamedQuery(name = "DonviThuoc.findByIddonVi", query = "SELECT d FROM DonviThuoc d WHERE d.iddonVi = :iddonVi"),
    @NamedQuery(name = "DonviThuoc.findByTenDonVi", query = "SELECT d FROM DonviThuoc d WHERE d.tenDonVi = :tenDonVi")})
public class DonviThuoc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_donVi")
    private Integer iddonVi;
    @Size(max = 45)
    @Column(name = "ten_don_vi")
    private String tenDonVi;
    @OneToMany(mappedBy = "donVi")
    @JsonIgnore
    private Set<Thuoc> thuocSet;

    public DonviThuoc() {
    }

    public DonviThuoc(Integer iddonVi) {
        this.iddonVi = iddonVi;
    }

    public Integer getIddonVi() {
        return iddonVi;
    }

    public void setIddonVi(Integer iddonVi) {
        this.iddonVi = iddonVi;
    }

    public String getTenDonVi() {
        return tenDonVi;
    }

    public void setTenDonVi(String tenDonVi) {
        this.tenDonVi = tenDonVi;
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
        hash += (iddonVi != null ? iddonVi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DonviThuoc)) {
            return false;
        }
        DonviThuoc other = (DonviThuoc) object;
        if ((this.iddonVi == null && other.iddonVi != null) || (this.iddonVi != null && !this.iddonVi.equals(other.iddonVi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hmh.pojo.DonviThuoc[ iddonVi=" + iddonVi + " ]";
    }
    
}
