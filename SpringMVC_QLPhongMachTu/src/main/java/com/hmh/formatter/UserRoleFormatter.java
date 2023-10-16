/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hmh.formatter;

import com.hmh.pojo.UserRole;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author Asus
 */
public class UserRoleFormatter implements Formatter<UserRole> {

    @Override
    public UserRole parse(String text, Locale locale) throws ParseException {
        return new UserRole(Integer.parseInt(text));
    }

    @Override
    public String print(UserRole role, Locale locale) {
        return  String.valueOf(role.getIdRole());
    }

}
