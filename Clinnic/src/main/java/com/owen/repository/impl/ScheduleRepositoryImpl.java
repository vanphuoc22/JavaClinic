/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.repository.impl;

import com.owen.pojo.Prescription;
import com.owen.pojo.ScheduleDetail;
import com.owen.pojo.ScheduleDetail;
import com.owen.pojo.Shift;
import com.owen.pojo.User;
import com.owen.repository.ScheduleRepository;
import com.owen.service.ShiftService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
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
public class ScheduleRepositoryImpl implements ScheduleRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private Environment env;

    @Override
    public List<ScheduleDetail> getSchedules(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<ScheduleDetail> q = b.createQuery(ScheduleDetail.class);
        Root<ScheduleDetail> root = q.from(ScheduleDetail.class);
        q.select(root);

//        q.orderBy(b.desc(root.get("id")));
        Query query = session.createQuery(q);

        return query.getResultList();
    }

    @Override
    public List<ScheduleDetail> getSchedules(Date fromDate, int roleId) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ScheduleDetail> query = builder.createQuery(ScheduleDetail.class);
        Root<ScheduleDetail> root = query.from(ScheduleDetail.class);
        query.select(root);

        if (fromDate != null) {
            Predicate fromDatePredicate = builder.greaterThanOrEqualTo(root.get("dateSchedule"), fromDate);
            Predicate statusPredicate = builder.notEqual(root.get("status"), 1);
            Predicate roleIdPredicate = builder.equal(root.get("userId").get("roleId"), roleId);
            Predicate finalPredicate = builder.and(fromDatePredicate, statusPredicate, roleIdPredicate);

            query.where(finalPredicate);
        }

        Query typedQuery = session.createQuery(query);
        return typedQuery.getResultList();
    }

    @Override
    public boolean checktontai(Date fromDate, int userid, int ca) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ScheduleDetail> query = builder.createQuery(ScheduleDetail.class);
        Root<ScheduleDetail> root = query.from(ScheduleDetail.class);
        query.select(root);
        Predicate fromDatePredicate = builder.greaterThanOrEqualTo(root.get("dateSchedule"), fromDate);
        Predicate statusPredicate = builder.equal(root.get("status"), 1);
        Predicate userIdPredicate = builder.equal(root.get("userId"), userid);
        Predicate finalPredicate = builder.and(fromDatePredicate, statusPredicate, userIdPredicate);

        query.where(finalPredicate);
        Query typedQuery = session.createQuery(query);
        List<ScheduleDetail> a = typedQuery.getResultList();
        for (ScheduleDetail test : a) {
            if (test.getShiftId().getId() == ca) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean checkLichHopLe(Date dateSchedule, int shiftId, int role) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<ScheduleDetail> root = query.from(ScheduleDetail.class);

        // Tạo join giữa bảng ScheduleDetail và User
        Join<ScheduleDetail, User> userJoin = root.join("userId");

        query.select(builder.count(root));

        // Tạo điều kiện lọc theo ngày đặt lịch
        Predicate datePredicate = builder.equal(root.get("dateSchedule"), dateSchedule);

        Predicate shiftPredicate = builder.equal(root.get("shiftId"), shiftId);

        // Tạo điều kiện lọc khi status bằng 1
        Predicate statusPredicate = builder.equal(root.get("status"), 1);

        // Tạo điều kiện lọc khi role của User là 2
        Predicate rolePredicate = builder.equal(userJoin.get("roleId"), role);

        // Kết hợp các điều kiện với nhau
        Predicate finalPredicate = builder.and(datePredicate, statusPredicate, rolePredicate, shiftPredicate);

        query.where(finalPredicate);

        Query typedQuery = session.createQuery(query);
        Long count = (Long) typedQuery.getSingleResult();

        // Kiểm tra số lượng bác sĩ đã đăng ký
        if (count >= 2) {
            return false; // Trả về "fail" nếu đã có 2 bác sĩ
        } else {
            return true; // Trả về "true" nếu chưa đủ 2 bác sĩ
        }
    }

    @Override
    public List<ScheduleDetail> getSchedulesaccepted(Date fromDate) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ScheduleDetail> query = builder.createQuery(ScheduleDetail.class);
        Root<ScheduleDetail> root = query.from(ScheduleDetail.class);
        query.select(root);

        if (fromDate != null) {
            // Tạo điều kiện lọc lịch từ ngày truyền vào trở đi
            Predicate fromDatePredicate = builder.greaterThanOrEqualTo(root.get("dateSchedule"), fromDate);

            // Tạo điều kiện lọc khi status bằng 1
            Predicate statusPredicate = builder.equal(root.get("status"), 1);

            // Kết hợp các điều kiện với nhau
            Predicate finalPredicate = builder.and(fromDatePredicate, statusPredicate);

            query.where(finalPredicate);
        }

        Query typedQuery = session.createQuery(query);
        return typedQuery.getResultList();
    }

    @Override
    public boolean addOrUpdateScheduleDetail(ScheduleDetail m) {
        Session s = this.factory.getObject().getCurrentSession();
        Shift s1 = s.get(Shift.class, 1);
        Shift s2 = s.get(Shift.class, 2);
        Shift s3 = s.get(Shift.class, 3);
        try {
            if (m.getId() == null) {
                if (m.getListdate1().length > 0) {
                    for (Date date : m.getListdate1()) {
                        ScheduleDetail newScheduleDetail = new ScheduleDetail();
                        newScheduleDetail.setDateSchedule(date);
                        newScheduleDetail.setShiftId(s1);
                        newScheduleDetail.setUserId(m.getUserId());
                        newScheduleDetail.setStatus(m.getStatus());

                        s.save(newScheduleDetail);
                    }
                }
                if (m.getListdate2().length > 0) {
                    for (Date date : m.getListdate2()) {
                        ScheduleDetail newScheduleDetail = new ScheduleDetail();
                        newScheduleDetail.setDateSchedule(date);
                        newScheduleDetail.setShiftId(s2);
                        newScheduleDetail.setUserId(m.getUserId());
                        newScheduleDetail.setStatus(m.getStatus());

                        s.save(newScheduleDetail);
                    }
                }
                if (m.getListdate3().length > 0) {
                    for (Date date : m.getListdate3()) {
                        ScheduleDetail newScheduleDetail = new ScheduleDetail();
                        newScheduleDetail.setDateSchedule(date);
                        newScheduleDetail.setShiftId(s3);
                        newScheduleDetail.setUserId(m.getUserId());
                        newScheduleDetail.setStatus(m.getStatus());

                        s.save(newScheduleDetail);
                    }
                }
            } else {
                if (m.getStatus() == 0) {
                    m.setStatus((short) 1);
                } else {
                    m.setStatus((short) 0);
                }
                s.update(m);
            }

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addOrUpdateScheduleDetails(List<ScheduleDetail> scheduleDetails) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            for (ScheduleDetail scheduleDetail : scheduleDetails) {
                if (scheduleDetail.getId() == null) {
                    s.save(scheduleDetail);
                } else {
                    s.update(scheduleDetail);
                }
            }
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteScheduleDetail(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        ScheduleDetail pr = session.get(ScheduleDetail.class, id);
        try {
            session.delete(pr);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Date> getScheduleofUser(User user, List<Date> dates, int idshift) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Date> query = builder.createQuery(Date.class);
        Root<ScheduleDetail> root = query.from(ScheduleDetail.class);
        query.select(root.get("dateSchedule"));
        Date from = dates.get(0);
        Date to = dates.get(6);

        Predicate shiftP = builder.equal(root.get("shiftId").get("id"), idshift);
        Predicate fromDateP = builder.greaterThanOrEqualTo(root.get("dateSchedule"), from);
        Predicate toDateP = builder.lessThanOrEqualTo(root.get("dateSchedule"), to);
        Predicate statusPredicate = builder.equal(root.get("status"), 0);
        Predicate userPredicate = builder.equal(root.get("userId").get("id"), user.getId());
        // Kết hợp các điều kiện với nhau
        Predicate finalPredicate = builder.and(userPredicate, statusPredicate, toDateP, fromDateP, shiftP);

        query.where(finalPredicate);
        Query typedQuery = session.createQuery(query);
        return typedQuery.getResultList();
    }

    @Override
    public List<ScheduleDetail> getSchedulesofUser(User user, List<Date> dates) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ScheduleDetail> query = builder.createQuery(ScheduleDetail.class);
        Root<ScheduleDetail> root = query.from(ScheduleDetail.class);
        query.select(root);

        Date from = dates.get(0);
        Date to = dates.get(6);

        Predicate fromDateP = builder.greaterThanOrEqualTo(root.get("dateSchedule"), from);
        Predicate toDateP = builder.lessThanOrEqualTo(root.get("dateSchedule"), to);
        Predicate statusPredicate = builder.equal(root.get("status"), 0);
        Predicate userPredicate = builder.equal(root.get("userId").get("id"), user.getId());
        // Kết hợp các điều kiện với nhau
        Predicate finalPredicate = builder.and(userPredicate, statusPredicate, toDateP, fromDateP);

        query.where(finalPredicate);
        TypedQuery<ScheduleDetail> typedQuery = session.createQuery(query);
        return typedQuery.getResultList();
    }
    @Override
    public List<ScheduleDetail> getScheduleNowofUser(User user, List<Date> dates) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ScheduleDetail> query = builder.createQuery(ScheduleDetail.class);
        Root<ScheduleDetail> root = query.from(ScheduleDetail.class);
        query.select(root);

        Date from = dates.get(0);
        Date to = dates.get(6);

        Predicate fromDateP = builder.greaterThanOrEqualTo(root.get("dateSchedule"), from);
        Predicate toDateP = builder.lessThanOrEqualTo(root.get("dateSchedule"), to);
        Predicate statusPredicate = builder.equal(root.get("status"), 1);
        Predicate userPredicate = builder.equal(root.get("userId").get("id"), user.getId());
        // Kết hợp các điều kiện với nhau
        Predicate finalPredicate = builder.and(userPredicate, statusPredicate, toDateP, fromDateP);

        query.where(finalPredicate);
        TypedQuery<ScheduleDetail> typedQuery = session.createQuery(query);
        return typedQuery.getResultList();
    }

    @Override
    public ScheduleDetail getScheduleDetailById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ScheduleDetail> query = builder.createQuery(ScheduleDetail.class);
        Root<ScheduleDetail> root = query.from(ScheduleDetail.class);
        query.where(
                builder.and(
                        builder.equal(root.get("id"), id)
                //                        builder.equal(root.get("roleId"), 2)
                )
        );
        Query q = session.createQuery(query);
        List<ScheduleDetail> results = q.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public List<ScheduleDetail> getScheduleDetailsByTaiKhoan(User user) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ScheduleDetail> query = builder.createQuery(ScheduleDetail.class);
        Root<ScheduleDetail> root = query.from(ScheduleDetail.class);
        query.select(root);

        Predicate statusPredicate = builder.equal(root.get("status"), 1);
        Predicate userPredicate = builder.equal(root.get("userId").get("id"), user.getId());
        // Kết hợp các điều kiện với nhau
        Predicate finalPredicate = builder.and(userPredicate, statusPredicate);

        query.where(finalPredicate);
        Query typedQuery = session.createQuery(query);
        return typedQuery.getResultList();
    }

