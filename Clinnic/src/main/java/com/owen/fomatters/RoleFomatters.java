/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.fomatters;

import java.util.Locale;
import com.owen.pojo.Role;
import org.springframework.format.Formatter;
import java.text.ParseException;

/**
 *
 * @author Trinh Bao Duy
 */
public class RoleFomatters implements Formatter<Role> {
    
    @Override
    public String print(Role role, Locale locale) {
        return String.valueOf(role.getId());
    }

    @Override
    public Role parse(String roleId, Locale locale) throws ParseException {
        return new Role(Integer.parseInt(roleId));
    }
}
