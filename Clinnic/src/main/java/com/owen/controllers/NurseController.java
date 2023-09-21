/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.owen.controllers;

import com.owen.pojo.Appointment;
import com.owen.pojo.Bill;
import com.owen.pojo.PrescriptionItem;
import com.owen.pojo.ScheduleDetail;
import com.owen.pojo.User;
import com.owen.service.AppointmentService;
import com.owen.service.BillService;
import com.owen.service.PaymentService;
import com.owen.service.PrescriptionItemService;
import com.owen.service.ScheduleService;
import com.owen.service.ServiceItemService;
import com.owen.service.ShiftService;
import com.owen.service.UserService;
import com.springmvc.dto.momoclasses.PaymentResponse;
import com.springmvc.enums.RequestType;
import com.springmvc.momoprocessor.CreateOrderMoMo;
import com.springmvc.share.utils.LogUtils;
import com.springmvc.dto.momoclasses.Environment;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.apache.commons.mail.Email;
//import org.apache.commons.mail.EmailException;
//import org.apache.commons.mail.SimpleEmail;
//import org.apache.commons.mail.DefaultAuthenticator;

/**
 *
 * @author hung
 */
@Controller
public class NurseController {

    @Autowired
    private CustomDateEditor customDateEditor;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, customDateEditor);
    }
    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private ShiftService shiftService;

    @Autowired
    private PrescriptionItemService prescriptionItemService;

    @Autowired
    private ServiceItemService serviceItemService;

    @Autowired
    private BillService billService;

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/nurse")
    public String nurseInfor(Model model, @RequestParam Map<String, String> params, Authentication authentication) {
        model.addAttribute("getbacsi", this.userService.getBacSi());
        model.addAttribute("Apo", this.appointmentService.getAppointments(params));
        model.addAttribute("appoment", new Appointment());
        model.addAttribute("UnApo", this.appointmentService.getAppointmentsunfished());
        model.addAttribute("thanhtoan", this.appointmentService.getAppointmentcantPay());
        if (authentication != null) {
            UserDetails user = this.userService.loadUserByUsername(authentication.getName());
            User u = this.userService.getUserByUsername(user.getUsername());
            model.addAttribute("nurse", u);
        }
        return "nurse";
    }

    @GetMapping("/nurse/{id}")
    public String xuli(Model model, @PathVariable(value = "id") int id, @RequestParam Map<String, String> params, Authentication authentication, HttpServletRequest request) {
        model.addAttribute("getbacsi", this.userService.getBacSi(id));
//        HttpSession session = request.getSession();
//        session.setAttribute("appoment", this.appointmentService.getAppointmentById(id));
        model.addAttribute("appoment", this.appointmentService.getAppointmentById(id));
        if (authentication != null) {
            UserDetails user = this.userService.loadUserByUsername(authentication.getName());
            User u = this.userService.getUserByUsername(user.getUsername());
            model.addAttribute("nurse", u);
//             model.addAttribute("appoment", this.appointmentService.changestatus(id, u));
            model.addAttribute("Apo", this.appointmentService.getAppointments(params));
//            return "forward:/nurse";
        }
//        }
        return "nurse";
    }

    @GetMapping("/nurse/huy{id}")
    public String xulihuy(Model model, @PathVariable(value = "id") int id, Authentication authentication, HttpServletRequest request) {
        if (authentication != null) {
            UserDetails user = this.userService.loadUserByUsername(authentication.getName());
            User u = this.userService.getUserByUsername(user.getUsername());
            model.addAttribute("nurse", u);
            this.appointmentService.changestatus(id, u);
            return "redirect:/nurse";

        }
//        }
        return "nurse";
    }

    @PostMapping("/nurse")
    public String Update(Model model, Authentication authentication, @ModelAttribute(value = "appoment") @Valid Appointment a,
            BindingResult rs) throws MessagingException {
        if (!rs.hasErrors()) {
            Date ngaykham = a.getAppointmentDate();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String ngaydikham = dateFormat.format(a.getAppointmentDate());
            if (this.appointmentService.canAcceptAppointment(ngaykham) == true) {
                if (this.appointmentService.addOrUpdateAppointment(a) == true) {
                    MimeMessage message = emailSender.createMimeMessage();
                    MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
                    String nguoinhan = this.userService.getUserById(a.getSickpersonId().getId()).getEmaill();
                    String tennguoibenh = this.userService.getUserById(a.getSickpersonId().getId()).getName();
                    String tenbacsi = this.userService.getUserById(a.getDoctorId().getId()).getName();
                    System.err.println(nguoinhan);
                    helper.setTo(nguoinhan);
                    helper.setSubject("LỊCH HẸN PHÒNG MẠCH");

                    String content = "<html><body>"
                            + "<p>Xin chào, " + tennguoibenh + "</p>"
                            + "<p>Bạn có lịch hẹn khám tại phòng mạch PIXCEL vào ngày [" + ngaydikham + "]</p>"
                            + "<p>Bác sĩ của bạn là " + tenbacsi + " .</p>"
                            + "<p>Chúng tôi hân hạnh chào đón bạn.</p>"
                            + "</body></html>";

                    helper.setText(content, true);

                    emailSender.send(message);
//                    model.addAttribute("successMessage", "Lịch hẹn đã được cập nhật và email đã được gửi thành công.");
                    return "redirect:/nurse";
                }

            } else {
                MimeMessage message = emailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
                String nguoinhan = this.userService.getUserById(a.getSickpersonId().getId()).getEmaill();
                String tennguoibenh = this.userService.getUserById(a.getSickpersonId().getId()).getName();
                System.err.println(nguoinhan);
                helper.setTo(nguoinhan);
                helper.setSubject("LỊCH HẸN PHÒNG MẠCH");

                String content = "<html><body>"
                        + "<p>Xin chào, " + tennguoibenh + "</p>"
                        + "<p>Bạn có lịch hẹn khám tại phòng mạch PIXCEL vào ngày [" + ngaydikham + "]</p>"
                        + "<p>Tuy nhiên , phòng mạch của chúng tôi đã đạt tối đa số lượng khách.</p>"
                        + "<p>Hi vọng bạn có thể chọn khám vào một ngày khác .Chúng tôi hân hạnh chào đón bạn.</p>"
                        + "</body></html>";

                helper.setText(content, true);

                emailSender.send(message);
//                model.addAttribute("errorMessage", "Không thể đặt lịch hẹn. Phòng mạch đã đạt tối đa số lượng khách.");
                return "redirect:/nurse";
            }
        }

        return "nurse";
    }

    @GetMapping("/nurse/thanhtoan/{id}")
    public String thanhtoan(Model model, @PathVariable(value = "id") int id,Authentication authentication) {
        model.addAttribute("appo", this.appointmentService.getAppointmentById(id));
        Appointment a = this.appointmentService.getAppointmentById(id);
        int idPre = a.getPrescriptionId().getId();
        int tongtien = this.billService.tinhtien(this.billService.getBillByApoId(id));
        model.addAttribute("tongtien", tongtien);
        List<PrescriptionItem> thuocs = this.prescriptionItemService.getPrescriptionsbyIDPres(idPre);
        model.addAttribute("thuoc", this.prescriptionItemService.getPrescriptionsbyIDPres(idPre));
        model.addAttribute("dichvu", this.serviceItemService.getServicecbyAppoID(id));
        model.addAttribute("pay", this.paymentService.getPayments());
        model.addAttribute("bill", this.billService.getBillByApoId(id));
        if (authentication != null) {
            UserDetails user = this.userService.loadUserByUsername(authentication.getName());
            User u = this.userService.getUserByUsername(user.getUsername());
            model.addAttribute("nurse", u);
        }
        return "thanhtoan";
    }

    @GetMapping("/paymomo/{id}")
    public String momoPay(Model model, @PathVariable(value = "id") int id) throws Exception {
        Bill b = this.billService.getBillById(id);
        LogUtils.init();
        String requestId = String.valueOf(System.currentTimeMillis());
        String orderId = String.valueOf(System.currentTimeMillis());
        long amount = b.getPayMoney();

        String orderInfo = "Thanh toán hóa đơn";
        String returnURL = "redirect:nurse/thanhtoan/" + id;
        String notifyURL = "redirect:nurse/thanhtoan/" + id;
        Environment environment = Environment.selectEnv("dev");
        model.addAttribute("nhap", "nhap");
        PaymentResponse captureWalletMoMoResponse = CreateOrderMoMo.process(environment, orderId, requestId, Long.toString(amount), orderInfo, returnURL, notifyURL, "", RequestType.CAPTURE_WALLET, Boolean.TRUE);
        String url = captureWalletMoMoResponse.getPayUrl();
        return "redirect:" + url;

    }

    @PostMapping("/nurse/thanhtoan")
    public String xulithanhtoan(Model model, @ModelAttribute(value = "bill") @Valid Bill bill, BindingResult rs) throws MessagingException, Exception {
        if (!rs.hasErrors()) {
            if (this.billService.addOrUpdateBill(bill) == true) {
                if (bill.getPayId().getId() == 1) {
                    return "redirect:/nurse";
                }
                if (bill.getPayId().getId() == 2) {
                    LogUtils.init();
                    String requestId = String.valueOf(System.currentTimeMillis());
                    String orderId = String.valueOf(System.currentTimeMillis());
                    long amount = bill.getPayMoney();

                    String orderInfo = "Thanh toán hóa đơn";
                    String returnURL = "redirect:nurse/thanhtoan/" + bill.getId();
                    String notifyURL = "redirect:nurse/thanhtoan/" + bill.getId();
                    Environment environment = Environment.selectEnv("dev");
                    model.addAttribute("nhap", "nhap");
                    PaymentResponse captureWalletMoMoResponse = CreateOrderMoMo.process(environment, orderId, requestId, Long.toString(amount), orderInfo, returnURL, notifyURL, "", RequestType.CAPTURE_WALLET, Boolean.TRUE);
                    String url = captureWalletMoMoResponse.getPayUrl();
                    return "redirect:" + url;
                }
            }
        }
        return "thanhtoan";
    }

    @GetMapping("/nurse/dangkylam")
    public String dangkylam(Model model, Authentication authentication) {

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
//        

//        List<ScheduleDetail> scheduleDetails = new ArrayList<ScheduleDetail>();
        model.addAttribute("lichlam", new ScheduleDetail());
        model.addAttribute("lich", this.shiftService.getShifts());
        if (authentication != null) {
            UserDetails user = this.userService.loadUserByUsername(authentication.getName());
            User u = this.userService.getUserByUsername(user.getUsername());
            model.addAttribute("nurse", u);
            model.addAttribute("lichhientaica1", this.scheduleService.getScheduleofUser(u, dateList, 1));
            model.addAttribute("lichhientaica2", this.scheduleService.getScheduleofUser(u, dateList, 2));
            model.addAttribute("lichhientaica3", this.scheduleService.getScheduleofUser(u, dateList, 3));

        }
        return "dangkylamviec";
    }

    @PostMapping("/nurse/dangkylam")
    public String update(@ModelAttribute(value = "lichlam") @Valid ScheduleDetail scheduleDetail,
            BindingResult rs) {
        if (!rs.hasErrors()) {
            if (this.scheduleService.addOrUpdateScheduleDetail(scheduleDetail) == true) {
                return "redirect:/nurse/dangkylam";
            }
        }
        return "dangkylamviec";
    }
    @GetMapping("/nurse/xemlichlam")
    public String xemlichlam(Model model, Authentication authentication) {
        List<Date> dateListnow = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // Đặt ngày là thứ Hai của tuần hiện tại

        for (int i = 0; i < 7; i++) { // Thêm các ngày từ thứ Hai đến Chủ nhật
            dateListnow.add(calendar.getTime());
            calendar.add(Calendar.DAY_OF_WEEK, 1);
        }

        List<Date> dateList = new ArrayList<>();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // Đặt ngày là thứ Hai
        calendar2.add(Calendar.WEEK_OF_YEAR, 1);
        dateList.add(calendar2.getTime()); // Thêm ngày thứ Hai gần nhất vào danh sách
        for (int i = 0; i < 6; i++) { // Thêm các ngày từ thứ Ba đến Chủ nhật
            calendar2.add(Calendar.DAY_OF_WEEK, 1);
            dateList.add(calendar2.getTime());
        }
        model.addAttribute("dateList", dateList);

//        List<ScheduleDetail> scheduleDetails = new ArrayList<ScheduleDetail>();
        model.addAttribute("lichlam", new ScheduleDetail());
        model.addAttribute("lich", this.shiftService.getShifts());

        if (authentication != null) {
            UserDetails user = this.userService.loadUserByUsername(authentication.getName());
            User u = this.userService.getUserByUsername(user.getUsername());
            model.addAttribute("nurse", u);
            model.addAttribute("lichhientaica", this.scheduleService.getSchedulesofUser(u, dateList));
            model.addAttribute("lichhientai", this.scheduleService.getScheduleNowofUser(u, dateListnow));
        }

        return "xemlichlamyta";
    }

    @GetMapping("/nurse/xemlichlam/huy/{id}")
    public String huylichlam(Model model, Authentication authentication, @PathVariable(value = "id") int id) {

        List<Date> dateListnow = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // Đặt ngày là thứ Hai của tuần hiện tại

        for (int i = 0; i < 7; i++) { // Thêm các ngày từ thứ Hai đến Chủ nhật
            dateListnow.add(calendar.getTime());
            calendar.add(Calendar.DAY_OF_WEEK, 1);
        }

        List<Date> dateList = new ArrayList<>();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // Đặt ngày là thứ Hai
        calendar2.add(Calendar.WEEK_OF_YEAR, 1);
        dateList.add(calendar2.getTime()); // Thêm ngày thứ Hai gần nhất vào danh sách
        for (int i = 0; i < 6; i++) { // Thêm các ngày từ thứ Ba đến Chủ nhật
            calendar2.add(Calendar.DAY_OF_WEEK, 1);
            dateList.add(calendar2.getTime());
        }
        model.addAttribute("dateList", dateList);

//        List<ScheduleDetail> scheduleDetails = new ArrayList<ScheduleDetail>();
        model.addAttribute("lichlam", new ScheduleDetail());
        model.addAttribute("lich", this.shiftService.getShifts());

        if (authentication != null) {
            UserDetails user = this.userService.loadUserByUsername(authentication.getName());
            User u = this.userService.getUserByUsername(user.getUsername());
            model.addAttribute("nurse", u);
            model.addAttribute("lichhientaica", this.scheduleService.getSchedulesofUser(u, dateList));
            model.addAttribute("lichhientai", this.scheduleService.getScheduleNowofUser(u, dateListnow));
        }
        if (this.scheduleService.deleteScheduleDetail(id) == true) {
            return "redirect:/nurse/xemlichlam";
        }

        return "nurse";
    }

}
