/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.service;

import com.owen.pojo.Service;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Trinh Bao Duy
 */
public interface ServiceService {

    List<Service> getServices(Map<String, String> params);

    boolean addOrUpdateService(Service m);

    boolean deleteService(int id);
    long tiencuadichvu(int s);
}
