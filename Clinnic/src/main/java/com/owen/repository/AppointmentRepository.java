/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.owen.repository;

import com.owen.pojo.Appointment;
import com.owen.pojo.User;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Trinh Bao Duy
 */
public interface AppointmentRepository {

    List<Appointment> getAppointments(Map<String, String> params);

    List<Appointment> getAppointmentsunfished();

    Appointment changestatus(int id, User yta);

    List<Appointment> getAppointmentsbyDoctor(User u);

    List<Object[]> getAppointmentServiceByDoctor(User doctor);

    boolean addOrUpdateAppointment(Appointment m);

    Appointment getAppointmentById(int id);

    List<Appointment> getAppointmentsbyUser(User u, Date date);

    boolean canAcceptAppointment(Date date);

    List<Integer> getCountUserByMonth(int year);

    List<Integer> getCountUserByQuarter(int year);

    Integer getCountUserByOneMonth(int month);

    List<Integer> getCountUserByQuarter(List<Integer> months);

    List<Appointment> getAppointmentcantPay();

    List<Appointment> getAppointmentsUserbyDate(int userId, int day, int month, int year);

    List<Appointment> getAppointmentsbySickperson(User u);

    boolean deleteAppo(int id);

    List<Appointment> getAppointmentsbyDoctorfordelete(User u);

    List<Appointment> getAppointmentsbyNursefordelete(User u);

    List<Appointment> getAppointmentsbySickPersonfordelete(User u);
    
    List<Appointment> getAppointmentsbyIDPrefordelete(int id);

}
