/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.service.impl;

import com.owen.pojo.Medicine;
import com.owen.pojo.PrescriptionItem;
import com.owen.repository.MedicineRepository;
import com.owen.service.MedicineService;
import com.owen.service.PrescriptionItemService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Trinh Bao Duy
 */
@Service
public class MedicineServiceImpl implements MedicineService{
    
    @Autowired
    private MedicineRepository medicineRepository;
    
    @Autowired
    private PrescriptionItemService PrescriptionItemService;

    @Override
    public List<Object> getMediciness(Map<String, String> params) {
        return this.medicineRepository.getMediciness(params);
    }

    @Override
    public boolean addOrUpdateMedicine(Medicine m) {
        return this.medicineRepository.addOrUpdateMedicine(m);
    }

    @Override
    public boolean deleteMedicine(int id) {
        if(this.PrescriptionItemService.getPrescriptionsbyIDMedicine(id)!=null){
            List<PrescriptionItem> ds = this.PrescriptionItemService.getPrescriptionsbyIDMedicine(id);
            for( PrescriptionItem pre : ds){
                this.PrescriptionItemService.deletePres(pre.getId());
            }
        }
        return this.medicineRepository.deleteMedicine(id);
    }

    @Override
    public Medicine getMedicineById(int id) {
        return this.medicineRepository.getMedicineById(id);
    }
    
}
