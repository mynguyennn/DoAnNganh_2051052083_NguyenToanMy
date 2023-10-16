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
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Asus
 */
@Entity
@Table(name = "tai_khoan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TaiKhoan.findAll", query = "SELECT t FROM TaiKhoan t"),
    @NamedQuery(name = "TaiKhoan.findByIdTk", query = "SELECT t FROM TaiKhoan t WHERE t.idTk = :idTk"),
    @NamedQuery(name = "TaiKhoan.findByHoTen", query = "SELECT t FROM TaiKhoan t WHERE t.hoTen = :hoTen"),
    @NamedQuery(name = "TaiKhoan.findByNgaySinh", query = "SELECT t FROM TaiKhoan t WHERE t.ngaySinh = :ngaySinh"),
    @NamedQuery(name = "TaiKhoan.findByGioiTinh", query = "SELECT t FROM TaiKhoan t WHERE t.gioiTinh = :gioiTinh"),
    @NamedQuery(name = "TaiKhoan.findByDiaChi", query = "SELECT t FROM TaiKhoan t WHERE t.diaChi = :diaChi"),
    @NamedQuery(name = "TaiKhoan.findByEmail", query = "SELECT t FROM TaiKhoan t WHERE t.email = :email"),
    @NamedQuery(name = "TaiKhoan.findBySdt", query = "SELECT t FROM TaiKhoan t WHERE t.sdt = :sdt"),
    @NamedQuery(name = "TaiKhoan.findByTaiKhoan", query = "SELECT t FROM TaiKhoan t WHERE t.taiKhoan = :taiKhoan"),
    @NamedQuery(name = "TaiKhoan.findByMatKhau", query = "SELECT t FROM TaiKhoan t WHERE t.matKhau = :matKhau"),
    @NamedQuery(name = "TaiKhoan.findByAvt", query = "SELECT t FROM TaiKhoan t WHERE t.avt = :avt")})
public class TaiKhoan implements Serializable {

    @OneToMany(mappedBy = "idBs")
    private Set<DanhGiaBs> danhGiaBsSet;
    @OneToMany(mappedBy = "idBn")
    private Set<DanhGiaBs> danhGiaBsSet1;

    

    @Transient
    private String sdtOTP;
    
    @Transient
    @Null
    private String confirmmatKhau;

    @Transient
    @Null
    @JsonIgnore
    private MultipartFile file;

    @Transient
    @Null
    private String matKhauHienTai;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tk")
    private Integer idTk;
    @Size(max = 45)
    @Column(name = "ho_ten")
    private String hoTen;
    @Column(name = "ngay_sinh")
    @Temporal(TemporalType.DATE)
    private Date ngaySinh;
    @Size(max = 45)
    @Column(name = "gioi_tinh")
    private String gioiTinh;
    @Size(max = 45)
    @Column(name = "dia_chi")
    private String diaChi;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "email")
    private String email;
    @Size(max = 45)
    @Column(name = "sdt")
    private String sdt;
    @Size(max = 45)
    @Column(name = "tai_khoan")
    private String taiKhoan;
    @Size(max = 1000)
    @Column(name = "mat_khau")
    private String matKhau;
    @Size(max = 1000)
    @Column(name = "avt")
    private String avt;
    @JoinColumn(name = "id_role", referencedColumnName = "id_role")
    @ManyToOne
    private UserRole idRole;
    @OneToMany(mappedBy = "idBn")
    @JsonIgnore
    private Set<PhieuDangKy> phieuDangKySet;
    @OneToMany(mappedBy = "idBs")
    @JsonIgnore
    private Set<PhieuDangKy> phieuDangKySet1;
    @OneToMany(mappedBy = "idYt")
    @JsonIgnore
    private Set<PhieuDangKy> phieuDangKySet2;
    @OneToMany(mappedBy = "idTk")
    @JsonIgnore
    private Set<ChiTietThoiGianTruc> chiTietThoiGianTrucSet;

    public TaiKhoan() {
    }

    public TaiKhoan(Integer idTk) {
        this.idTk = idTk;
    }

    public Integer getIdTk() {
        return idTk;
    }

    public void setIdTk(Integer idTk) {
        this.idTk = idTk;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getAvt() {
        return avt;
    }

    public void setAvt(String avt) {
        this.avt = avt;
    }

    public UserRole getIdRole() {
        return idRole;
    }

    public void setIdRole(UserRole idRole) {
        this.idRole = idRole;
    }

    @XmlTransient
    public Set<PhieuDangKy> getPhieuDangKySet() {
        return phieuDangKySet;
    }

    public void setPhieuDangKySet(Set<PhieuDangKy> phieuDangKySet) {
        this.phieuDangKySet = phieuDangKySet;
    }

    @XmlTransient
    public Set<PhieuDangKy> getPhieuDangKySet1() {
        return phieuDangKySet1;
    }

    public void setPhieuDangKySet1(Set<PhieuDangKy> phieuDangKySet1) {
        this.phieuDangKySet1 = phieuDangKySet1;
    }

    @XmlTransient
    public Set<PhieuDangKy> getPhieuDangKySet2() {
        return phieuDangKySet2;
    }

    public void setPhieuDangKySet2(Set<PhieuDangKy> phieuDangKySet2) {
        this.phieuDangKySet2 = phieuDangKySet2;
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
        hash += (idTk != null ? idTk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TaiKhoan)) {
            return false;
        }
        TaiKhoan other = (TaiKhoan) object;
        if ((this.idTk == null && other.idTk != null) || (this.idTk != null && !this.idTk.equals(other.idTk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hmh.pojo.TaiKhoan[ idTk=" + idTk + " ]";
    }

    /**
     * @return the confirmmatKhau
     */
    public String getConfirmmatKhau() {
        return confirmmatKhau;
    }

    /**
     * @param confirmmatKhau the confirmmatKhau to set
     */
    public void setConfirmmatKhau(String confirmmatKhau) {
        this.confirmmatKhau = confirmmatKhau;
    }

    /**
     * @return the file
     */
    public MultipartFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(MultipartFile file) {
        this.file = file;
    }

    /**
     * @return the matKhauHienTai
     */
    public String getMatKhauHienTai() {
        return matKhauHienTai;
    }

    /**
     * @param matKhauHienTai the matKhauHienTai to set
     */
    public void setMatKhauHienTai(String matKhauHienTai) {
        this.matKhauHienTai = matKhauHienTai;
    }

    @XmlTransient
    public Set<DanhGiaBs> getDanhGiaBsSet() {
        return danhGiaBsSet;
    }

    public void setDanhGiaBsSet(Set<DanhGiaBs> danhGiaBsSet) {
        this.danhGiaBsSet = danhGiaBsSet;
    }

    @XmlTransient
    public Set<DanhGiaBs> getDanhGiaBsSet1() {
        return danhGiaBsSet1;
    }

    public void setDanhGiaBsSet1(Set<DanhGiaBs> danhGiaBsSet1) {
        this.danhGiaBsSet1 = danhGiaBsSet1;
    }

    /**
     * @return the sdtOTP
     */
    public String getSdtOTP() {
        return sdtOTP;
    }

    /**
     * @param sdtOTP the sdtOTP to set
     */
    public void setSdtOTP(String sdtOTP) {
        this.sdtOTP = sdtOTP;
    }

   

   

}
