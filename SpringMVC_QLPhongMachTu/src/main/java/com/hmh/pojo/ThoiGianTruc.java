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
@Table(name = "thoi_gian_truc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ThoiGianTruc.findAll", query = "SELECT t FROM ThoiGianTruc t"),
    @NamedQuery(name = "ThoiGianTruc.findByIdtgTruc", query = "SELECT t FROM ThoiGianTruc t WHERE t.idtgTruc = :idtgTruc"),
    @NamedQuery(name = "ThoiGianTruc.findByBuoiTruc", query = "SELECT t FROM ThoiGianTruc t WHERE t.buoiTruc = :buoiTruc"),
    @NamedQuery(name = "ThoiGianTruc.findByBatDau", query = "SELECT t FROM ThoiGianTruc t WHERE t.batDau = :batDau"),
    @NamedQuery(name = "ThoiGianTruc.findByKetThuc", query = "SELECT t FROM ThoiGianTruc t WHERE t.ketThuc = :ketThuc")})
public class ThoiGianTruc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tgTruc")
    private Integer idtgTruc;
    @Size(max = 45)
    @Column(name = "buoi_truc")
    private String buoiTruc;
    @Column(name = "bat_dau")
    @Temporal(TemporalType.TIME)
    private Date batDau;
    @Column(name = "ket_thuc")
    @Temporal(TemporalType.TIME)
    private Date ketThuc;
    @OneToMany(mappedBy = "idTgTruc")
    @JsonIgnore
    private Set<ChiTietThoiGianTruc> chiTietThoiGianTrucSet;

    public ThoiGianTruc() {
    }

    public ThoiGianTruc(Integer idtgTruc) {
        this.idtgTruc = idtgTruc;
    }

    public Integer getIdtgTruc() {
        return idtgTruc;
    }

    public void setIdtgTruc(Integer idtgTruc) {
        this.idtgTruc = idtgTruc;
    }

    public String getBuoiTruc() {
        return buoiTruc;
    }

    public void setBuoiTruc(String buoiTruc) {
        this.buoiTruc = buoiTruc;
    }

    public Date getBatDau() {
        return batDau;
    }

    public void setBatDau(Date batDau) {
        this.batDau = batDau;
    }

    public Date getKetThuc() {
        return ketThuc;
    }

    public void setKetThuc(Date ketThuc) {
        this.ketThuc = ketThuc;
    }

    @XmlTransient
    public Set<ChiTietThoiGianTruc> getChiTietThoiGianTrucSet() {
        return chiTietThoiGianTrucSet;
    }

    public void setChiTietThoiGianTrucSet(Set<ChiTietThoiGianTruc> chiTietThoiGianTrucSet) {
        this.chiTietThoiGianTrucSet = chiTietThoiGianTrucSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtgTruc != null ? idtgTruc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ThoiGianTruc)) {
            return false;
        }
        ThoiGianTruc other = (ThoiGianTruc) object;
        if ((this.idtgTruc == null && other.idtgTruc != null) || (this.idtgTruc != null && !this.idtgTruc.equals(other.idtgTruc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hmh.pojo.ThoiGianTruc[ idtgTruc=" + idtgTruc + " ]";
    }
    
}
