/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.fomatters;

import com.owen.pojo.Shift;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author Trinh Bao Duy
 */
public class ShiftFomatter implements Formatter<Shift>{

    @Override
    public String print(Shift Shift, Locale locale) {
        return String.valueOf(Shift.getId());
    }

    @Override
    public Shift parse(String ShiftID, Locale locale) throws ParseException {
        return new Shift(Integer.parseInt(ShiftID));
    }
    
}
