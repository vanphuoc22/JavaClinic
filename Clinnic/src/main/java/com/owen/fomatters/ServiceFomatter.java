/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.fomatters;

import com.owen.pojo.Service;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author Trinh Bao Duy
 */
public class ServiceFomatter implements Formatter<Service> {

    @Override
    public String print(Service object, Locale locale) {
        return String.valueOf(object.getId());
    }

    @Override
    public Service parse(String id, Locale locale) throws ParseException {
        return new Service(Integer.parseInt(id));
    }

}
