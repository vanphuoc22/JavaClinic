/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.repository.impl;

import com.owen.pojo.Appointment;
import com.owen.pojo.Bill;
import com.owen.pojo.PrescriptionItem;
import com.owen.pojo.ServiceItems;
import com.owen.pojo.Tienkham;
import com.owen.repository.BillRepository;
import com.owen.service.PrescriptionItemService;
import com.owen.service.ServiceItemService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Trinh Bao Duy
 */
@Repository
@Transactional
public class BillRepositoryImpl implements BillRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private Environment env;

    @Autowired
    private ServiceItemService serviceItemService;

    @Autowired
    private PrescriptionItemService prescriptionItemService;

    @Override
    public List<Bill> getBills() {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Bill> query = builder.createQuery(Bill.class);
        Query q = session.createQuery(query);
        List<Bill> results = q.getResultList();
        return results;
    }

    @Override
    public boolean addOrUpdateBill(Bill m) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (m.getId() == null) {
                if (this.isAppoIdExists(m.getAppoId().getId()) == true) {
                    s.save(m);
                } else {
                    s.update(m);
                }

            } else {
                s.update(m);
            }

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Bill getBillByAppoId(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Bill> query = builder.createQuery(Bill.class);
        Root<Bill> root = query.from(Bill.class);
        query.where(
                builder.and(
                        builder.equal(root.get("appoId"), id)
                )
        );
        Query q = session.createQuery(query);
        List<Bill> results = q.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    public boolean isAppoIdExists(int appoId) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Bill> query = builder.createQuery(Bill.class);
        Root<Bill> root = query.from(Bill.class);
        query.where(
                builder.and(
                        builder.equal(root.get("appoId"), appoId)
                )
        );
        Query q = session.createQuery(query);
        List<Bill> results = q.getResultList();
        if (results.isEmpty()) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean deleteBill(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        Bill me = session.get(Bill.class, id);
        try {
            session.delete(me);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public Bill getBillById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Bill> query = builder.createQuery(Bill.class);
        Root<Bill> root = query.from(Bill.class);
        query.where(
                builder.and(
                        builder.equal(root.get("id"), id)
                )
        );
        Query q = session.createQuery(query);
        List<Bill> results = q.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public Bill getBillByApoId(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Bill> query = builder.createQuery(Bill.class);
        Root<Bill> root = query.from(Bill.class);
        query.where(
                builder.and(
                        builder.equal(root.get("appoId"), id)
                )
        );
        Query q = session.createQuery(query);
        List<Bill> results = q.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public int tinhtien(Bill m) {
        Tienkham tienkhamphongmach = m.getTienkham();
        int tienkham = tienkhamphongmach.getTienkham();
        long tiendichvu = 0;
        long tienthuoc = 0;
        Appointment idappo = m.getAppoId();
        int idPre = idappo.getPrescriptionId().getId();
        List<ServiceItems> dichvus = this.serviceItemService.getServicecbyAppoID(idappo.getId());
        List<PrescriptionItem> thuocs = this.prescriptionItemService.getPrescriptionsbyIDPres(idPre);
        for (ServiceItems dichvu : dichvus) {
            long tien = dichvu.getServiceId().getPrice();
            tiendichvu += tien;
        }
        for (PrescriptionItem thuoc : thuocs) {
            BigDecimal tient = thuoc.getMedicineId().getPrice();
            int sl = thuoc.getQuantity();
            long tien = sl * tient.longValue();
            tienthuoc += tien;
        }
        int tongtien = (int) (tiendichvu + tienkham + tienthuoc);

        return tongtien;
    }

    @Override
    public List<Integer> getRevenueByMonth(int year) {
        List<Integer> revenueData = new ArrayList<>();
        try {
            Session session = this.factory.getObject().getCurrentSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Tuple> query = builder.createTupleQuery();
            Root<Appointment> appointmentRoot = query.from(Appointment.class);
            Root<Bill> billRoot = query.from(Bill.class);

            query.multiselect(
                    builder.function("MONTH", Integer.class, appointmentRoot.get("medicalappointmentDate")).alias("month"),
                    builder.sum(billRoot.get("payMoney")).alias("revenue")
            );

            Predicate joinCondition = builder.equal(appointmentRoot.get("id"), billRoot.get("appoId"));
            query.where(
                    joinCondition,
                    builder.equal(builder.function("YEAR", Integer.class, appointmentRoot.get("medicalappointmentDate")), year)
            );

            query.groupBy(builder.function("MONTH", Integer.class, appointmentRoot.get("medicalappointmentDate")));

            TypedQuery<Tuple> typedQuery = session.createQuery(query);
            List<Tuple> results = typedQuery.getResultList();

            for (int month = 1; month <= 12; month++) {
                boolean monthFound = false;
                for (Tuple result : results) {
                    Integer resultMonth = result.get("month", Integer.class);
                    if (resultMonth != null && resultMonth.equals(month)) {
                        Long revenue = result.get("revenue", Long.class);
                        revenueData.add(revenue.intValue());
                        monthFound = true;
                        break;
                    }
                }
                if (!monthFound) {
                    revenueData.add(0);
                }
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return revenueData;
    }

    @Override
    public List<Integer> getRevenueByQuarter(int year) {
        List<Integer> revenueData = new ArrayList<>();
        try {
            // Lấy danh sách doanh thu theo tháng
            List<Integer> monthlyRevenueData = getRevenueByMonth(year);

            // Tính toán doanh thu theo quý
            for (int quarter = 1; quarter <= 4; quarter++) {
                int startIndex = (quarter - 1) * 3;
                int endIndex = startIndex + 3;
                int quarterlyRevenue = 0;

                // Tính tổng doanh thu của các tháng trong quý
                for (int i = startIndex; i < endIndex; i++) {
                    quarterlyRevenue += monthlyRevenueData.get(i);
                }

                revenueData.add(quarterlyRevenue);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return revenueData;
    }

}
