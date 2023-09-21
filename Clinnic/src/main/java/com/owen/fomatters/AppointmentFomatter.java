/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.fomatters;

import com.owen.pojo.Appointment;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author Trinh Bao DuyAppointment
 */
public class AppointmentFomatter implements Formatter<Appointment> {

    @Override
    public String print(Appointment object, Locale locale) {
        return String.valueOf(object.getId());
    }

    @Override
    public Appointment parse(String id, Locale locale) throws ParseException {
        return new Appointment(Integer.parseInt(id));
    }
    
}
