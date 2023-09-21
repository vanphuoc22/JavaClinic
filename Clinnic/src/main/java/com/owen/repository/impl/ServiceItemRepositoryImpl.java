/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.repository.impl;

import com.owen.pojo.Appointment;
import com.owen.pojo.PrescriptionItem;
import com.owen.pojo.Service;
import com.owen.pojo.ServiceItems;
import com.owen.repository.ServiceItemRepository;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Trinh Bao Duy
 */
@Repository
@Transactional
public class ServiceItemRepositoryImpl implements ServiceItemRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public boolean addOrUpdateServiceItem(ServiceItems m, int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Appointment a = s.get(Appointment.class, id);
        try {
            if (m.getId() == null) {
                if (m.getListdichvu().length > 0) {
                    for (Service dichvu : m.getListdichvu()) {
                        ServiceItems ser = new ServiceItems();
                        ser.setAppoId(a);
                        ser.setDateSer(m.getDateSer());
                        ser.setServiceId(dichvu);
                        s.save(ser);
                    }
                   
                }else {
                    m.setAppoId(a);
                    s.save(m);           
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

    @Override
    public List<ServiceItems> getServicecbyAppoID(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ServiceItems> query = builder.createQuery(ServiceItems.class);
        Root<ServiceItems> root = query.from(ServiceItems.class);

        query.select(root).where(builder.equal(root.get("appoId"), id));

        return session.createQuery(query).getResultList();
    }
    
    @Override
    public boolean deleteServiceItems(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        ServiceItems ser = session.get(ServiceItems.class, id);
        try {
            session.delete(ser);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return false;

    }

}
