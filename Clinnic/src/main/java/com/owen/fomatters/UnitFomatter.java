/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.fomatters;

import com.owen.pojo.Unit;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author Trinh Bao Duy
 */
public class UnitFomatter implements Formatter<Unit>{

    @Override
    public String print(Unit object, Locale locale) {
        return String.valueOf(object.getId());
    }

    @Override
    public Unit parse(String idUnit, Locale locale) throws ParseException {
         return new Unit(Integer.parseInt(idUnit));
    }
    
    
}
