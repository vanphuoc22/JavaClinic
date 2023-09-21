/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.configs;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 *
 * @author Trinh Bao Duy
 */
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest hsr, HttpServletResponse hsr1, Authentication a) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authoritys = a.getAuthorities();

        for (GrantedAuthority authority : authoritys) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                hsr1.sendRedirect(String.format("%s/admin", hsr.getContextPath()));
            } else if (authority.getAuthority().equals("ROLE_DOCTOR")) {
                hsr1.sendRedirect(String.format("%s/doctor", hsr.getContextPath()));
            } else if (authority.getAuthority().equals("ROLE_NURSE")) {
                hsr1.sendRedirect(String.format("%s/nurse", hsr.getContextPath()));
            } else if (authority.getAuthority().equals("ROLE_SICKPERSON")) {
                hsr1.sendRedirect(String.format("%s/user/booking", hsr.getContextPath()));
            }
        }
    }

}
