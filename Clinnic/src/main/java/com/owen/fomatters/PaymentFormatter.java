/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.fomatters;

import com.owen.pojo.Payment;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author Trinh Bao Duy
 */
public class PaymentFormatter implements Formatter<Payment> {

    @Override
    public String print(Payment p, Locale locale) {
        return String.valueOf(p.getId());
    }

    @Override
    public Payment parse(String id, Locale locale) throws ParseException {
        return new Payment(Integer.parseInt(id));
    }
    
}
