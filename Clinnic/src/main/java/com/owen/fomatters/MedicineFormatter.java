/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.fomatters;

import com.owen.pojo.Medicine;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author Trinh Bao Duy
 */
public class MedicineFormatter implements Formatter<Medicine>{

    @Override
    public String print(Medicine m, Locale locale) {
        return String.valueOf(m.getId());
    }

    @Override
    public Medicine parse(String id, Locale locale) throws ParseException {
        return new Medicine(Integer.parseInt(id));
    }
    
}
