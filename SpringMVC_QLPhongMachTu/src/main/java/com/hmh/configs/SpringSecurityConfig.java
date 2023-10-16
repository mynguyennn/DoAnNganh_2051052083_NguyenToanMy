/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.twilio.http.TwilioRestClient;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 *
 * @author Asus
 */
@Controller
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.hmh.controllers",
    "com.hmh.repository",
    "com.hmh.service",
    "com.hmh.components"
})
@Order(2)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment env;
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    @Qualifier("customSuccessHandler")
    private CustomSuccessHandler customSuccessHandler;

    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //chung thuc
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    //phan quyen
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/dangnhap").usernameParameter("username").passwordParameter("password");

        http.formLogin().successHandler(customSuccessHandler).
                failureUrl("/dangnhap?error");

        http.logout().logoutSuccessUrl("/dangnhap");

        http.exceptionHandling().accessDeniedPage("/dangnhap?accessDenied");

        http.authorizeRequests().antMatchers("/").permitAll().
                antMatchers("/benhnhan/dangkykham/**").access("hasRole('BENHNHAN')");

        http.authorizeRequests().antMatchers("/").permitAll().
                antMatchers("/benhnhan/lichsukham/**").access("hasRole('BENHNHAN')");

        http.authorizeRequests().antMatchers("/").permitAll().
                antMatchers("/yta/lapdskham/**").access("hasRole('YTA')");

        http.authorizeRequests().antMatchers("/").permitAll().
                antMatchers("/yta/thanhtoan/**").access("hasRole('YTA')");

        http.authorizeRequests().antMatchers("/").permitAll().
                antMatchers("/yta/taohoadon/**").access("hasRole('YTA')");

        http.authorizeRequests().antMatchers("/").permitAll().
                antMatchers("/yta/dangkylichYT/**").access("hasRole('YTA')");

        http.authorizeRequests().antMatchers("/").permitAll().
                antMatchers("/bacsi/dangkylichBS/**").access("hasRole('BACSI')");

        http.authorizeRequests().antMatchers("/").permitAll().
                antMatchers("/bacsi/lapphieukham/**").access("hasRole('BACSI')");

        http.authorizeRequests().antMatchers("/").permitAll().
                antMatchers("/bacsi/khambenh/**").access("hasRole('BACSI')");

        http.authorizeRequests().antMatchers("/").permitAll().
                antMatchers("/bacsi/capthuoc/**").access("hasRole('BACSI')");

        http.authorizeRequests().antMatchers("/").permitAll().
                antMatchers("/admin/quanlythuoc/**").access("hasRole('ADMIN')");

        http.authorizeRequests().antMatchers("/").permitAll().
                antMatchers("/admin/quanlytaikhoan/**").access("hasRole('ADMIN')");

        http.authorizeRequests().antMatchers("/").permitAll().
                antMatchers("/admin/thongkebenhnhan/**").access("hasRole('ADMIN')");

        http.authorizeRequests().antMatchers("/").permitAll().
                antMatchers("/admin/thongkedoanhthu/**").access("hasRole('ADMIN')");

        http.authorizeRequests().antMatchers("/").permitAll().
                antMatchers("/admin/lichtruc/**").access("hasRole('ADMIN')");

        http.authorizeRequests().antMatchers("/").permitAll().
                antMatchers("/admin/tienkham/**").access("hasRole('ADMIN')");

        http.csrf().disable();
    }

    @Bean
    public Cloudinary cloudinary() {
        Cloudinary cloudinary
                = new Cloudinary(ObjectUtils.asMap(
                        "cloud_name", this.env.getProperty("cloudinary.cloud_name"),
                        "api_key", this.env.getProperty("cloudinary.api_id"),
                        "api_secret", this.env.getProperty("cloudinary.api_secret"),
                        "secure", true));
        return cloudinary;
    }

   
    
   
    
    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("nguyentoanmy112002@gmail.com");
        mailSender.setPassword("xcinlzwwfdzdncdb");

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        mailSender.setJavaMailProperties(properties);

        return mailSender;

    }
    
    public SimpleDateFormat simpleDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }

    @Bean
    public CustomDateEditor customDateEditor() {
        return new CustomDateEditor(simpleDateFormat(), true);
    }

}
