/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.owen.repository;

import com.owen.pojo.ScheduleDetail;
import com.owen.pojo.User;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Trinh Bao Duy
 */
public interface ScheduleRepository {
    List<ScheduleDetail> getSchedules(Map<String, String> params);

    boolean addOrUpdateScheduleDetail(ScheduleDetail m);

    boolean deleteScheduleDetail(int id);

    boolean addOrUpdateScheduleDetails(List<ScheduleDetail> scheduleDetails);
    
    List<ScheduleDetail> getSchedules(Date fromDate,int roleId);
    List<ScheduleDetail> getSchedulesaccepted(Date fromDate);
    ScheduleDetail getScheduleDetailById(int id);
    boolean checkLichHopLe(Date dateSchedule, int shiftId,int role);
    List<ScheduleDetail> getScheduleDetailsByTaiKhoan(User user);
    List<Integer> getScheduleStatusByDatesAndShift(User user, List<Date> dates, int shiftId);
    boolean checktontai(Date fromDate, int roleId, int ca);
    List<Date> getScheduleofUser(User user, List<Date> dates, int idshift);
    List<ScheduleDetail> getSchedulesofUser(User user, List<Date> dates);
    List<ScheduleDetail> getScheduleNowofUser(User user, List<Date> dates);
    List<ScheduleDetail> getScheduleDetailsByTaiKhoanfordelete(User user);


}
