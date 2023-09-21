/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.service;

import com.owen.pojo.Medicine;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Trinh Bao Duy
 */
public interface MedicineService {
    List<Object> getMediciness(Map<String, String> params);
    boolean addOrUpdateMedicine(Medicine m);
    boolean deleteMedicine(int id);
    Medicine getMedicineById(int id);
}
