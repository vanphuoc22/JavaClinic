/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.owen.repository;

import com.owen.pojo.Appointment;
import com.owen.pojo.Bill;
import java.util.List;

/**
 *
 * @author Trinh Bao Duy
 */
public interface BillRepository {

    List<Bill> getBills();

    boolean addOrUpdateBill(Bill m);

    boolean deleteBill(int id);

    Bill getBillById(int id);

    int tinhtien(Bill m);

    Bill getBillByApoId(int id);

    List<Integer> getRevenueByMonth(int year);
    
    List<Integer> getRevenueByQuarter(int year);
}
