/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.service.impl;

import com.owen.pojo.Shift;
import com.owen.repository.ShiftRepository;
import com.owen.service.ShiftService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Trinh Bao Duy
 */
@Service
public class ShiftServiceImpl implements ShiftService{

    @Autowired
    private ShiftRepository shiftRepository;
    
    @Override
    public List<Shift> getShifts() {
        return this.shiftRepository.getShifts();
    }
    
}
