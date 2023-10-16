/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.hmh.formatter.DichVuFormatter;
import com.hmh.formatter.DonViFormatter;
import com.hmh.formatter.KhamBenhFormatter;
import com.hmh.formatter.LapDsKhamFormatter;
import com.hmh.formatter.LoaiThuocFormatter;
import com.hmh.formatter.ThuocFomatter;
import com.hmh.formatter.UserRoleFormatter;
import com.twilio.http.TwilioRestClient;

import java.text.SimpleDateFormat;
import java.util.Properties;
import javax.xml.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.format.FormatterRegistry;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author Asus
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.hmh.controllers", "com.hmh.repository", "com.hmh.service"})
@EnableTransactionManagement
@PropertySource("classpath:configs.properties")
public class WebApplicationContextConfig implements WebMvcConfigurer {

    @Autowired
    private Environment env;

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new UserRoleFormatter());
        registry.addFormatter(new LapDsKhamFormatter());
        registry.addFormatter(new KhamBenhFormatter());
        registry.addFormatter(new ThuocFomatter());
        registry.addFormatter(new DichVuFormatter());
        registry.addFormatter(new DonViFormatter());
        registry.addFormatter(new LoaiThuocFormatter());
    }

//    @Bean
//    public InternalResourceViewResolver getInternalResourceViewResolver() {
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setViewClass(JstlView.class);
//        resolver.setPrefix("/WEB-INF/page/");
//        resolver.setSuffix(".jsp");
//        return resolver;
//    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/resources/css/");
        registry.addResourceHandler("/img/**").addResourceLocations("/WEB-INF/resources/image/");
        registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/resources/js/");
        registry.addResourceHandler("/font/**").addResourceLocations("/WEB-INF/resources/font/");
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        return resolver;
    }

    @Bean
    public Cloudinary cloudinary() {
        Cloudinary cloudinary
                = new Cloudinary(ObjectUtils.asMap(
                        "cloud_name", env.getProperty("cloudinary.cloud_name"),
                        "api_key", env.getProperty("cloudinary.api_id"),
                        "api_secret", env.getProperty("cloudinary.api_secret"),
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
    
    
    
     
    

//    @Bean
//    public MessageSource messageSource() {
//        ResourceBundleMessageSource m = new ResourceBundleMessageSource();
//        m.addBasenames("messages");
//
//        return m;
//    }
//
//    @Bean(name = "validator")
//    public LocalValidatorFactoryBean validator() {
//        LocalValidatorFactoryBean bean
//                = new LocalValidatorFactoryBean();
//        bean.setValidationMessageSource(messageSource());
//        return bean;
//    }
//
//    @Override
//    public org.springframework.validation.Validator getValidator() {
//        return validator();
//    }
}
