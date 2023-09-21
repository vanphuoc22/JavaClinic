/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.controllers;

import com.owen.pojo.Appointment;
import com.owen.pojo.User;
import com.owen.service.AppointmentService;
import com.owen.service.UserService;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Trinh Bao Duy
 */
@Controller
public class AppointmentController {

    @Autowired
    private Environment env;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private UserService userService;

    @GetMapping("/lich")
    public String lich(Model model, @RequestParam Map<String, String> params, Authentication authentication) {
        model.addAttribute("Appoment", new Appointment());
        model.addAttribute("Apo", this.appointmentService.getAppointments(params));
        model.addAttribute("UnApo", this.appointmentService.getAppointmentsunfished());
        model.addAttribute("user", new User());
        if (authentication != null) {
            UserDetails user = this.userService.loadUserByUsername(authentication.getName());
            User u = this.userService.getUserByUsername(user.getUsername());
            model.addAttribute("user", u);
        }
        return "lich";
    }

//    @GetMapping("/lich/{id}")
//    public String xuli(Model model, @RequestParam Map<String, String> params, @PathVariable(value = "id") int id, Authentication authentication) {
//        model.addAttribute("user", new User());
//        if (authentication != null) {
//            UserDetails user = this.userService.loadUserByUsername(authentication.getName());
//            User u = this.userService.getUserByUsername(user.getUsername());
//            model.addAttribute("user", u);
//            if (this.appointmentService.changestatus(id, u) == true) {
//            return "forward:/lich";
//        }
//        }
//        
//        return "lich";
//
//    }
//    @RequestMapping("/lich")
//    public String doituongNurse(Model model, Authentication authentication) {
//        
//    }
}
