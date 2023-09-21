/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.fomatters;

import com.owen.pojo.Prescription;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author Trinh Bao Duy
 */
public class PrescriptionFomatter implements Formatter<Prescription> {

    @Override
    public String print(Prescription p, Locale locale) {
        return String.valueOf(p.getId());
    }

    @Override
    public Prescription parse(String id, Locale locale) throws ParseException {
        return new Prescription(Integer.parseInt(id));
    }
}
