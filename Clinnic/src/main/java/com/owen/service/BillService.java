/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.owen.service;
import com.owen.pojo.Bill;
import java.util.List;

/**
 *
 * @author Trinh Bao Duy
 */
public interface BillService {

    List<Bill> getBills();

    boolean addOrUpdateBill(Bill m);

    boolean deleteBill(int id);

    Bill getBillById(int id);

    int tinhtien(Bill m);

    Bill getBillByApoId(int id);

    List<Integer> getRevenueByMonth(int year);
    List<Integer> getRevenueByQuarter(int year);
}
