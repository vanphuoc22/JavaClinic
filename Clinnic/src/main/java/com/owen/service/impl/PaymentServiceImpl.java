/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.service.impl;

import com.owen.pojo.Payment;
import com.owen.repository.PaymentRepository;
import com.owen.service.PaymentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Trinh Bao Duy
 */

@Service
public class PaymentServiceImpl implements PaymentService{
    
    @Autowired
    private PaymentRepository paymentRepository;
    @Override
    public List<Payment> getPayments() {
        return this.paymentRepository.getPayments();
    }
    
}
