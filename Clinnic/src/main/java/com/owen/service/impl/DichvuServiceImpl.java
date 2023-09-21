/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.service.impl;

import com.owen.pojo.Service;
import com.owen.repository.ServiceRepository;
import com.owen.service.ServiceService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;


/**
 *
 * @author Trinh Bao Duy
 */

@org.springframework.stereotype.Service
public class DichvuServiceImpl implements  ServiceService{
    
    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public List<Service> getServices(Map<String, String> params) {
            return this.serviceRepository.getServices(params);
    }

    @Override
    public boolean addOrUpdateService(Service m) {
        return this.serviceRepository.addOrUpdateService(m);
    }

    @Override
    public boolean deleteService(int id) {
        return this.serviceRepository.deleteService(id);
    }

    @Override
    public long tiencuadichvu(int s) {
        return this.serviceRepository.tiencuadichvu(s);
    }
    
}
