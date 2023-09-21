/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.fomatters;

import com.owen.pojo.User;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author Trinh Bao Duy
 */
public class UserFormatter implements Formatter<User>{
    @Override
    public User parse(String text, Locale locale) throws ParseException {
        return new User(Integer.parseInt(text));
    }

    @Override
    public String print(User tk, Locale locale) {
        return  String.valueOf(tk.getId());
    }
}
