/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.repository;

import com.owen.pojo.Payment;
import java.util.List;

/**
 *
 * @author Trinh Bao Duy
 */
public interface PaymentRepository {
     List<Payment> getPayments();
}
