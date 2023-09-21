/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.repository.impl;

import com.owen.pojo.Appointment;
import com.owen.pojo.Medicine;
import com.owen.pojo.PrescriptionItem;
import com.owen.repository.PrescriptionItemRepository;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
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
public class PrescriptionItemRepositoryImpl implements PrescriptionItemRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private Environment env;

    @Override
    public boolean addOrUpdatePrescriptionItem(PrescriptionItem m, int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Appointment p = s.get(Appointment.class, id);
        try {
            if (m.getId() == null) {
                s.save(m);
                m.setPrescriptionId(p.getPrescriptionId());
                p.setMedicalappointmentDate(new Date());
            } else {
                s.update(m);
            }

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<PrescriptionItem> getPrescriptionsbyIDPres(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PrescriptionItem> query = builder.createQuery(PrescriptionItem.class);
        Root<PrescriptionItem> root = query.from(PrescriptionItem.class);

        query.select(root).where(builder.equal(root.get("prescriptionId"), id));

        return session.createQuery(query).getResultList();
    }

    @Override
    public List<PrescriptionItem> getPrescriptionsbyIDMedicine(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PrescriptionItem> query = builder.createQuery(PrescriptionItem.class);
        Root<PrescriptionItem> root = query.from(PrescriptionItem.class);

        query.select(root).where(builder.equal(root.get("medicineId"), id));

        return session.createQuery(query).getResultList();
    }

    @Override
    public boolean deletePres(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        PrescriptionItem me = session.get(PrescriptionItem.class, id);
        try {
            int idthuoc = me.getMedicineId().getId();
            Medicine thuoc = session.get(Medicine.class, idthuoc);
            int slban = me.getQuantity();
            int slkho = thuoc.getQuantity();
            int naplai = slkho + slban;
            thuoc.setQuantity(naplai);
            session.delete(me);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return false;
    }

}
