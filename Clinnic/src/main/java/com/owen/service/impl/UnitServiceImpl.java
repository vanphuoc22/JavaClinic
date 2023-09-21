/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.service.impl;

import com.owen.pojo.Role;
import com.owen.repository.UnitRepository;
import com.owen.service.UnitService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Trinh Bao Duy
 */
@Service
public class UnitServiceImpl implements  UnitService{

    @Autowired
    private UnitRepository unitRepository;
    
    @Override
    public List<Role> getUnits() {
        return this.unitRepository.getUnits();
    }
    
}
