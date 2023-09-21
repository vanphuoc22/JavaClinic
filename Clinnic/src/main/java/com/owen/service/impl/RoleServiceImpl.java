/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.service.impl;

import com.owen.pojo.Role;
import com.owen.service.RoleService;
import java.util.List;
import com.owen.repository.RoleReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Trinh Bao Duy
 */
@Service
public class RoleServiceImpl implements RoleService {
    
    @Autowired
    private RoleReponsitory roleReponsitory;
    
    @Override
    public List<Role> getRoles() {
        return this.roleReponsitory.getRoles();
    }

    @Override
    public Role getRoleById(int id) {
        return this.roleReponsitory.getRoleById(id);
    }
    
}
