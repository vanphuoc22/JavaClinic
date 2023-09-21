/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.service.impl;

import com.cloudinary.utils.ObjectUtils;
import com.owen.pojo.Appointment;
import com.owen.pojo.PrescriptionItem;
import com.owen.pojo.ServiceItems;
import com.owen.pojo.User;
import com.owen.repository.AppointmentRepository;
import com.owen.repository.BillRepository;
import com.owen.repository.PrescriptionRepository;
import com.owen.repository.ServiceItemRepository;
import com.owen.repository.UserRepository;
import com.owen.repository.impl.UserRepositoryImpl;
import com.owen.service.AppointmentService;
import com.owen.service.BillService;
import com.owen.service.PrescriptionItemService;
import com.owen.service.PrescriptionService;
import com.owen.service.ServiceItemService;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Trinh Bao Duy
 */
@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private BillService billService;

    @Autowired
    private ServiceItemService serviceItemService;

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private PrescriptionItemService PrescriptionItemService;

    @Override
    public List<Appointment> getAppointments(Map<String, String> params) {
        return this.appointmentRepository.getAppointments(params);
    }

    @Override
    public List<Appointment> getAppointmentsunfished() {
        return this.appointmentRepository.getAppointmentsunfished();
    }

    @Override
    public Appointment changestatus(int m, User yta) {
        return this.appointmentRepository.changestatus(m, yta);
    }

    @Override
    public List<Appointment> getAppointmentsbyDoctor(User u) {
        return this.appointmentRepository.getAppointmentsbyDoctor(u);
    }

    @Override
    public List<Object[]> getAppointmentServiceByDoctor(User doctor) {
        return this.appointmentRepository.getAppointmentServiceByDoctor(doctor);
    }

    @Override
    public Appointment getAppointmentById(int id) {
        return this.appointmentRepository.getAppointmentById(id);

    }

    @Override
    public boolean addOrUpdateAppointment(Appointment m) {
        return this.appointmentRepository.addOrUpdateAppointment(m);
    }

    @Override
    public List<Appointment> getAppointmentsbyUser(User u, Date date) {
        return this.appointmentRepository.getAppointmentsbyUser(u, date);
    }

    @Override
    public boolean canAcceptAppointment(Date date) {
        return this.appointmentRepository.canAcceptAppointment(date);
    }

    @Override
    public List<Integer> getCountUserByMonth(int year) {
        return this.appointmentRepository.getCountUserByMonth(year);
    }

    @Override
    public Integer getCountUserByOneMonth(int month) {
        return this.appointmentRepository.getCountUserByOneMonth(month);
    }

    @Override
    public List<Integer> getCountUserByQuarter(List<Integer> months) {
        return this.appointmentRepository.getCountUserByQuarter(months);
    }

    @Override
    public List<Integer> getCountUserByQuarter(int year) {
        return this.appointmentRepository.getCountUserByQuarter(year);
    }

    @Override
    public Appointment dangkykham(Map<String, String> params) {
        Appointment a = new Appointment();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date parsedDate = null;
        Date parsedTime = null;
        Date parsedDateTime = null;
//        Object thoigian = params.get("appointmentTime");
        try {
            parsedDate = dateFormat.parse(params.get("appointmentDate"));
            parsedTime = timeFormat.parse(params.get("appointmentTime"));

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(parsedDate);

            Calendar timeCalendar = Calendar.getInstance();
            timeCalendar.setTime(parsedTime);

            // Thiết lập giờ, phút và giây từ parsedTime
            int hour = timeCalendar.get(Calendar.HOUR_OF_DAY);
            int minute = timeCalendar.get(Calendar.MINUTE);
            int second = timeCalendar.get(Calendar.SECOND);

            // Thiết lập giờ, phút và giây cho parsedDate
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);
            calendar.set(Calendar.SECOND, second);

            // Lấy ngày và giờ đã chỉnh sửa
            parsedDateTime = calendar.getTime();
        } catch (ParseException ex) {
            Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        User nguoibenh = this.UserRepository.getUserById(Integer.parseInt(params.get("sickpersonId")));
        a.setAppointmentDate(parsedDateTime);
        a.setSickpersonId(nguoibenh);
        this.appointmentRepository.addOrUpdateAppointment(a);
        return a;
    }

    @Override
    public List<Appointment> getAppointmentcantPay() {
        return this.appointmentRepository.getAppointmentcantPay();
    }

    @Override
    public List<Appointment> getAppointmentsUserbyDate(int userId, int day, int month, int year) {
        return this.appointmentRepository.getAppointmentsUserbyDate(userId, day, month, year);
    }

    @Override
    public List<Appointment> getAppointmentsbySickperson(int id) {
        User nguoibenh = this.UserRepository.getUserById(id);
        return this.appointmentRepository.getAppointmentsbySickperson(nguoibenh);
    }

    @Override
    public boolean deleteAppo(int id) {
        if (this.billService.getBillByApoId(id) != null) {
            this.billService.deleteBill(this.billService.getBillByApoId(id).getId());
        }
        if (this.serviceItemService.getServicecbyAppoID(id) != null) {
            List<ServiceItems> ds = this.serviceItemService.getServicecbyAppoID(id);
            for (ServiceItems ser : ds) {
                this.serviceItemService.deleteServiceItems(ser.getId());
            }
        }
        if (this.appointmentRepository.getAppointmentById(id).getPrescriptionId() != null) {
            if (this.PrescriptionItemService.getPrescriptionsbyIDPres(this.appointmentRepository.getAppointmentById(id).getPrescriptionId().getId()) != null) {
                List<PrescriptionItem> ds = this.PrescriptionItemService.getPrescriptionsbyIDPres(this.appointmentRepository.getAppointmentById(id).getPrescriptionId().getId());
                for (PrescriptionItem pre : ds) {
                    this.PrescriptionItemService.deletePres(pre.getId());
                }
            }
        }

        return this.appointmentRepository.deleteAppo(id);
    }

    @Override
    public List<Appointment> getAppointmentsbyDoctorfordelete(User u) {
        return this.appointmentRepository.getAppointmentsbyDoctorfordelete(u);
    }

    @Override
    public List<Appointment> getAppointmentsbyNursefordelete(User u) {
        return this.appointmentRepository.getAppointmentsbyNursefordelete(u);
    }

    @Override
    public List<Appointment> getAppointmentsbySickPersonfordelete(User u) {
        return this.appointmentRepository.getAppointmentsbySickPersonfordelete(u);
    }

    @Override
    public List<Appointment> getAppointmentsbyIDPrefordelete(int id) {
        return this.appointmentRepository.getAppointmentsbyIDPrefordelete(id);
    }

}
