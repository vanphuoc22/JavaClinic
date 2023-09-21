/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.repository;

import com.owen.pojo.Prescription;
import com.owen.pojo.PrescriptionItem;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Trinh Bao Duy
 */
public interface PrescriptionItemRepository {

    boolean addOrUpdatePrescriptionItem(PrescriptionItem m, int id);

    List<PrescriptionItem> getPrescriptionsbyIDPres(int id);
    
    boolean deletePres(int id);
     List<PrescriptionItem> getPrescriptionsbyIDMedicine(int id);
}
