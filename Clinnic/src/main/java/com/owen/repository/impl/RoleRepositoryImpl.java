package com.owen.repository.impl;

import com.owen.pojo.Role;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import com.owen.repository.RoleReponsitory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Trinh Bao Duy
 */
@Repository
@Transactional
public class RoleRepositoryImpl implements  RoleReponsitory{
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Role> getRoles() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Role");
        
        return q.getResultList();
    }

    @Override
    public Role getRoleById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Role.class, id);
    }
}

