/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.controllers;

import com.owen.pojo.Medicine;
import com.owen.pojo.User;
import com.owen.service.MedicineService;
import com.owen.service.UnitService;
import com.owen.service.UserService;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Trinh Bao Duy
 */
@Controller
@Transactional
@ControllerAdvice
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private UnitService unitService;

    @Autowired
    private CustomDateEditor customDateEditor;

    @Autowired
    private Environment env;
    
     @Autowired
    private UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, customDateEditor);
    }

    @GetMapping("/admin/quanlythuoc/{id}")
    public String update(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("medicien", this.medicineService.getMedicineById(id));
        model.addAttribute("getmediciens", this.medicineService.getMediciness(null));
        model.addAttribute("units", this.unitService.getUnits());
        return "quanlythuoc";
    }

    @GetMapping("/admin/quanlythuoc")
    public String quanlythuoc(Model model, @RequestParam Map<String, String> params,Authentication authentication) {
        model.addAttribute("medicien", new Medicine());
        model.addAttribute("getmediciens", this.medicineService.getMediciness(params));
        model.addAttribute("units", this.unitService.getUnits());
         if (authentication != null) {
            UserDetails user = this.userService.loadUserByUsername(authentication.getName());
            User u = this.userService.getUserByUsername(user.getUsername());
            model.addAttribute("admin", u);

        }
        return "quanlythuoc";
    }

    @PostMapping("/admin/quanlythuoc")
    public String XuLi(Model model, @ModelAttribute(value = "medicien") Medicine m, BindingResult rs) throws IOException {
        model.addAttribute("units", this.unitService.getUnits());
        if (this.medicineService.addOrUpdateMedicine(m) == true) {
            return "redirect:/admin/quanlythuoc";
        }
        return "quanlythuoc";
    }

}
