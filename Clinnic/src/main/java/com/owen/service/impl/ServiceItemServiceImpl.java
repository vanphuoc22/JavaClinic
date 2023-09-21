/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.service.impl;

import com.owen.pojo.ServiceItems;
import com.owen.repository.ServiceItemRepository;
import com.owen.service.ServiceItemService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Trinh Bao Duy
 */
@Service
public class ServiceItemServiceImpl implements ServiceItemService{

    @Autowired
    private ServiceItemRepository serviceItemRepository;
    
    @Override
    public boolean addOrUpdateServiceItem(ServiceItems m,int id) {
        return this.serviceItemRepository.addOrUpdateServiceItem(m,id);
    }

    @Override
    public List<ServiceItems> getServicecbyAppoID(int id) {
        return this.serviceItemRepository.getServicecbyAppoID(id);
    }

    @Override
    public boolean deleteServiceItems(int id) {
        return this.serviceItemRepository.deleteServiceItems(id);
    }

    
    
}