//    @Override
//    public List<ScheduleDetail> getScheduleStatusByDatesAndShift(User user, List<Date> dates, int shiftId) {
//        Session session = this.factory.getObject().getCurrentSession();
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<ScheduleDetail> query = builder.createQuery(ScheduleDetail.class);
//        Root<ScheduleDetail> root = query.from(ScheduleDetail.class);
//        query.select(root);
//        Date fromDate = dates.get(0);
//        Date toDate = dates.get(6);
//        // Tạo điều kiện lọc lịch từ ngày truyền vào trở đi
//        Predicate fromDatePredicate = builder.greaterThanOrEqualTo(root.get("dateSchedule"), fromDate);
//
//        Predicate toDatePredicate = builder.lessThanOrEqualTo(root.get("dateSchedule"), toDate);
//        // Tạo điều kiện lọc khi status bằng 1
//        Predicate statusPredicate = builder.equal(root.get("status"), 1);
//        
//        Predicate shiftPredicate = builder.equal(root.get("shiftId"), shiftId);
//        
//        // Tạo điều kiện lọc khi userId bằng giá trị cụ thể
//        Predicate userIdPredicate = builder.equal(root.get("userId"), user.getId());
//
//        // Kết hợp các điều kiện với nhau
//        Predicate finalPredicate = builder.and(fromDatePredicate, statusPredicate, userIdPredicate, toDatePredicate,shiftPredicate);
//
//        query.where(finalPredicate);
//
//        Query typedQuery = session.createQuery(query);
//
//        return typedQuery.getResultList();
//    }
    private boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)
                && cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public List<Integer> getScheduleStatusByDatesAndShift(User user, List<Date> dates, int shiftId) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ScheduleDetail> query = builder.createQuery(ScheduleDetail.class);
        Root<ScheduleDetail> root = query.from(ScheduleDetail.class);
        query.select(root);
        Date fromDate = dates.get(0);
        Date toDate = dates.get(6);
        // Tạo điều kiện lọc lịch từ ngày truyền vào trở đi
        Predicate fromDatePredicate = builder.greaterThanOrEqualTo(root.get("dateSchedule"), fromDate);

        Predicate toDatePredicate = builder.lessThanOrEqualTo(root.get("dateSchedule"), toDate);
        // Tạo điều kiện lọc khi status bằng 1
        Predicate statusPredicate = builder.equal(root.get("status"), 1);

        Predicate shiftPredicate = builder.equal(root.get("shiftId"), shiftId);

        // Tạo điều kiện lọc khi userId bằng giá trị cụ thể
        Predicate userIdPredicate = builder.equal(root.get("userId"), user.getId());

        // Kết hợp các điều kiện với nhau
        Predicate finalPredicate = builder.and(fromDatePredicate, statusPredicate, userIdPredicate, toDatePredicate, shiftPredicate);

        query.where(finalPredicate);

        Query typedQuery = session.createQuery(query);

        List<ScheduleDetail> resultList = typedQuery.getResultList();

        List<Integer> listInt = new ArrayList<>();
        for (Date date : dates) {
            int status = 0;
            for (ScheduleDetail schedule : resultList) {
                if (isSameDate(schedule.getDateSchedule(), date)) {
                    status = 1;
                    break;
                }
            }
            listInt.add(status);
        }

        return listInt;
    }
    
    @Override
    public List<ScheduleDetail> getScheduleDetailsByTaiKhoanfordelete(User user) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ScheduleDetail> query = builder.createQuery(ScheduleDetail.class);
        Root<ScheduleDetail> root = query.from(ScheduleDetail.class);
        query.select(root);
        Predicate userPredicate = builder.equal(root.get("userId"), user.getId());
        // Kết hợp các điều kiện với nhau
        Predicate finalPredicate = builder.and(userPredicate);

        query.where(finalPredicate);
        Query typedQuery = session.createQuery(query);
        return typedQuery.getResultList();
    }

}
