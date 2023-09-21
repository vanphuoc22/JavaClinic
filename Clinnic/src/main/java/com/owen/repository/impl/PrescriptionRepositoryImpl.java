/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.repository.impl;

import com.owen.pojo.Appointment;
import com.owen.pojo.Prescription;
import com.owen.repository.PrescriptionRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
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
public class PrescriptionRepositoryImpl implements PrescriptionRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private Environment env;

    @Override
    public List<Prescription> getPrescriptions(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Prescription> q = b.createQuery(Prescription.class);
        Root<Prescription> root = q.from(Prescription.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String kw = params.get("name");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(b.like(root.get("name"), String.format("%%%s%%", kw)));
            }

            q.where(predicates.toArray(new Predicate[predicates.size()]));
        }

//        q.orderBy(b.desc(root.get("id")));
        Query query = session.createQuery(q);

        if (params != null) {
            String page = params.get("page");
            if (page != null && !page.isEmpty()) {
                int p = Integer.parseInt(page);
                int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));

                query.setMaxResults(pageSize);
                query.setFirstResult((p - 1) * pageSize);
            }
        }

        return query.getResultList();
    }

    @Override
    public boolean deletePrescription(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        Prescription pr = session.get(Prescription.class, id);
        try {
            session.delete(pr);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public String getDoctorPrescribeMedicine(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root<Appointment> rAppo = q.from(Appointment.class);
        Root<Prescription> rPres = q.from(Prescription.class);

        q.where(b.equal(rAppo.get("prescriptionId"), rPres.get("id")),
                b.equal(rPres.get("id"), id));

        q.multiselect(rAppo.get("doctorId").get("name"));

        Query query = s.createQuery(q);
        String doctorName = (String) query.getSingleResult();
        return doctorName;
    }

    @Override
    public boolean addOrUpdatePrescription(Prescription m, int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Appointment a = s.get(Appointment.class, id);
        try {
            if (m.getId() == null) {
                s.save(m);
                a.setPrescriptionId(m);

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
    public Prescription getPrescriptionById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Prescription> query = builder.createQuery(Prescription.class);
        Root<Prescription> root = query.from(Prescription.class);
        query.where(builder.equal(root.get("id"), id));
        Query q = session.createQuery(query);
        List<Prescription> results = q.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }
}
