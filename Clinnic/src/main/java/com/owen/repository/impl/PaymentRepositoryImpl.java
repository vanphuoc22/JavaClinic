/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.repository.impl;

import com.owen.pojo.Payment;
import com.owen.repository.PaymentRepository;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Trinh Bao Duy
 */
@Transactional
@Repository
public class PaymentRepositoryImpl implements PaymentRepository{
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Payment> getPayments() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Payment");

        return q.getResultList();
    }
    
}
