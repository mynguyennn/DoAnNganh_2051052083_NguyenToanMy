/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.hmh.pojo.ChiTietThoiGianTruc;
import com.hmh.pojo.TaiKhoan;
import com.hmh.pojo.ThoiGianTruc;
import com.hmh.pojo.UserRole;
import com.hmh.repository.LichTrucRepository;
import com.hmh.repository.TaiKhoanRepository;
import com.hmh.service.TaiKhoanService;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author Asus
 */
@Service("userDetailsService")
public class TaiKhoanServiceImpl implements TaiKhoanService {

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private LichTrucRepository lichTrucRepository;

    @Override
    public boolean addTaiKhoan(TaiKhoan tk) {
        String pass = tk.getMatKhau();
        if (tk.getIdTk() == null) {
            tk.setMatKhau(this.passwordEncoder.encode(pass));
        }
        tk.setIdRole(this.getRoleBenhNhan("ROLE_BENHNHAN"));

        if (!tk.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(tk.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                tk.setAvt(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(TaiKhoanServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return this.taiKhoanRepository.addTaiKhoan(tk);
    }

    @Override
    public List<TaiKhoan> getTaiKhoan(String username) {
        return this.taiKhoanRepository.getTaiKhoan(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<TaiKhoan> users = this.getTaiKhoan(username);
        TaiKhoan user = users.get(0);
        if (users.isEmpty()) {
            throw new UsernameNotFoundException("Tài khoản không tồn tại!");
        }

        boolean canLogin = false;

        List<Object> chiTietThoiGianTrucList = lichTrucRepository.getChiTietThoiGianTrucByIDTK(user);

        if (chiTietThoiGianTrucList.isEmpty()) {
            canLogin = true;
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

            Date currentDate = new Date();
            Date currentTime = new Date();

            String formattedDate = formatter.format(currentDate);
            String currentTimeStr = timeFormat.format(currentTime);

            try {
                Date ngayHienTai = formatter.parse(formattedDate);
                Date gioHienTai = timeFormat.parse(currentTimeStr);

                for (Object chiTiet : chiTietThoiGianTrucList) {
                    ChiTietThoiGianTruc chiTietThoiGianTruc = (ChiTietThoiGianTruc) chiTiet;

                    ThoiGianTruc thoiGianTruc = chiTietThoiGianTruc.getIdTgTruc();

                    Date startTime = thoiGianTruc.getBatDau();
                    Date endTime = thoiGianTruc.getKetThuc();

                    Date ngayDkyTruc = chiTietThoiGianTruc.getNgayDkyTruc();

                    if (ngayDkyTruc.equals(ngayHienTai) && gioHienTai.after(startTime) && gioHienTai.before(endTime)) {
                        canLogin = true;
                        break;
                    }
                }
            } catch (ParseException ex) {
                Logger.getLogger(TaiKhoanServiceImpl.class.getName()).log(Level.SEVERE, "Lỗi khi phân tích ngày tháng", ex);
            }
        }

        if (canLogin) {
            Set<GrantedAuthority> authorities = new HashSet<>();
            authorities.add(new SimpleGrantedAuthority(user.getIdRole().getChucVu()));
            return new org.springframework.security.core.userdetails.User(user.getTaiKhoan(), user.getMatKhau(), authorities);

        } else {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            request.setAttribute("loginError", "Ngoài giờ làm việc, vui lòng quay lại sau!");
        }
        return null;
    }

    @Override
    public UserRole getRoleBenhNhan(String role) {
        return this.taiKhoanRepository.getRoleBenhNhan(role);
    }

    @Override
    public TaiKhoan getTaiKhoanById(int id) {
        return this.taiKhoanRepository.getTaiKhoanById(id);
    }

    @Override
    public TaiKhoan getTaiKhoanByUsername(String username) {
        return this.taiKhoanRepository.getTaiKhoanByUsername(username);
    }

    @Override
    public boolean doiMatKhau(TaiKhoan tk) {
        return taiKhoanRepository.doiMatKhau(tk);

    }

    @Override
    public boolean authUser(String username, String password) {
        return taiKhoanRepository.authUser(username, password);

    }

    @Override
    public TaiKhoan addUser(Map<String, String> params, MultipartFile avatar) {
        TaiKhoan u = new TaiKhoan();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = null;

        try {
            parsedDate = dateFormat.parse(params.get("ngaySinh"));
        } catch (ParseException ex) {
            Logger.getLogger(TaiKhoanServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        u.setHoTen(params.get("hoTen"));
        u.setSdt(params.get("sdt"));
        u.setEmail(params.get("email"));
        u.setDiaChi(params.get("diaChi"));
        u.setTaiKhoan(params.get("taiKhoan"));
        u.setGioiTinh(params.get("gioiTinh"));
        u.setNgaySinh(parsedDate);
        u.setConfirmmatKhau(params.get("confirmmatKhau"));
        u.setMatKhau(this.passwordEncoder.encode(params.get("matKhau")));
        u.setIdRole(this.getRoleBenhNhan("ROLE_BENHNHAN"));
        if (!avatar.isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(avatar.getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                u.setAvt(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(TaiKhoanService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        this.taiKhoanRepository.addUser(u);
        return u;
    }

    @Override
    public TaiKhoan thayDoiMatKhau(Map<String, String> params) {
        TaiKhoan tk = this.taiKhoanRepository.getTaiKhoanByUsername(params.get("taiKhoan").toString());
        tk.setMatKhau(this.passwordEncoder.encode(params.get("matKhauMoi")));
        this.taiKhoanRepository.thayDoiMatKhau(tk);
        return tk;
    }

    @Override
    public TaiKhoan loadUserByUsernameQuenPass(String username) throws UsernameNotFoundException {
        List<TaiKhoan> users = this.getTaiKhoan(username);
        TaiKhoan user = users.get(0);
        if (users.isEmpty()) {
            throw new UsernameNotFoundException("Tài khoản không tồn tại!");
        }
        return user;
    }

    @Override
    public List<TaiKhoan> getListTaiKhoan() {
        return taiKhoanRepository.getListTaiKhoan();
    }

}
