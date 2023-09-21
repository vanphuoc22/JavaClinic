/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.controllers;

import com.owen.pojo.Appointment;
import com.owen.pojo.Medicine;
import com.owen.pojo.User;
import com.owen.service.AppointmentService;
import com.owen.service.UserService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Trinh Bao Duy
 */
@RestController
@RequestMapping("/api")
public class ApiAppointmentController {

    @Autowired
    private AppointmentService appointmentService;

//    @DeleteMapping("/admin/quanlythuoc/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void delete(Model model, @PathVariable(value = "id") int id) {
//        this.medicineService.deleteMedicine(id);
//
//    }
    @Autowired
    private UserService userService;

    @PostMapping("/appointments")
    @CrossOrigin
    public ResponseEntity<Appointment> list(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.appointmentService.dangkykham(params), HttpStatus.OK);
    }

    @GetMapping("/appointments")
    @CrossOrigin
    public ResponseEntity<List<Appointment>> listphieu() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        User usercurrent = this.userService.getUserByUsername(userDetails.getUsername());
        return new ResponseEntity<>(this.appointmentService.getAppointmentsbySickperson(usercurrent.getId()), HttpStatus.OK);
    }

    @DeleteMapping("/appointments/{id}")
    @CrossOrigin
    public ResponseEntity<Boolean> deleteById(@PathVariable(value = "id") int id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        User usercurrent = this.userService.getUserByUsername(userDetails.getUsername());
        Boolean res = this.appointmentService.deleteAppo(id);
        return new ResponseEntity<>(res, HttpStatus.NO_CONTENT);
    }
}
