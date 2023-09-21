/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.service;

import com.owen.pojo.Prescription;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Trinh Bao Duy
 */
public interface PrescriptionService {
    List<Prescription> getPrescriptions(Map<String, String> params);
    boolean addOrUpdatePrescription(Prescription m, int id);
    boolean deletePrescription(int id);
    String getDoctorPrescribeMedicine(int id);
    Prescription getPrescriptionById(int id);
}
