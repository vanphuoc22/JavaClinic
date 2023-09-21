/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.fomatters;

import com.owen.pojo.Tienkham;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author Trinh Bao Duy
 */
public class TienKhamFormatter implements Formatter<Tienkham> {

    @Override
    public String print(Tienkham object, Locale locale) {
        return String.valueOf(object.getId());
    }

    @Override
    public Tienkham parse(String id, Locale locale) throws ParseException {
        return new Tienkham(Integer.parseInt(id));
    }

}
