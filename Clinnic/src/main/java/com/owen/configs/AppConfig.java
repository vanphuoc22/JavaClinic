/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 *
 * @author Trinh Bao Duy
 */
@Configuration
@EnableWebMvc
public class AppConfig{
    @Bean
    public CustomSuccessHandler cusstomSuccessHandler(){
        return new CustomSuccessHandler(); 
    }
}
