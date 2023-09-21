/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.service.impl;

import com.owen.pojo.Appointment;
import com.owen.pojo.Bill;
import com.owen.repository.BillRepository;
import com.owen.service.BillService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Trinh Bao Duy
 */
@Service
public class BillServiceImpl implements BillService{
    
    @Autowired
    private BillRepository billRepository;

    @Override
    public List<Bill> getBills() {
        return this.billRepository.getBills();
    }

    @Override
    public boolean addOrUpdateBill(Bill m) {
        return this.billRepository.addOrUpdateBill(m);
    }

    @Override
    public boolean deleteBill(int id) {
        return this.billRepository.deleteBill(id);
    }
    
    @Override
    public Bill getBillById(int id) {
        return this.billRepository.getBillById(id);
    }

    @Override
    public int tinhtien(Bill m) {
        return this.billRepository.tinhtien(m);
    }

    @Override
    public Bill getBillByApoId(int id) {
        return this.billRepository.getBillByApoId(id);
    }

    @Override
    public List<Integer> getRevenueByMonth(int year) {
        return this.billRepository.getRevenueByMonth(year);
    }

    @Override
    public List<Integer> getRevenueByQuarter(int year) {
        return this.billRepository.getRevenueByQuarter(year);
    }

}
