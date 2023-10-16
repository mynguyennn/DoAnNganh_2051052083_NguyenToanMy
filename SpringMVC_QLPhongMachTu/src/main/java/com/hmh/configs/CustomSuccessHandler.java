/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.configs;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 *
 * @author Asus
 */
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest hsr, HttpServletResponse hsr1, Authentication a) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authoritys = a.getAuthorities();

        for (GrantedAuthority authority : authoritys) {
            if (authority.getAuthority().equals("ROLE_YTA")) {
                redirectStrategy.sendRedirect(hsr, hsr1, "/yta/lapdskham");
                return;
            } else if (authority.getAuthority().equals("ROLE_YTA")) {
                redirectStrategy.sendRedirect(hsr, hsr1, "/yta/thanhtoan");
                return;
            } else if (authority.getAuthority().equals("ROLE_YTA")) {
                redirectStrategy.sendRedirect(hsr, hsr1, "/yta/taohoadon");
                return;
            } else if (authority.getAuthority().equals("ROLE_YTA")) {
                redirectStrategy.sendRedirect(hsr, hsr1, "/yta/dangkylichYT");
                return;
            } else if (authority.getAuthority().equals("ROLE_BACSI")) {
                redirectStrategy.sendRedirect(hsr, hsr1, "/bacsi/lapphieukham");
                return;
            } else if (authority.getAuthority().equals("ROLE_BACSI")) {
                redirectStrategy.sendRedirect(hsr, hsr1, "/bacsi/dangkylichBS");
                return;
            } else if (authority.getAuthority().equals("ROLE_BACSI")) {
                redirectStrategy.sendRedirect(hsr, hsr1, "/bacsi/khambenh");
                return;
            } else if (authority.getAuthority().equals("ROLE_BACSI")) {
                redirectStrategy.sendRedirect(hsr, hsr1, "/bacsi/capthuoc");
                return;
            } else if (authority.getAuthority().equals("ROLE_BENHNHAN")) {
                redirectStrategy.sendRedirect(hsr, hsr1, "/");
                return;
            } else if (authority.getAuthority().equals("ROLE_ADMIN")) {
                redirectStrategy.sendRedirect(hsr, hsr1, "/admin/quanlytaikhoan");
                return;
            } else if (authority.getAuthority().equals("ROLE_ADMIN")) {
                redirectStrategy.sendRedirect(hsr, hsr1, "/admin/quanlythuoc");
                return;
            } else if (authority.getAuthority().equals("ROLE_ADMIN")) {
                redirectStrategy.sendRedirect(hsr, hsr1, "/admin/thongkebenhnhan");
                return;
            } else if (authority.getAuthority().equals("ROLE_ADMIN")) {
                redirectStrategy.sendRedirect(hsr, hsr1, "/admin/thongkedoanhthu");
                return;
            } else if (authority.getAuthority().equals("ROLE_ADMIN")) {
                redirectStrategy.sendRedirect(hsr, hsr1, "/admin/lichtruc");
                return;
            } else if (authority.getAuthority().equals("ROLE_ADMIN")) {
                redirectStrategy.sendRedirect(hsr, hsr1, "/admin/tienkham");
                return;
            }

        }
    }
}
