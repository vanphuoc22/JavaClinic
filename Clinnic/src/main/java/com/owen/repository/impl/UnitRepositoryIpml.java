/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.repository.impl;

import com.owen.pojo.Role;
import com.owen.repository.UnitRepository;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Trinh Bao Duy
 */
@Repository
@Transactional
public class UnitRepositoryIpml implements UnitRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Role> getUnits() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Unit");

        return q.getResultList();
    }

}
