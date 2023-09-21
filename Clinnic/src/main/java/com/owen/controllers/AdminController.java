/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.controllers;

import com.cloudinary.Cloudinary;
import com.owen.pojo.ScheduleDetail;
import com.owen.pojo.User;
import com.owen.service.AppointmentService;
import com.owen.service.BillService;
import com.owen.service.ScheduleService;
import com.owen.service.ShiftService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.owen.service.UserService;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Trinh Bao Duy
 */
@Controller
public class AdminController {

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private UserService userService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private CustomDateEditor customDateEditor;

    @Autowired
    private Environment env;

    @Autowired
    private ShiftService shiftService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private BillService BillService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, customDateEditor);
    }

    @GetMapping("/admin/quanlytaikhoan")
    public String quanlytaikhoan(Model model, @RequestParam Map<String, String> params, Authentication authentication) {
        model.addAttribute("user", this.userService.getUsers(params));
        if (authentication != null) {
            UserDetails user = this.userService.loadUserByUsername(authentication.getName());
            User u = this.userService.getUserByUsername(user.getUsername());
            model.addAttribute("admin", u);

        }
        int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
        long count = this.userService.countUser();
        model.addAttribute("counter", Math.ceil(count * 1.0 / pageSize));
        return "quanlytaikhoan";
    }

    @GetMapping("/admin/quanlytaikhoan/themtaikhoan")
    public String themtaikhoan(Model model, Authentication authentication) {
        if (authentication != null) {
            UserDetails user = this.userService.loadUserByUsername(authentication.getName());
            User u = this.userService.getUserByUsername(user.getUsername());
            model.addAttribute("admin", u);

        }
        model.addAttribute("nguoidung", new User());
        return "themtaikhoan";
    }

    @GetMapping("/admin/quanlytaikhoan/themtaikhoan/{id}")
    public String capnhattaikhoan(Model model, @PathVariable(value = "id") int id, Authentication authentication) {
        if (authentication != null) {
            UserDetails user = this.userService.loadUserByUsername(authentication.getName());
            User u = this.userService.getUserByUsername(user.getUsername());
            model.addAttribute("admin", u);
        }
        model.addAttribute("nguoidung", this.userService.getUserById(id));
        return "themtaikhoan";
    }

    @PostMapping("/admin/quanlytaikhoan/themtaikhoan")
    public String addAndUpUser(Model model, @ModelAttribute(value = "nguoidung") @Valid User us, BindingResult rs, Authentication authentication) throws IOException {
        if (!rs.hasErrors()) {
            if (this.userService.addOrUpdateUser(us) == true) {
                return "redirect:/admin/quanlytaikhoan";
            }
        }
        return "themtaikhoan";
    }

    @GetMapping("/admin")
    public String admin(Model model, Authentication authentication) {
        if (authentication != null) {
            UserDetails user = this.userService.loadUserByUsername(authentication.getName());
            User u = this.userService.getUserByUsername(user.getUsername());
            model.addAttribute("admin", u);

        }
        return "admin";
    }

    @GetMapping("/admin/thongke")
    public String thongke(Model model, Authentication authentication) {
        if (authentication != null) {
            UserDetails user = this.userService.loadUserByUsername(authentication.getName());
            User u = this.userService.getUserByUsername(user.getUsername());
            model.addAttribute("admin", u);
        }
        model.addAttribute("um", 2023);
        model.addAttribute("list", this.appointmentService.getCountUserByMonth(2023));
        model.addAttribute("listq", this.appointmentService.getCountUserByQuarter(2023));
        model.addAttribute("listdoanhthu", this.BillService.getRevenueByMonth(2023));
        return "thongke";
    }

    @PostMapping("/admin/thongke")
    public String thongkexuli(Model model, @RequestParam("yearofnd") int yearofrevenuebymonth, Authentication authentication) {
        if (authentication != null) {
            UserDetails user = this.userService.loadUserByUsername(authentication.getName());
            User u = this.userService.getUserByUsername(user.getUsername());
            model.addAttribute("admin", u);
        }
        model.addAttribute("um", yearofrevenuebymonth);
        model.addAttribute("list", this.appointmentService.getCountUserByMonth(yearofrevenuebymonth));
        model.addAttribute("listq", this.appointmentService.getCountUserByQuarter(yearofrevenuebymonth));
        return "thongke";
    }

    @GetMapping("/admin/thongkedoanhthu")
    public String thongkedoanhthu(Model model, Authentication authentication) {
        if (authentication != null) {
            UserDetails user = this.userService.loadUserByUsername(authentication.getName());
            User u = this.userService.getUserByUsername(user.getUsername());
            model.addAttribute("admin", u);
        }
        model.addAttribute("um", 2023);
        model.addAttribute("listdt", this.BillService.getRevenueByMonth(2023));
        model.addAttribute("listdtq", this.BillService.getRevenueByQuarter(2023));
        return "thongkedoanhthu";
    }

    @PostMapping("/admin/thongkedoanhthu")
    public String thongkexulidoanhthu(Model model, @RequestParam("yearofnd") int yearofrevenuebymonth, Authentication authentication) {
        if (authentication != null) {
            UserDetails user = this.userService.loadUserByUsername(authentication.getName());
            User u = this.userService.getUserByUsername(user.getUsername());
            model.addAttribute("admin", u);
        }
        model.addAttribute("um", yearofrevenuebymonth);
        model.addAttribute("listdt", this.BillService.getRevenueByMonth(yearofrevenuebymonth));
        model.addAttribute("listdtq", this.BillService.getRevenueByQuarter(yearofrevenuebymonth));
        return "thongkedoanhthu";
    }

    @GetMapping("/admin/saplichlam")
    public String lichlam(Model model, Authentication authentication) {
//        msg = null;
        List<Date> dateList = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // Đặt ngày là thứ Hai
        calendar.add(Calendar.WEEK_OF_YEAR, 1);
        dateList.add(calendar.getTime()); // Thêm ngày thứ Hai gần nhất vào danh sách
        for (int i = 0; i < 6; i++) { // Thêm các ngày từ thứ Ba đến Chủ nhật
            calendar.add(Calendar.DAY_OF_WEEK, 1);
            dateList.add(calendar.getTime());
        }
        model.addAttribute("dateList", dateList);
        model.addAttribute("lichundonebs", this.scheduleService.getSchedules(dateList.get(0), 2));
        model.addAttribute("lichundoneyt", this.scheduleService.getSchedules(dateList.get(0), 3));
        model.addAttribute("lichdone", this.scheduleService.getSchedulesaccepted(dateList.get(0)));
        if (authentication != null) {
            UserDetails user = this.userService.loadUserByUsername(authentication.getName());
            User u = this.userService.getUserByUsername(user.getUsername());
            model.addAttribute("admin", u);

        }
//        model.addAttribute("msg", msg);
        return "saplichlam";
    }

    @PostMapping("/admin/saplichlam")
    public String saplichlam(@ModelAttribute(value = "lichlam") @Valid ScheduleDetail scheduleDetail,
            BindingResult rs) {
        if (!rs.hasErrors()) {
            if (this.scheduleService.addOrUpdateScheduleDetail(scheduleDetail) == true) {
                return "redirect:/admin/saplichlam";
            }
        }
        return "saplichlam";
    }

    @GetMapping("/admin/saplichlam/xatnhan/{id}")
    public String saplichlamxatnhan(Model model, @PathVariable(value = "id") int id) throws UnsupportedEncodingException {
        String msg = "";
        ScheduleDetail s = this.scheduleService.getScheduleDetailById(id);
        User u = this.userService.getUserById(s.getUserId().getId());
//        if (this.scheduleService.checktontai(s.getDateSchedule(), u.getRoleId().getId(), s.getShiftId().getId()) == true) {
        if (this.scheduleService.checkLichHopLe(s.getDateSchedule(), s.getShiftId().getId(), u.getRoleId().getId()) == true) {
            if (this.scheduleService.addOrUpdateScheduleDetail(s) == true) {
                return "redirect:/admin/saplichlam";
            }
        } else {
            msg = "Đã quá số lượng nhân viên làm trong ngày" + s.getDateSchedule();
            return "redirect:/admin/saplichlam" + "?msg=" + URLEncoder.encode(msg, "UTF-8");

        }
//        } else {
//            msg = "Đã tồn tại lịch ngày" + s.getDateSchedule()+" vào ca "+s.getShiftId().getName()+" của "+s.getUserId().getName();
//            return "redirect:/admin/saplichlam" + "?msg=" + URLEncoder.encode(msg, "UTF-8");
//        }
        return "saplichlam";
    }

    @GetMapping("/admin/saplichlam/huy/{id}")
    public String saplichlamhuy(@PathVariable(value = "id") int id) {
        ScheduleDetail s = this.scheduleService.getScheduleDetailById(id);
        if (this.scheduleService.addOrUpdateScheduleDetail(s) == true) {
            return "redirect:/admin/saplichlam";
        }
        return "saplichlam";
    }
}


