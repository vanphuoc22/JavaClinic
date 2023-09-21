/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.controllers;


import com.owen.pojo.User;
import com.owen.service.RoleService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ControllerAdvice;
import com.owen.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Trinh Bao Duy
 */
@Controller
@ControllerAdvice
@PropertySource("classpath:configs.properties")
public class IndexController {

    
    @Autowired
    private UserService userService;
    
    @Autowired
    private RoleService roleService;

    @Autowired
    private Environment env;

    @RequestMapping("/")
    @Transactional
    public String index(Model model, Authentication authentication) {
         if (authentication != null) {
            UserDetails user = this.userService.loadUserByUsername(authentication.getName());
            User u = this.userService.getUserByUsername(user.getUsername());
            if(u.getRoleId().getId().equals(1)){
                model.addAttribute("admin", u);
                return "indexadmin";
            }
            if(u.getRoleId().getId().equals(2)){
                model.addAttribute("doctor", u);
                return "indexbacsi";
            }
            if(u.getRoleId().getId().equals(3)){
                model.addAttribute("nurse", u);
                return "indexyta";
            }
            
        }
        return "index";
    }
    
    @ModelAttribute
    public void commonAttr(Model model) {
        model.addAttribute("roles", this.roleService.getRoles());
    }
    
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }
     @GetMapping("/home")
    public String home() {
        return "home";
    }   
}
